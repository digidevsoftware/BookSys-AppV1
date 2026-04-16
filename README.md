# Shift Management System

A beginner-friendly Version 1 Spring Boot web app for managing employees, assigning shifts, and viewing schedules.

## Tech stack
- Java 21
- Spring Boot 3
- Thymeleaf
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven Wrapper

## Features in this Version 1 starter
- Login page with seeded admin user
- Dashboard with employee and shift counts
- Employee CRUD
- Shift CRUD
- Shift schedule view
- Validation for key form fields
- PostgreSQL configuration placeholders

## Default seeded login
A default admin user is seeded from `src/main/resources/application.properties` for local development.

For public safety, the exact default password is not documented here.
Please review and change the seeded credentials before using the application beyond local testing.

## Database setup
Create a PostgreSQL database named:

`shift_management_system`

Then update the database settings in:

`src/main/resources/application.properties`

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/shift_management_system
spring.datasource.username=postgres
spring.datasource.password=your_password
```

## Running the application
Use the Maven wrapper included in the project:

```bash
./mvnw spring-boot:run
```

Or run tests with:

```bash
./mvnw test
```

Then open:

`http://localhost:8080`

## tmux cheat sheet
For day-to-day starting, stopping, and reconnecting to the app in tmux, see:

`TMUX_CHEATSHEET.md`

## Project structure
```text
src/main/java/digidevsoftware/shiftmanagementsystem
├── config
├── controller
├── model
├── repository
└── service
```

## Next recommended improvements
- add employee search/filtering
- add better exception handling
- move form models to DTOs
- add employee self-service in a later version
