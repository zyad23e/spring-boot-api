# Social Platform API

RESTful backend API for a micro-blogging platform built with Spring Boot.  
Provides user registration, authentication, and message management with relational persistence using Spring Data JPA and Hibernate.

## Features
- User registration with validation and duplicate-username handling
- User login with credential verification
- Create, read, update (PATCH), and delete messages
- Retrieve all messages or messages by a specific user
- Ownership checks and domain-level validation
- Proper HTTP status codes and idempotent DELETE behavior

## Tech Stack
- Java
- Spring Boot
- Spring Web (REST)
- Spring Data JPA / Hibernate
- H2 (in-memory database)

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


