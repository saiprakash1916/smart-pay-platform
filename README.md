# 💳 SmartPay Platform

A secure and scalable Spring Boot backend application for payment processing, featuring JWT authentication, role-based access control, refresh tokens, and logout with token blacklisting.


## 🚀 Features

* 🔐 JWT Authentication (Access + Refresh Tokens)
* 👥 Role-Based Access Control (ADMIN / USER)
* 🔄 Refresh Token Mechanism
* 🚪 Secure Logout (Token Blacklisting)
* 📊 REST APIs with Swagger UI
* 🗄️ MySQL Database Integration
* 🐳 Dockerized Application

## 🧱 Tech Stack

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* MySQL
* JWT (jjwt)
* Lombok
* Swagger (OpenAPI)
* Docker

## 🔐 Authentication Flow

1. User logs in → receives Access Token + Refresh Token
2. Access Token used for API calls
3. When expired → use Refresh Token
4. Logout → token blacklisted

## 📊 API Documentation

### Swagger UI:
```aiignore
http://localhost:8080/swagger-ui/index.html
```

###  Sample APIs

#### Login
```aiignore
POST /api/users/login
```

#### User API
```aiignore
GET /api/home
```

#### Admin API
```aiignore
GET /api/users/admin/dashboard
```

#### Refresh Token
```aiignore
POST /api/auth/refresh
```

#### Logout
```aiignore
POST /api/users/logout
```


## 🐳 Docker Setup

#### Build JAR
```aiignore
mvn clean package
```

#### Build Image
```aiignore
docker build -t smartpay-app .
```

#### Run Container
```aiignore
docker run -p 8080:8080 smartpay-app
```

## ⚙️ Configuration

Update application.properties:

```aiignore
spring.datasource.url=jdbc:mysql://localhost:3306/smartpay
spring.datasource.username=root
spring.datasource.password=yourpassword
```