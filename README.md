# 🛒 E-Commerce API

A RESTful E-Commerce Backend API built with **Java** and **Spring Boot**.

---

## 🚀 Features

- User registration and management
- JWT authentication
- BCrypt password hashing
- Product and category management
- Shopping cart management
- Stock validation
- Order creation and management
- Input validation
- Global exception handling
- PostgreSQL database

---

## 🛠️ Tech Stack

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **JWT**
- **PostgreSQL**
- **Maven**
- **Postman**
- **Git & GitHub**
- **Render**

---

## 🏗️ Architecture

The project follows a layered architecture:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
```

### Architecture Layers

- **Controller** – Handles HTTP requests and responses
- **Service** – Contains business logic
- **Repository** – Handles database operations
- **PostgreSQL** – Stores application data

---

## 🔐 Authentication

The API uses **JWT (JSON Web Token)** for authentication.

### Login

```http
POST /auth/login
```

Protected endpoints require:

```http
Authorization: Bearer <token>
```

Passwords are securely stored using **BCrypt hashing**.

---

## 📦 Main Endpoints

### 👤 Users

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/user` | Create a new user |
| `GET` | `/user` | Get all users |
| `GET` | `/user/{id}` | Get user by ID |
| `PUT` | `/user/{id}` | Update user |
| `DELETE` | `/user/{id}` | Delete user |

### 🛍️ Products

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/products` | Get all products |
| `GET` | `/products/{id}` | Get product by ID |
| `POST` | `/products` | Create a product |
| `PUT` | `/products/{id}` | Update a product |
| `DELETE` | `/products/{id}` | Delete a product |

### 🏷️ Categories

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/category` | Get all categories |
| `GET` | `/category/{id}` | Get category by ID |
| `POST` | `/category` | Create a category |
| `PUT` | `/category/{id}` | Update a category |
| `DELETE` | `/category/{id}` | Delete a category |

### 🛒 Cart

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/cart/{userId}` | Get user's cart |
| `POST` | `/cart/{cartId}/products/{productId}` | Add product to cart |
| `PUT` | `/cart/items/{itemId}` | Update cart item |
| `DELETE` | `/cart/items/{itemId}` | Remove cart item |
| `DELETE` | `/cart/{cartId}` | Delete cart |

### 📋 Orders

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/order/user/{userId}` | Get user's orders |
| `GET` | `/order/{id}` | Get order by ID |
| `POST` | `/order` | Create a new order |
| `PUT` | `/order/{id}/status` | Update order status |
| `PUT` | `/order/{id}/cancel` | Cancel an order |

---

## 🗄️ Database

PostgreSQL is used to store:

- Users
- Products
- Categories
- Carts
- Cart Items
- Orders
- Order Items

---

## 🧪 Testing

The API was tested using **Postman**, including:

- Authentication
- CRUD operations
- Cart and order flows
- Stock validation
- Input validation
- Error handling
- Invalid JWT tokens

---

## 🌐 Deployment

The backend is deployed on **Render** and uses environment variables for sensitive configuration.

---

## 📱 Next Step

An **iOS application built with SwiftUI** will consume this REST API.

---

## 👩‍💻 Author

**Rawan Amr**
