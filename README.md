Welcome to Skiply Fees Management

Skiply Fees Management is a microservice created with latest version of Springboot which helps to manage fee collection for the Students. 
With the help of this we can do Service class that handles the collection of fees and retrieval of fees receipts.

This microservice provides methods for collecting fees, retrieving fees receipts by ID or all receipts,and validating fee collection requests for the Students.
The StudentServiceClient class in this project is responsible for interacting with the Student Service API to retrieve student details.
The generated response is sent to the FeescollectionService class which collects the fees and generates the receipts.


This project uses an H2 in-memory database. 
The database schema is automatically created based on the entity classes and data is automatically initialized as per the data.sql under resources folder. 
You can find the database configuration in the application.properties file.

To run this project, make sure you have the following prerequisites installed on your machine:

1.Java Development Kit (JDK) 21.0.2 
2.Install IntelliJ IDEA(latest version) 
3.Install Postman. 
4.git clone https://github.com/onimakas/SkiplyFeesApplication.git or download it into zip and unzip it to your local. 
5.Open project in IntelliJ IDEA and run locally. 
6.The application should now be up and running on http://localhost:9000/api/v1/fees

The Open API documentation links are as follows: Swagger links - http://localhost:9000/swagger-ui/index.html#/

Once the application is running, you can access the following endpoints:

POST Request for collecting fees (http://localhost:9000/api/v1/fees/collect) - Create a Transaction / Collect fees for the student with student details and transaction details . 
GET Request (http://localhost:9000/api/v1/fees/receipts/{id}) - Retrieves fee details for particular transaction with specific to the student Id. 
GET Request (http://localhost:9000/api/v1/fees/receipts) - Retrieves all the fee details for all the students.
s/{id}) - Deletes a student by ID.
