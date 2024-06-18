# Event Planner Backend System

This repository contains the backend system for an Event Planner application, designed to facilitate event management, attendee registration, and task tracking. Built with Spring Boot, this backend leverages the robustness of Spring's ecosystem, offering a RESTful API, integration with PostgreSQL for data persistence, and Swagger for API documentation.

## Features

- **Event Management**: Create, update, delete, and list events with details such as name, description, location, date and time, and total seats.
- **Attendee Registration**: Manage attendee registrations, including attendee information and registration status.
- **Task Management**: Assign tasks related to events, including task name, description, and associated costs.
- **Cost Management**: Track costs with detailed cost sources and categories for financial management of events.
- **API Documentation**: Utilize Swagger UI for easy navigation and interaction with the API's endpoints.

## Technology Stack

- **Spring Boot** (3.2.0): Framework for creating stand-alone, production-grade Spring based applications.
- **Spring Data JPA**: For data persistence and CRUD operations on the database.
- **PostgreSQL**: Open source relational database.
- **Spring Security**: For authentication and authorization.
- **Lombok**: To reduce boilerplate code.
- **Java JWT**: For JSON Web Token (JWT) generation and validation.

## Prerequisites

- JDK 17
- Maven
- PostgreSQL

## Setup & Installation

1. **Clone the repository**

    ```bash
    git clone https://github.com/<your-username>/event_planner_backend.git
    cd event_planner_backend
    ```

2. **Configure PostgreSQL**

    Create a PostgreSQL database named `event_planner`.

    ```sql
    CREATE DATABASE event_planner;
    ```

    Update `application.yml` with your PostgreSQL username and password.

    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/event_planner
        username: <your_username>
        password: <your_password>
    ```

3. **Build the Application**

    Use Maven to build the application.

    ```bash
    mvn clean install
    ```

4. **Run the Application**

    Start the Spring Boot application.

    ```bash
    mvn spring-boot:run
    ```

    The application will start on `http://localhost:8080`.

## Accessing Swagger UI

Navigate to `http://localhost:8080/swagger-ui.html` to view and interact with the API documentation.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any bugs, features, or improvements.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
