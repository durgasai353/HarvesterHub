# 🌾 HarvesterHub

A **Java Full Stack Web Application** designed to manage agricultural stock and distribution efficiently.
This system helps track inventory, enforce minimum stock limits, and record farmer distributions in a structured way.

---

## 🚀 Features

### 📦 Stock Management

* Add new stock items (Seeds, Fertilizers, etc.)
* Maintain quantity and minimum stock limit
* Auto-generated Item IDs using Oracle sequences

### 📊 Inventory Monitoring

* View complete stock inventory
* Highlight **Low Stock** items automatically
* Clean UI with Bootstrap styling

### 🚜 Distribution System

* Distribute stock to farmers
* Prevent distribution when:

  * Requested quantity exceeds available stock
  * Remaining stock goes below minimum limit
* Record all distributions with date and details

### ⚠️ Smart Validation

* Minimum stock protection system
* Error handling for invalid inputs
* Database consistency maintained

---

## 🛠️ Tech Stack

| Layer    | Technology Used          |
| -------- | ------------------------ |
| Frontend | HTML, CSS, Bootstrap     |
| Backend  | Java Servlets            |
| Database | Oracle (JDBC)            |
| Server   | Apache Tomcat            |
| Tools    | Eclipse IDE, Git, GitHub |

---

## 🏗️ Project Structure

```
HarvesterHub/
│
├── src/
│   └── com.srv.www/
│       ├── addStockServlet.java
│       ├── distributionServlet.java
│       ├── stockViewServlet.java
│
├── webapp/
│   ├── index.html
│   ├── addStock.html
│   ├── distribute.html
│   └── css/
│
├── WEB-INF/
│   └── web.xml
│
└── database/
    ├── STOCKS table
    ├── DISTRIBUTION_LOGS table
    └── Sequences
```

---

## 🗄️ Database Schema

### STOCKS Table

* ITEM_ID (Primary Key)
* NAME
* CATEGORY
* QTY
* MIN_LIMIT

### DISTRIBUTION_LOGS Table

* LOG_ID (Primary Key)
* ITEM_ID
* FARMER
* QTY
* DATE

---

## ⚙️ How to Run the Project

1. Clone the repository

```
git clone https://github.com/your-username/HarvesterHub.git
```

2. Import into Eclipse as Dynamic Web Project

3. Configure Apache Tomcat Server

4. Setup Oracle Database:

   * Create tables
   * Create sequences (`stock_seq`, `sq1`)

5. Update DB connection:

```java
DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
```

6. Run the project on server

7. Open in browser:

```
http://localhost:8080/HarvesterHub
```

---

## 🎯 Key Highlights

* Implements **real-world inventory logic**
* Ensures **data integrity with validations**
* Clean separation of frontend and backend
* Beginner-friendly **Java Full Stack project**

---

## 📸 Screenshots
### 🏠 Home Page
![Home](Screenshots/home.png)

### ➕ Add Stock
![Add Stock](Screenshots/addstock.png)

### 📊 View Stock
![View Stock](Screenshots/viewstock.png)

### 🚜 Distribution
![Distribution](Screenshots/distribute.png)

---

## 🔮 Future Enhancements

* Dashboard with analytics (charts & stats)
* User authentication (Admin login)
* Farmer management system
* Export reports (PDF/Excel)
* REST API integration

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork this repository and improve it.

---

## 📌 Author

**Sai M**
Java Full Stack Developer (Aspiring)

---

## ⭐ Show Your Support

If you like this project, please ⭐ star the repository on GitHub!
