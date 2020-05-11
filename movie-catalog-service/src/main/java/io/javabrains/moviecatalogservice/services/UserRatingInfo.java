package io.javabrains.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                 @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                 @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                 @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            },
            /* BULK HEAD PATTERN */
            threadPoolKey = "userRatingPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "20"),
                @HystrixProperty(name = "maxQueueSize", value = "10"),
            }
        )
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(String userId){
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Collections.singletonList(
                new Rating("0", 0)
        ));
        return userRating;
    }
}