# ZSocial Backend

> [!NOTE]  
> ZSocial is a vibrant social networking platform designed specifically for the youth. It provides a dynamic space for users to connect, share their thoughts, and interact with each other through various engaging features.

This repository contains the backend service built with Spring Boot, managing core platform interactions, user data, media, and real-time chat functionality.

## Features

- **Blogging & Posts**: Create, share, and explore rich blog posts from other users.
- **Interactions**: Write comments on posts and engage dynamically with the community.
- **Real-time Messaging**: Chat with friends seamlessly through direct messaging and conversations.
- **User Profiles**: View and manage personal profiles securely.
- **Follow System**: Follow friends and creators to stay updated with their latest activities and posts.
- **Media Management**: Upload and manage media content within posts and chats.

## Architecture & Technology Stack

The project follows a clean, feature-driven architecture separating modules roughly into `chat`, `media`, `posts`, `users`, and `common` concerns.

- **Framework**: Spring Boot 3.5.x using Java 21
- **Database**: PostgreSQL (JPA/Hibernate)
- **Authentication**: JWT, Spring Security, OAuth2 (Google Login)
- **Real-time Services**: WebSocket, RabbitMQ
- **Object Storage**: Google Cloud Storage (GCS)
- **API Documentation**: Swagger/OpenAPI 3

## Prerequisites

Ensure you have the following installed to run the backend locally:
- JDK 21 (or higher)
- PostgreSQL
- RabbitMQ
- A Google Cloud Service Account JSON key (for GCS Object Storage integration)

## Getting Started

### 1. Environment Configuration

Create a `.env` file in the root directory. You can use this template based on local development requirements:

```env
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5438/zsocial?stringtype=unspecified
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password

# JWT Authentication
SECRET_JWT=your_base64_encoded_secret_key_needs_to_be_long_enough
JWT_EXPIRATION=604800

# Google OAuth & reCAPTCHA
GOOGLE_CLIENT_ID=your_client_id.apps.googleusercontent.com
GOOGLE_CLIENT_SECRET=your_client_secret
GOOGLE_REDIRECT_URI=http://localhost:5173/callback
RECAPTCHA_SECRET=your_recaptcha_secret

# Google Cloud Storage (Media)
GCS_CREDENTIALS_PATH=your_gcs-key.json
GCS_PROJECT_ID=your_gcp_project_id
GCS_BUCKET_NAME=your_gcp_bucket_name

# Messaging (RabbitMQ)
RABBITMQ_HOST=localhost
RABBITMQ_USERNAME=your_rabbitmq_user
RABBITMQ_PASSWORD=your_rabbitmq_password

# Application Settings
SWAGGER_SERVER_URL=http://localhost:8080
ALLOWED_ORIGINS=http://localhost:5173
```

> [!IMPORTANT]
> Make sure to place your Google Cloud Storage credentials file inside the project (e.g., `your_gcs-key.json`) locally, and correctly map its location in the `.env` via `GCS_CREDENTIALS_PATH`.

### 2. Installation and Execution

Clone the repository and build the project using the included Maven wrapper:

```sh
# Clone repository
git clone <repository-url>
cd zsocialbackend

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

By default, the application will boot and listen on port `8080`.

## API Documentation

Once the server is running, you can access the interactive API documentation and test endpoints natively:

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI Schema**: `http://localhost:8080/v3/api-docs`
