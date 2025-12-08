# 🧪 OrangeHRM Automation Test

Selenium WebDriver | TestNG | Java | Maven

This project contains automated test scripts for the OrangeHRM website using Selenium and TestNG.
The framework follows a simple Page Object Model structure and supports reporting, screenshots, and data-driven testing.

## Project Folder Structure
```
FinalProject
│
├── exports/
│   ├── logs/                 # Log files
│   ├── reports/              # Extent HTML reports
│   ├── screenshots/          # Screenshots on failure
│   └── videos/               # Recorded test runs
│
├── src/
│   ├── main/java/finalProject/
│   │   ├── drivers/          # WebDriver setup
│   │   ├── helpers/          # Helper methods
│   │   ├── keywords/         # Actions like click, type, wait
│   │   ├── reports/          # Extent report setup
│   │   └── utils/            # Config reader, Excel reader
│   │
│   └── test/java/finalProject/
│       ├── common/           # Base test class
│       ├── listeners/        # TestNG listeners
│       ├── pages/            # Page Object classes
│       └── testcases/        # Test scripts
│
├── pom.xml
└── README.md
```

## Test Scenarios Implemented
### Admin Module

- Manage Users
- Manage Job Titles
- Manage Job Categories
- Manage Employment Status
- Manage Locations
- Add / Edit / Delete operations
- Validate table updates and UI elements

### Login Module

- Invalid username
- Invalid password
- Successful login
- Login using newly created Admin user

### PIM Module

- Add Employee
- Update Personal Details
- Update Job Details
- Validate data mapping from Admin configurations

### Employee Search Module

- Search employee
- Validate search result table  

## 👤 Author

Automation QA – Selenium TestNG Java

Khanh Pham