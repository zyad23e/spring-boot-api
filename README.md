# Social Platform API

RESTful backend API for a micro-blogging platform built with Spring Boot.  
Provides user registration, authentication, and message management with relational persistence using Spring Data JPA and Hibernate.

## Features
- User account creation and authentication logic
- Create, update, delete, and retrieve messages
- Retrieve all messages posted by a specific user
- RESTful API design with clear separation of concerns
- Service and repository layers for business logic and persistence
- Basic validation and error handling via HTTP status codes

## Tech Stack
- Java
- Spring Boot
- Spring Web (REST)
- Spring Data JPA / Hibernate
- MySQL
- Maven

## API Endpoints
- `POST /register`
- `POST /login`
- `POST /messages`
- `GET /messages`
- `GET /messages/{messageId}`
- `PATCH /messages/{messageId}`
- `DELETE /messages/{messageId}`
- `GET /accounts/{accountId}/messages`

## Running Locally
1. Clone the repository
2. Run the application using IntelliJ or:
   ```bash
   mvn spring-boot:run


