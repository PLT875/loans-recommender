# Loans Recommender

Example application.

Assumptions:
* Multiple lenders can be used to fulfil a loan request
* For others please see code comments

## Pre-requisites
* Maven 3.3.9 or above
* Java 8

## Build, test and package
```
mvn package
```

## Running the application, e.g.
```
java -jar target/loans-recommender-1.0.jar src/test/resources/market-data.csv 1000
```