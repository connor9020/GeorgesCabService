# George's Cab Service

George's Cab Service is a microservices-based application that provides functionalities for booking cabs, calculating fares, and managing user sessions. The application is built using Spring Boot and follows a microservices architecture.

## Table of Contents

- [Introduction](#introduction)
- [Architecture](#architecture)
- [Microservices](#microservices)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Technologies Used](#technologies-used)


## Introduction

George's Cab Service allows users to book cabs, view routes, and calculate fares based on their journey. It is designed as a microservices-based system, with each microservice handling a specific domain within the application. The main goal for this project was to work on implementing an external API while using microservices basics. CRUD operations have not been implemented yet on the booking system after the user selects a route. 

## Architecture

The application consists of multiple microservices:

- **Cab-Booking Service**: Handles the booking of cabs by customers.
- **Cab-Fare Service**: Calculates the fare based on the distance traveled.
- **Login Service**: Manages user authentication and sessions.
- **Map Service**: Integrates with external mapping APIs to provide route information.

Each service is decoupled from the others and communicates through REST APIs.

## Microservices

### 1. Cab-Booking Service
This service allows users to book cabs and manage their bookings.

### 2. Cab-Fare Service
This service calculates fares based on the distance between the starting and ending points of a journey.

### 3. Login Service
This service handles user authentication, including sign-in and sign-up functionalities.

### 4. Map Service
This service provides route information by integrating with external mapping APIs such as OpenRouteService.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6 or later
- MySQL 8.0 or later
- Docker (optional, for containerized deployment)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/GeorgesCabService.git
    cd GeorgesCabService
    ```

2. Set up the MySQL database:

    - Create a database named `georgescabservice`.
    - Update the database configurations in each microservice's `application.properties` file.

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

## Running the Application

You can run the microservices individually through your IDE of choice

## API Endpoints

Cab-Booking Service
-POST /bookings: Create a new booking.
-GET /bookings/{id}: Retrieve a booking by its ID. (Storage of bookings not implemented yet)
-GET /bookings: Retrieve all bookings. (Storage of bookings not implemented yet)
-POST /bookings/confirm-booking: Confirm a booking.
Cab-Fare Service
-GET /fares: Retrieve all fare rates.
Login Service
-POST /login/signin: Sign in a user.
-POST /login/signup: Sign up a new user.
Map Service
-GET /map: Show the map view.
-GET /route: Calculate the route between two locations.

## Testing was completed through the IDE using Junit

## Technologies Used
-Spring Boot: Framework for building microservices.
-Spring Data JPA: For database operations.
-MySQL: Relational database for data storage.
-Thymeleaf: Server-side template engine for rendering views.
-JUnit & Mockito: For testing.
-Docker: For containerization.
-OpenRouteService API: For routing and map services.

