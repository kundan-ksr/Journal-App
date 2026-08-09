# My Journal Application 📝

A RESTful web application built with Spring Boot, Spring Security, and MongoDB that enables users to manage personal journal entries securely.

---

## 🚀 Features

- **User Authentication & Authorization:** Implemented security using Spring Security.
- **Journal Entry Management:** Full CRUD operations for managing journal entries linked to specific users.
- **Database Integration:** Persistence handling with MongoDB (Spring Data MongoDB).
- **Environment Configuration:** Secure credential management using `.env` files.

---

## 🛠️ Tech Stack

- **Backend Framework:** Java, Spring Boot
- **Security:** Spring Security
- **Database:** MongoDB
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA

---

## ⚙️ Getting Started

### Prerequisites

Ensure you have the following installed:
- JDK 17 or higher
- Apache Maven
- MongoDB (Local or MongoDB Atlas account)

---

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/kundan-ksr/myJournalApp.git
   cd myJournalApp

2. **Configure Environment Variables:**
Create a .env file in the root directory (same level as pom.xml):
    ```bash
    SPRING_DATA_MONGODB_URI=mongodb+srv://<username>:<password>@cluster.mongodb.net/journaldb
    PORT=8080

3. **Build the Project:**
    ```bash
    mvn clean compile

4. **Run the Application:**
    ```bash
    mvn spring-boot:run
