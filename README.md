# Tasks Challenge

### Requirements

- Java 21
- Maven
- Docker

### Notes

- Hexagonal architecture is being used. Spring has been considered part of the infrastructure so
  there are no Spring annotations inside the 'application' folder.
  Use cases are being initialized inside 'infrastructure/configuration' folder
- Java records are being used for immutability
- A MongoDB index is automatically created for the field userId
- Spring Boot Docker Compose support is being used. Docker containers (for Mongo) are
  executed just by running the Spring application
- Integration testing is done with Testcontainers

### Improvements

- Use references for MongoDB relationships instead of embedded data
- More tests could be added

## How to run

````bash
mvn clean compile
mvn spring-boot:run
````

You can check the OpenAPI documentation:

- Using Swagger UI: http://localhost:8080/swagger-ui/index.html
- In JSON format: http://localhost:8080/v3/api-docs
- In YAML format: http://localhost:8080/v3/api-docs.yaml

## How to run tests

````bash
mvn verify
````
