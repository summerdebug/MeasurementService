# Measurement service

## Description
This is a REST service based on Maven, Java 8, Spring and HSQLDB.
REST service exposes 2 methods
- To add a measurement
- To get the measurements for a particular user

5 users with ids from 1 to 5 already exist in the database.
Method to add new users is not implemented. This functionality is considered as TODO.
Trying to add measurement for non-existent user will cause an exception.

## View source code
Import project to IntelliJ Idea as Maven project

## Build and run
Open class MeasurementService and run it's "main" method.

## View Measurements
To view the measurements of user with userId = 1,
open URL in browser: http://localhost:8080/api/measurement/1
Measurements of the user will be shown in JSON format like this:
```json
[{"userId":1,"type":"COLD_WATER","value":15,"date":"2020-07-14"}]
```
Initially, the database contains single measurement of user with userId = 1

## Add Measurement
To add a measurement, send POST request via SOAP UI to URL:
http://localhost:8080/api/measurement
with Media Type: application/json
and body:
```json
{"userId":1,"type":"COLD_WATER","value":20}
```

## Exception Handling
To see exception handling, try adding measurement with not-existing userId.
Send POST request via SOAP UI to URL:
http://localhost:8080/api/measurement
with Media Type: application/json
and body: 
```json
{"userId":100,"type":"COLD_WATER","value":20}
```
The response will be as following:
```json
{"Error message":"User with id 100 does not exist"}
```
