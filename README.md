# 💰 Expense Tracker System

A secure and efficient RESTful API for managing personal expenses, built with **Java 21**, **Spring Boot 4.0.2**, and **PostgreSQL**. Features JWT-based authentication, category-based expense tracking, and comprehensive date-range queries for financial data management.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 🚀 Features

### 🔐 Authentication & Security
- **JWT-based Authentication** - Secure token-based user authentication
- **Spring Security Integration** - Industry-standard security implementation
- **Password Encryption** - Secure password storage with BCrypt
- **User Isolation** - Each user can only access their own expenses

### 💸 Expense Management
- **CRUD Operations** - Create, Read, Update, and Delete expenses
- **Category-based Tracking** - Organize expenses into 7 categories:
  - 🧳 Travel
  - 🛍️ Shopping
  - 🏥 Medical
  - 🍔 Food
  - 📚 Education
  - 🏠 Housing
  - 🎬 Entertainment
- **Date-Range Queries** - Filter expenses by date ranges
- **Soft Delete** - Expenses are marked as deleted, not permanently removed
- **Automatic Timestamps** - Track creation and update times

### 🛠️ Technical Features
- **RESTful API Design** - Clean and intuitive API endpoints
- **DTO Pattern** - Separation between entities and API responses
- **MapStruct Integration** - Efficient object mapping
- **JPA/Hibernate** - Powerful ORM for database operations
- **Lombok** - Reduced boilerplate code

---

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher installed
- **Maven 3.8+** for dependency management
- **PostgreSQL 14+** database server
- **Git** for version control

---

## 🔧 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/krisharma955/Expense-Tracker-System.git
cd Expense-Tracker-System
```

### 2. Configure Database
Create a PostgreSQL database:
```sql
CREATE DATABASE expense_tracker;
```

### 3. Configure Application Properties
Create or update `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: ExpenseTracker
  
  datasource:
    url: jdbc:postgresql://localhost:5432/expense_tracker
    username: your_db_username
    password: your_db_password
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true

server:
  port: 8080

jwt:
  secret: your-secret-key-here-make-it-long-and-secure
  expiration: 86400000  # 24 hours in milliseconds
```

### 4. Build the Project
```bash
./mvnw clean install
```

### 5. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

---

## 📚 API Documentation

### Authentication Endpoints

#### 1. User Signup
```http
POST /api/auth/signup
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "email": "john@example.com"
}
```

#### 2. User Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "email": "john@example.com"
}
```

---

### Expense Endpoints
> **Note:** All expense endpoints require JWT token in the Authorization header:
> ```
> Authorization: Bearer <your-jwt-token>
> ```

#### 1. Create Expense
```http
POST /api/expenses
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Grocery Shopping",
  "amount": "150.50",
  "category": "FOOD"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Grocery Shopping",
  "amount": "150.50",
  "category": "FOOD",
  "createdAt": "2026-01-29T10:30:00Z",
  "updatedAt": "2026-01-29T10:30:00Z"
}
```

#### 2. Get All Expenses
```http
GET /api/expenses
Authorization: Bearer <token>
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Grocery Shopping",
    "amount": "150.50",
    "category": "FOOD"
  },
  {
    "id": 2,
    "name": "Movie Tickets",
    "amount": "25.00",
    "category": "ENTERTAINMENT"
  }
]
```

#### 3. Get Expense by ID
```http
GET /api/expenses/{expenseId}
Authorization: Bearer <token>
```

#### 4. Update Expense
```http
PATCH /api/expenses/{expenseId}
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Updated Expense Name",
  "amount": "200.00",
  "category": "SHOPPING"
}
```

#### 5. Delete Expense
```http
DELETE /api/expenses/{expenseId}
Authorization: Bearer <token>
```

**Response:** `204 No Content`

---

### Category-Based Queries

Get expenses filtered by category:

```http
GET /api/expenses/travel          # Travel expenses
GET /api/expenses/shopping        # Shopping expenses
GET /api/expenses/medical         # Medical expenses
GET /api/expenses/food            # Food expenses
GET /api/expenses/education       # Education expenses
GET /api/expenses/housing         # Housing expenses
GET /api/expenses/entertainment   # Entertainment expenses
```

---

### Date-Range Queries

#### Get Expenses After a Date
```http
POST /api/expenses/after
Authorization: Bearer <token>
Content-Type: application/json

{
  "date": "2026-01-01"
}
```

#### Get Expenses Between Dates
```http
POST /api/expenses/between
Authorization: Bearer <token>
Content-Type: application/json

{
  "startDate": "2026-01-01",
  "endDate": "2026-01-31"
}
```

---

## 🗂️ Project Structure

```
src/main/java/com/K955/ExpenseTracker/
├── Controllers/           # REST API endpoints
│   ├── AuthController.java
│   ├── ExpenseController.java
│   ├── CategortyExpenseController.java
│   └── UserController.java
├── DTOs/                  # Data Transfer Objects
│   ├── Auth/
│   ├── Expense/
│   └── User/
├── Entity/                # JPA Entities
│   ├── Expense.java
│   └── User.java
├── Enums/                 # Enumerations
│   └── Expense/
│       └── Category.java
├── Errors/                # Exception handling
├── Mapper/                # MapStruct mappers
├── Repository/            # JPA Repositories
├── Security/              # Security configuration
│   ├── JwtAuthFilter.java
│   ├── JwtService.java
│   └── WebSecurityConfig.java
└── Service/               # Business logic
    ├── Impl/
    ├── AuthService.java
    ├── ExpenseService.java
    └── UserService.java
```

---

## 🛡️ Security

This application implements multiple security layers:

1. **JWT Authentication** - Stateless authentication using JSON Web Tokens
2. **Password Encryption** - BCrypt hashing for secure password storage
3. **Spring Security** - Comprehensive security framework
4. **User Isolation** - Users can only access their own data
5. **Soft Delete** - Data is never permanently deleted, maintaining audit trails

---

## 🔑 Environment Variables

For production deployment, use environment variables instead of hardcoding sensitive data:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/expense_tracker
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export JWT_SECRET=your-super-secret-key
export JWT_EXPIRATION=86400000
```

Update `application.yaml`:
```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}
```

---

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

---

## 📦 Dependencies

### Core Dependencies
- **Spring Boot Starter Web MVC** - RESTful web services
- **Spring Boot Starter Data JPA** - Database operations
- **Spring Boot Starter Security** - Authentication & authorization
- **PostgreSQL Driver** - Database connectivity

### Utility Libraries
- **Lombok** - Reduce boilerplate code
- **MapStruct** - Object mapping
- **JJWT** - JWT token generation and validation

### Testing
- **Spring Boot Starter Test** - Testing framework
- **Spring Security Test** - Security testing utilities

---

## 🚀 Deployment

### Docker Deployment (Recommended)

Create a `Dockerfile`:
```dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
./mvnw clean package
docker build -t expense-tracker .
docker run -p 8080:8080 expense-tracker
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 Future Enhancements

- [ ] Add expense analytics and reports
- [ ] Implement budget tracking and alerts
- [ ] Add recurring expense support
- [ ] Export functionality (CSV, PDF)
- [ ] Multi-currency support
- [ ] Expense sharing between users
- [ ] Mobile app integration
- [ ] Email notifications
- [ ] Data visualization dashboard

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Krish Sharma**
- GitHub: [@krisharma955](https://github.com/krisharma955)
- Email: krishsharma9005@gmail.com

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community for the robust database
- All contributors and supporters of this project

---

## 📞 Support

If you encounter any issues or have questions:
1. Check the [Issues](https://github.com/krisharma955/Expense-Tracker-System/issues) page
2. Create a new issue with detailed information
3. Contact the maintainer

---

**⭐ If you find this project useful, please consider giving it a star!**
