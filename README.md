<h1 align="center"> 
Food Delivery Platform API</h1>

*This repository contains the code for a food delivery platform API that allows users to order food from restaurants. The API is developed using the Spring Boot framework and utilizes MySQL as the database management system. It supports user authentication, CRUD operations for food items by administrators, and tracking of user orders.

* Implemented CRUD operations with data validation, MySQL database configuration, 
Swagger integration, authentication and established table relationships, authentication 
using “MD-5” algorithm

* Tech stack used: Spring Boot, Hibernate, MySQL, OOPs, Postman, Swagger, and Java
>### Prerequisites
* ![MySql](https://img.shields.io/badge/DBMS-MYSQL%205.7%20or%20Higher-red)
 * ![SpringBoot](https://img.shields.io/badge/Framework-SpringBoot-green)


* ![Java](https://img.shields.io/badge/Language-Java%208%20or%20higher-yellow)

>## Data flow
 The application is built using the SpringBoot framework and consists of four layers: DTO, model, service, and repository.-

* **DTO** -The DTO layer consists of classes that are used to transfer data between different layers of the application
* **Controller** - The controller layer handles the HTTP requests, translates the JSON parameter to object, and authenticates the request and transfer it to the business (service) layer. In short, it consists of views i.e., frontend part.
* **Service** -The business layer handles all the business logic. It consists of service classes and uses services provided by data access layers.
* **Repository** - This layer mainatains the h2-database thing on which CRUD operations are performed
* **Model** - This layer consists basically the class level things- the various classes required for the project and these classes consists the attributes to be stored.

>## API Documentation
The API endpoints will be available at swagger documentation

>#### Database Design
The API uses a MySQL database to store its data. The database design includes the following tables:

user: Stores user information such as username, password, and order history.
food_item: Contains information about food items, including their name, description, and price.
<br>
admin_user : it stores the information about admin
<br>
orders : its stores the order detais

>## Project Summary
The Food Delivery Platform API is a Spring Boot-based application that provides endpoints for users to order food items from restaurants. It ensures secure authentication for users and allows administrators to manage food items through CRUD operations. The API communicates with a MySQL database to store and retrieve user and food item data.
