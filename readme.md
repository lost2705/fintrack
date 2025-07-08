# 📊 FinTrack &nbsp;![Java](https://img.shields.io/badge/Java-21-blue?logo=java) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=spring) ![License](https://img.shields.io/github/license/lost2705/fintrack)

**FinTrack** is a personal finance tracker with a web interface and Telegram bot.  
Designed to help users stay on top of their daily expenses and build better budgeting habits.

---

## 🚀 Features (in development)

- ✅ Add and view personal expenses
- ✅ Category-based tracking (food, transport, travel, etc.)
- ✅ Database schema managed via Flyway
- ⏳ REST API for expense management
- ⏳ Telegram bot integration
- ⏳ Monthly summaries and reports
- ⏳ PostgreSQL support

---

## 🛠 Tech Stack

- **Language**: Java 21  
- **Framework**: Spring Boot 3  
- **Persistence**: Spring Data JPA  
- **Database**: H2 (in-memory), PostgreSQL (planned)  
- **Migration Tool**: Flyway  
- **Build Tool**: Maven  
- **Bot Platform**: Telegram Bot API  

---

## ▶️ Getting Started

```bash
git clone https://github.com/lost2705/fintrack.git
cd fintrack
./mvnw spring-boot:run
```

> By default, FinTrack uses H2 in-memory database.  
> To switch to PostgreSQL, edit `application.yml`.

---

## 🧪 Sample Data

Flyway migrations automatically create:

- Example categories (e.g., Food, Transport)
- Sample expenses with timestamps

Perfect for testing UI or REST endpoints.

---

## 🧾 Planned Enhancements

- 💬 Multi-user support with authentication
- 📈 Analytics & charts
- 📆 Budget planning per category
- 🌍 Localization (English, Russian)
- 📦 Docker support

---

## 🙌 Contributing

Pull requests are welcome!  
This project is a backend playground to improve Java and Spring development skills.  
Feel free to fork, use, and suggest improvements.

---

## 📄 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**[lost2705](https://github.com/lost2705)**  
Backend Developer • Java Enthusiast • Learning through practice
