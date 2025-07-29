# Expense Tracker Console Application

A Java-based expense tracker with:
 * A file-based console application backend (uses plain text file storage)

 * A simple web frontend in HTML/CSS/JS (disconnected from backend, demo/UI only)

---

## Table of Contents

- [Features](#features)  
- [Project Structure](#project-structure)  
- [Getting Started](#getting-started)  
  - [Backend Setup](#backend-setup)  
  - [Frontend Setup](#frontend-setup)  
- [Input Validation](#input-validation)  
- [File Storage](#file-storage)  
- [Error Handling](#error-handling)  
- [Limitations & Future Improvements](#limitations--future-improvements)  
- [Author](#author)  
- [License](#license)
 

---

## Features

- Add new expenses with date, amount, category, and optional description  
- View all recorded expenses  
- View total amount spent  
- View spending summarized by category  
- Input validation for dates, amounts, and required fields  
- Backend: Persistent file storage via Java text I/O
- Frontend: Interactive single-user UI (data  not connected to backend)
- Clear validation and user feedback
- Persistent storage using a plain text file (`expenses-log.txt`) inside logs folder  
- Clean separation of concerns using Model, DAO, Service, Controller layers

---

## Project Structure

ExpenseTrackerProject/
│
├── src/
│   ├── controller/          # Handles user input and application flow
│   ├── dao/                 # File-based Data Access Object for expenses
│   ├── logs/                # Directory to store expenses-log.txt for persistence
│   │    └── expenses-log.txt
│   ├── model/               # Expense data model class
│   ├── service/             # Business logic and validation layer
│   ├── util/                # Utility classes (e.g., FileUtil)
│   └── Main.java            # Console user interface (entry point)
│
├── web/                     # Frontend files: HTML, CSS, JS 
│   ├── index.html
│   ├── view.html
│   ├── style.css
│   └── script.js
│
├── README.md                # Project documentation

---

## Getting Started

---

## Backend setup

- Java Development Kit (JDK) 8 or higher installed and configured in your system PATH  
- Command line/terminal access to compile and run Java applications

### Compile and Run

1. Open your terminal/command prompt in the `src/` directory.

2. Compile all Java files: javac controller/*.java dao/*.java model/*.java service/*.java util/*.java Main.java

3. Run the application: java Main

---

## How to Use

Once running, the application displays a menu:

Select an option:

1. Add Expense

2. View All Expenses

3. View Total Spent

4. View Spent by Category

5. Exit


- **Add Expense:** Enter date (YYYY-MM-DD), amount (>0), category, and optional description.  
- **View All Expenses:** Lists all recorded expense entries.  
- **View Total Spent:** Shows the total amount spent across all expenses.  
- **View Spent by Category:** Displays spend summary grouped by category.  
- **Exit:** Closes the application.

---

## Frontend Setup
1. Navigate to the web/ folder.

2. Open index.html in your web browser.

3. Enter expenses and try the UI features.

4. Note: The web UI is a standalone demo and does not connect to the Java backend.

---

## Input Validation

- **Date:** Must be in `YYYY-MM-DD` format, and valid calendar date.  
- **Amount:** Must be a positive number.  
- **Category:** Cannot be empty; case-insensitive handling internally.  
- **Description:** Optional; max length 100 characters.

Invalid inputs will result in user-friendly error messages and re-prompting.

---

## File Storage

- Expenses are stored in a plain text file: `logs/expenses-log.txt`  
- Each expense is stored as a CSV line: date,amount,category,description  

- The project ensures that the `logs` directory and file are created automatically if they do not exist.
- The web UI stores expenses in the browser's memory and does not persist them or use backend dat

---

## Error Handling
* Console:
 - The project handles common errors such as:  
 - Invalid user input  
 - File I/O issues (with meaningful messages)  
 - Number format and parsing exceptions  
 - Exceptions propagate to the UI and display clear messages to help users correct errors.

* Web UI:
 - Alerts on incorrect input or empty fields, but cannot save data after page refresh.
---

## Limitations & Future Improvements

- Implement Edit/Delete expense functionality  
- Add sorting and filtering options when viewing expenses  
- Integrate with a web-based frontend and REST API backend  
- Add graphical reports and charts for spending summaries  
- Support multiple users and data synchronization

---

## Author

Ravi Raj 
Email: ravi_2312res1015.iitp.ac.in 
GitHub: https://github.com/raviraj-47

---

## License

MIT License © 2025 Ravi Raj

---

*Thank you for using Expense Tracker! Feel free to contribute or raise issues.*

