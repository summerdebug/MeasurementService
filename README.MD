# Measurement service
Service for collect Utilities measurements - gas, water and electricity.

## Description
REST service based on Maven, Java 11, Spring Boot and in-memory database HSQLDB.
There are 2 REST functions:
- Add a measurement
- Get the measurements by user id

Database contains 5 users with ids from 1 to 5.
Adding measurement for non-existent user will result in exception.

## View Measurements
http://localhost:8080/api/measurement/1

## Add Measurement
In Soap UI send POST request.
URL: http://localhost:8080/api/measurement
Media Type: application/json
Body:
```json
{"userId":1,"type":"GAS","value":15}
```