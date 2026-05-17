#  Library Management System

A complete, console-based Java Full Stack application designed to manage library operations efficiently. This project demonstrates core backend development skills including Object-Oriented Programming (OOP), Database Management, and CRUD operations using Java and MySQL.

##  Features

* **Interactive Console UI:** A clean, menu-driven interface for seamless navigation.
* **Database Integration:** Persistent and secure data storage using MySQL and JDBC.
* **Core CRUD Operations:**
  * Add new books to the inventory.
  * View the complete library catalog.
  * Search for books by title keywords.
  * Update book quantities.
  * Delete books from the system.
* **Business Logic:** Issue and Return functionalities with real-time inventory tracking (prevents issuing out-of-stock books).
* **Robust Error Handling:** Input validation and SQL exception management to ensure a crash-free user experience.

##  Technologies Used

* **Language:** Java (Core Java, JDK 8+)
* **Database:** MySQL
* **API/Driver:** JDBC (Java Database Connectivity) - `mysql-connector-j`
* **Architecture:** Modular, Object-Oriented Design

##  Folder Structure

```text
library-management-system/
│
├── src/
│   ├── Main.java                 # Entry point and console menu UI
│   ├── Book.java                 # Encapsulated model class
│   ├── DBConnection.java         # JDBC connection utility
│   └── LibraryOperations.java    # Business logic and SQL queries
│
├── sql/
│   └── library.sql               # Database schema and mock data
│
└── README.md                     # Project documentation
 Prerequisites
Before you begin, ensure you have met the following requirements:

Java Development Kit (JDK): Version 8 or higher installed.

MySQL Server: Installed and running locally.

MySQL JDBC Driver: Download the mysql-connector-j.jar file to connect Java to MySQL.

 Installation & Setup
1. Clone the repository

Bash
git clone [https://github.com/yourusername/library-management-system.git](https://github.com/yourusername/library-management-system.git)
cd library-management-system
2. Database Setup

Open your MySQL client (e.g., MySQL Workbench or Command Line).

Run the SQL commands provided in sql/library.sql to create the library_db database, the books table, and insert sample data.

3. Configure Database Credentials

Open src/DBConnection.java.

Update the USER and PASSWORD variables to match your local MySQL setup:

Java
private static final String USER = "root";      // Replace with your username
private static final String PASSWORD = "password";  // Replace with your password
4. Compile and Run

Ensure the MySQL JDBC driver (mysql-connector-j.jar) is added to your project's build path/classpath.

Compile the Java files and execute the Main class.

 Usage
Once the application is running, follow the on-screen menu:

Enter 1 to add a new book.

Enter 2 to view all available books.

Enter 3 to search for a book by its title.

Enter 4 to update the quantity of an existing book.

Enter 5 to delete a book.

Enter 6 to issue a book (decreases quantity by 1).

Enter 7 to return a book (increases quantity by 1).

Enter 8 to safely exit the application.
