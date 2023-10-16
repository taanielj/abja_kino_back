# Abja Kino Backend Project

This repository contains the backend for the Abja Kino cinema application which includes a Spring Boot application and relies on a PostgreSQL database for data persistence.

# Project Structure

## Package Structure
* Business - contains the business logic
    * Genre
    * Movie
    * Room
    * Seance
    * Ticket
        * Type
    * User
* Infrastructure - infrastructure and related code
    * Exception
* Security - security related code

# Project setup

## Getting Started
Development: Install JDK 17 and Your favourite IDE (IntelliJ IDEA, VSCode, Eclipse, etc.)
Your IDE should detect the project as a Gradle project and automatically download the dependencies.
Build: `gradle build`

Deployment: Go to http://www.github.com/taanielj/abja_kino_deployment for deployment instructions.