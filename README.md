Learning Management System (LMS)

Overview
- This is a backend-based Learning Management System built using Spring Boot.
- It provides secure and scalable REST APIs for managing users, courses, and enrollments.
- Authentication and authorization are implemented using JWT and Spring Security.
- The system supports role-based access control for Admin, Instructor, and Student.

Features
- User registration and authentication
- JWT-based stateless security
- Role-based access control (Admin, Instructor, Student)
- Course creation, update, deletion, and retrieval
- Student enrollment in courses
- Secure RESTful APIs
- Integration with Spring Data JPA
- API documentation using Swagger/OpenAPI

Tech Stack
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- JSON Web Token (JWT)
- H2 Database
- Maven
- Swagger / OpenAPI

Architecture
- Controller Layer: Handles HTTP requests and responses
- Service Layer: Contains business logic
- Repository Layer: Handles database operations
- Security Layer: Manages authentication and authorization
- Model Layer: Defines database entities
- DTO Layer: Transfers data between layers

Project Structure
src/main/java/com/astra/learning/management/system
- role
- authFilter
- config
- controller
- dto
- model
- principal
- repository
- service

Security Implementation
- JWT token-based authentication
- Password encryption using BCrypt
- Role-based endpoint protection using Spring Security
- Stateless session management

Running the Project

Prerequisites
- Java 20+
- Maven

Steps
- Clone the repository:
  git clone https://github.com/abdulsami458/Learning-Management-System-LMS-.git

- Navigate to project directory:
  cd Learning-Management-System-LMS-

- Build the project:
  mvn clean install

- Run the application:
  mvn spring-boot:run

- Access Swagger UI:
  http://localhost:8080/swagger-ui.html

API Endpoints 

Authentication
- POST /register
- POST /login

Courses
- POST /createCourses
- GET /getCourses
- PUT /courses/{id}
- DELETE /courses/{id}

Enrollments
- POST /enroll
- GET /my-courses

Author
- Abdul Sami
- GitHub: github.com/abdulsami458
