# Jivan-UIAssignment
Assignment 


Reward Point System  

Project Description
A Spring Boot application for managing customer transactions and calculating reward points based on spending patterns.  

Features
•	Customer Management
o	Registration & Login
o	Email uniqueness validation
•	Transaction Tracking
o	Add/Update/Delete transactions
o	View transaction history
o	Reward Points Calculation
o	Real-time point calculation (50-100 = 1pt, >100 = 2pts per dollar)
o	3-month reward summary 
•	Exception Handling
o	Custom exceptions (`ResourceNotFound`, `DuplicateEmail`, etc.)
o	Global exception handler with consistent JSON responses

Tech Stack
Backend	Spring Boot 3.4.3
Database	PostgreSQL 9.3
ORM	Spring Data JPA + Hibernate
API Docs	OpenAPI 3.0 (Swagger UI)
Utilities	Lombok (Boilerplate reduction)

ModelMapper (DTO-Entity conversion)


API Endpoints

Customer API
Endpoint	Method	Description
/customers/register	POST	Register new customer
/customers/login	POST	Customer login


Transaction API
Endpoint	Method	Description
/transactions/add	POST	Add new transaction
/transactions/customer/{customerId}	GET	Get all transactions for customer
/transactions/update/{transactionId}	PUT	Update transaction
/transactions/delete/{transactionId}	DELETE	Delete transaction

Rewards API
Endpoint	Method	Description
/rewards/customer/{customerId}	GET	Get all reward records
/rewards/last-three-months/{customerId}	GET	Get formatted 3-month reward summary


Exception Handling

Exception	HTTP Status	Trigger Condition
ResourceNotFoundException	404	Entity not found
DuplicateEmailException	409	Email already registered
InvalidInputException	400	Invalid request data

