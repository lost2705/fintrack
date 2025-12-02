# 📊 FinTrack

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)]()

**FinTrack** is a personal finance tracker with a web interface and Telegram bot (in progress).  
It helps users track their income and expenses, organize them by category, and get better control over their budget.

---

## ✨ Features

✅ Add and view personal transactions  
✅ Filter by category and date  
✅ Validation with meaningful error messages  
✅ Data transfer via DTOs  
✅ REST API for expense management  
✅ MapStruct-based mapping logic  
✅ Category management  
✅ Flyway-based DB versioning  
✅ Basic Telegram bot integration (echo + manual registration via TelegramBots API) is implemented; command‑based finance management is in progress  
⬜ Monthly summaries and analytics (planned)  
⬜ PostgreSQL support (planned)

---

## 🛠 Tech Stack

| Layer        | Technology              |
|--------------|--------------------------|
| **Language** | Java 21                 |
| **Framework**| Spring Boot 3           |
| **Database** | H2 (in-memory), PostgreSQL (planned) |
| **Persistence** | Spring Data JPA     |
| **Validation** | Jakarta Validation    |
| **Migration**  | Flyway                |
| **Mapping**    | MapStruct             |
| **Build Tool** | Maven                 |

---

## 🚀 Getting Started

```bash
git clone https://github.com/lost2705/fintrack.git
cd fintrack
./mvnw spring-boot:run
```

> By default, FinTrack runs on an H2 in-memory database.  
> To switch to PostgreSQL, configure your `application.yml`.

---

## 🧪 Sample Data

Flyway migration automatically creates:

- 🗂 Default categories: Food, Transport, Entertainment, etc.
- 🔄 Example transactions for testing

---

## 📌 Roadmap

- [x] Basic CRUD for transactions
- [x] DTO mapping with MapStruct
- [x] Validation and exception handling
- [x] Swagger UI docs
- [x] Telegram bot commands and setup
- [ ] User authentication (optional)
- [ ] Budgeting goals and monthly reports

## 👤 Author

**[lost2705](https://github.com/lost2705)**  
Backend Developer • Java Enthusiast • Learning through practice
