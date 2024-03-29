# Welcome to Skiply Fees Management

## Overview
Skiply Fees Management is a microservice created with latest version of Springboot which helps to manage fee collection for the Students. 
With the help of this we can do the fees collection and retrieval of fees receipts for a specific student or list of students.

This microservice provides methods for collecting fees, retrieving fees receipts by ID or all receipts,and validating fee collection requests for the Students.  

The StudentServiceClient class in this project is responsible for interacting with the Student Service API to retrieve student details.
The generated response is sent to the FeesCollectionService class which collects the fees and generates the receipts.

## DB configuration
This project uses an H2 in-memory database. 
The database schema is automatically created based on the Spring DATA JPA entity classes and data is automatically initialized as per the data.sql under resources folder. 
You can find the database configuration in the application.properties file.

## Setup instructions
To run this project, make sure you have the following prerequisites installed on your machine:

1. Java Development Kit (JDK) 21.0.2   
2. Install IntelliJ IDEA(latest version)   
3. Install Postman(latest version)  
4. git clone https://github.com/onimakas/SkiplyFeesApplication.git or download it into zip and unzip it to your local.   
5. Open project in IntelliJ IDEA.
6. Enable Lombok Annotation processing in Intellij IDEA while building the application.
7. Run the application in Intellij.
8. The application should now be up and running on http://localhost:9000/api/v1/fees  

## API documentation
The Open API documentation links are as follows: 

Swagger link - http://localhost:9000/swagger-ui/index.html#/

Once the application is running, you can access the following endpoints:
1. POST Request for collecting fees (http://localhost:9000/api/v1/fees/collect) - Create a Transaction / Collect fees for the student with student details and transaction details.   
2. GET Request (http://localhost:9000/api/v1/fees/receipts/{id}) - Retrieves fee details for particular transaction with specific to the student Id.  
3. GET Request (http://localhost:9000/api/v1/fees/receipts) - Retrieves all the fee details for all the students.
