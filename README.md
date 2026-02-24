Spring Boot RESTful API – Practical Assignment


Student Name: Louis De Gonzague
Branch Name: restFull_api_StudentId

Project Overview

This assignment consists of multiple independent Spring Boot RESTful API projects developed using Spring Initializr with only the Spring Web dependency.

Each question is implemented as a separate Spring Boot project following the required structure:

Two main packages:

controller

model

Each question has its own sub-package inside controller and model.

All APIs were tested using Postman.

Proper HTTP methods and status codes were used.

 General Project Structure
 

Each project follows this structure:

Main Spring Boot Application class

controller package (contains REST Controllers)

model package (contains entity/model classes)

No service layer or repository layer was used as instructed.

All data is stored in memory using a List collection.

 Question 1: Library Book Management API

 Description

This API manages books in a library system.
It allows users to:

View all books

View a book by ID

Search books by title

Add a new book

Delete a book

Three sample books were initialized when the application starts.

 Endpoints Summary

Get all books

Get book by ID

Search book by title

Add new book

Delete book by ID

 HTTP Status Codes Used

200 OK – When request is successful

201 CREATED – When a book is added

204 NO CONTENT – When a book is deleted

404 NOT FOUND – When book does not exist

 Question 2: Student Registration API

 Description

This API manages student information.

It allows:

Viewing all students

Viewing student by ID

Filtering students by major

Filtering students by GPA

Registering a new student

Updating student information

Five sample students were created with different majors and GPA values.

 Testing Scenarios Completed

Filtering students by Computer Science major

Filtering students with GPA greater than or equal to 3.5

Updating student information successfully

 Status Codes Used

200 OK

201 CREATED

404 NOT FOUND

 Question 3: Restaurant Menu API
 Description

This API manages a restaurant menu system.

It allows:

Viewing all menu items

Viewing item by ID

Filtering by category

Viewing available items

Searching by name

Adding new menu item

Updating item availability

Deleting item

At least 8 menu items were created across categories:

Appetizer

Main Course

Dessert

Beverage

 Special Feature

Availability can be toggled using a dedicated endpoint.

Question 4: E-Commerce Product API
Description

This API manages an e-commerce product catalog.

It includes:

Viewing all products

Pagination support

Viewing product by ID

Filtering by category

Filtering by brand

Searching by keyword

Filtering by price range

Viewing products in stock

Adding product

Updating product

Updating stock quantity

Deleting product

Ten sample products were created with different:

Categories

Brands

Prices

Stock quantities

 Advanced Features Implemented

Pagination using page and limit parameters

Keyword search in product name and description

Price range filtering

Stock update using PATCH method

 Question 5: Task Management API
 Description

This API manages tasks (To-Do list).

It allows:

Viewing all tasks

Viewing task by ID

Filtering by completion status

Filtering by priority

Creating task

Updating task

Marking task as completed

Deleting task

Tasks include:

Title

Description

Completion status

Priority (LOW, MEDIUM, HIGH)

Due date (YYYY-MM-DD format)

Bonus: User Profile API
Description

This API manages user profiles.

It includes:

Full CRUD operations

Search by username

Search by country

Search by age range

Activate and deactivate user profiles

Custom response wrapper

Special Feature

All responses are wrapped inside a custom response object that contains:

Success status

Message

Data object

This ensures consistent API responses.

 Testing

All APIs were tested using Postman.

Testing includes:

GET requests

POST requests

PUT requests

PATCH requests

DELETE requests

Error scenarios (invalid ID)

Filter and search functionality

Screenshots / Postman collection included as proof of testing.
