Run application with

```
mvn spring-boot:run
```

Then go to: http://localhost:8080/swagger-ui.html to test the application




In src/main/resources/aaplication.yml change
```
spring:
  profiles:  
    active: dev
```

to
```
spring:
  profiles:  
    active: prod
```

to use real weather data. (Default is dev to not consume limited OpenWeatherMap resources)