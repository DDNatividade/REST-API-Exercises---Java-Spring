# REST API Exercises - Java Spring

This repository contains a collection of **REST API exercises using Spring Boot**, designed to practice and consolidate backend development skills. Each exercise focuses on a key concept of architecture, best practices, validations, and design patterns applied to REST APIs.

The goal is to provide hands-on learning, progressing from basic CRUD operations to advanced validation, error handling, and testing.

---

## 📂 Repository Structure

Each folder represents an independent exercise and contains:

- `src/main/java`: Source code (controllers, services, repositories, models, and DTOs)
- `src/main/resources`: Configuration files and templates if applicable
- `src/test/java`: Unit and integration tests
- `pom.xml`: Maven dependencies and configuration
- `README.md` (optional) with instructions for each exercise
- API documentation and key concepts

REST-API-Exercises/
│
├── 01-simple-crud/
│ └── Basic CRUD example using Spring Data JPA
│
├── 02-crud-validations/
│ └── CRUD with validations, error handling, and DTOs
│
├── 03-pagination-sorting/
│ └── Pagination and sorting exercise
│
├── 04-search-filter/
│ └── Advanced search and filtering
│
├── 05-exception-handling/
│ └── Global exception handling and custom responses
│
├── 06-swagger-documentation/
│ └── API documentation with Swagger/OpenAPI
│
├── 07-final-project/
│ └── Full project integrating all previous concepts
│
└── README.md (this file)



---

## ⚡ Technologies Used

- **Java 17+**
- **Spring Boot 3**
- **Spring MVC / Spring Data JPA / Hibernate**
- **Validation (jakarta.validation)**
- **H2 / MySQL / PostgreSQL** (depending on exercise)
- **Swagger / OpenAPI** for API documentation
- **JUnit 5 & Mockito** for testing
- **Maven** as dependency manager

---

## 📖 Concepts and Patterns Learned

- Layered architecture: Controller → Service → Repository
- DTOs and entity mapping
- Input validation and error handling
- Pagination and sorting with Spring Data
- Filtering and dynamic search
- API documentation with Swagger/OpenAPI
- Unit and integration testing
- REST API best practices (status codes, consistent responses)

---

## 🚀 How to Use the Exercises

1. Clone the repository:

```bash
git clone https://github.com/your-username/REST-API-Exercises.git
cd REST-API-Exercises
