# inventory_service
![Java CI with Maven](https://github.com/MrWalshyType2/inventory_service/workflows/Java%20CI%20with%20Maven/badge.svg?branch=main)

## Versioning
MAJOR.MINOR.PATCH-RELEASE.BUILD
v1.0.0-SNAPSHOT.132

## Profiles
The active profile can be set in different ways, an easy way is via a JVM system parameter that Spring can recognise:

```
./mvnw spring-boot:run -Dspring.profiles.active=dev

./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

A configuration class could also be used to set the active profile programmatically:

```
@Configuration
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
 
        servletContext.setInitParameter(
          "spring.profiles.active", "dev");
    }
}
```

## Gateway
Actuator endpoint exposed for 'gateway', access using:

```
http://localhost/actuator/gateway/routes
```
