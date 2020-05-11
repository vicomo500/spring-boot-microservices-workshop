# spring-boot-microservices-workshop 
A simple java spring boot workshop application (from [Java Brains Springboot Microservices tutorial](https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas)), that implements the microservices architecture, meant to get anybody started with spring boot microservices.  
It is a sample movie catalog application that allows users list and rate movies they have watched. It consists of 4 microservices:
1. Discovery Server - enables communication among the other micro-services ***(i.e. service discovery)***, using Eureka  
2. Movie Catalog Service - consumed by any client, it consolidates data from ratings-data-service and movie-info-service  
3. Movie Info Service - manages movie information data (also makes use of [The Movie DB](http://themoviedb.org/documentation/api) external api)  
4. Ratings Data Service - manages the user ratings for listed movies  
  
---

## Main Features / Dependencies
* [Eureka](https://spring.io/guides/gs/service-registration-and-discovery/) for service discovery  
* [Hystrix](https://spring.io/guides/gs/circuit-breaker/) for circuit breaker pattern (i.e. fault tolerance and resilience)  
  
---  
  
### NOTE:  
* > App uses hardcoded data ***(i.e. no database)***.
* > To properly utilise the movie-info-service, register for your api key at <http://www.themoviedb.org/> and replace the `api.key` property default value in the movie-info-service [application.properties](movie-info-service/src/main/resources/application.properties) file.