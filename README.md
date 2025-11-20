# Blog Platform - Spring Boot Application

A complete blog platform built with Spring Boot, featuring user authentication, post management, categories, and tags.

## Features

- ✅ User authentication with JWT
- ✅ Post management (CRUD)
- ✅ Categories and tags system
- ✅ Spring Security configuration
- ✅ PostgreSQL database with Docker
- ✅ RESTful API endpoints

## Technologies

- Spring Boot 3.4.0
- Spring Security
- JWT Authentication
- PostgreSQL
- Docker
- MapStruct
- Lombok

## Setup

1. Run database: `docker-compose up`
2. Start application: `./mvnw spring-boot:run`
3. Access API: http://localhost:8080

## API Documentation

Endpoints available for:
- Authentication (/api/v1/auth)
- Posts (/api/v1/posts) 
- Categories (/api/v1/categories)
- Tags (/api/v1/tags)
