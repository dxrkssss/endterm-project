# Music Library REST API — Endterm Project

## Project Overview

This repository contains a Spring Boot RESTful API for a Music Library system. The project demonstrates how to migrate a layered Java application into a modern backend API while applying design patterns, SOLID principles, and component principles.

The API supports CRUD operations for media items. The current media types are:

- `SONG`
- `PODCAST`

The application uses JDBC for database access and SQLite as a lightweight relational database.

---

## Learning Goals Covered

- Build a Spring Boot REST API with clean layered architecture
- Implement CRUD endpoints using proper HTTP methods
- Integrate database access using JDBC
- Apply creational design patterns (Singleton, Factory, Builder)
- Organize code using component principles (REP, CCP, CRP)
- Maintain SOLID principles in a real backend project
- Provide technical documentation, database schema, and testing evidence (Postman)

---

## Technologies

- Java 17
- Spring Boot
- Maven
- Spring Web
- JDBC
- SQLite
- Postman

---

## Project Structure

All packages are located under the root package `java.alnur.endtermprojectapi`:

src/main/java/.../ 
controller/
service/
repository/
model/
dto/
exception/
patterns/
utils/

src/main/resources/
application.properties
schema.sql

docs/
screenshots/
uml.png

---

## Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database

yaml
Копировать код

### Responsibilities

- **Controller**: HTTP layer. Receives requests and returns JSON responses.
- **Service**: Business logic layer. Validates input, coordinates operations, maps entities to DTOs.
- **Repository**: Data access layer. Executes SQL queries via JDBC and maps `ResultSet` rows to domain objects.
- **Database**: SQLite database stored in a file.