# Routing Application
Routing application is responsible for calculating possible land routes from one country to another.

## Technologies
- [Java 11](https://docs.oracle.com/en/java/javase/11/)
- [Spring Boot Framework](https://spring.io/projects/spring-boot) (2.7.6)

## Setup
### Local environment pre-requirements
- Install Java 11
- Install Maven 3.6+

## Getting Started
1. Clone the repository:
```
git clone https://github.com/SN4NTR/RoutingApplication.git
```
2. Go to folder with project and build it:
```
mvn clean package
```
3. Run application:
```
mvn spring-boot:run
```
4. Send test request:
```
curl --location --request GET 'http://localhost:8080/routing/CZE/ITA'
```