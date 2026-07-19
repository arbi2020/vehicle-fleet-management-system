# 🚗 Vehicle Fleet Management System

## 📋 Project Overview

The **Vehicle Fleet Management System** is a Java application developed to manage a rental vehicle fleet.

The application allows users to:

- Load vehicle data from a CSV file
- Manage vehicle rentals and returns
- Track vehicle maintenance operations
- Generate fleet statistics
- Create summary reports
- Visualize fleet information through a JavaFX graphical interface

This project was developed as part of the **UA3 – Advanced Object-Oriented Programming** assignment.

---

# 🎯 Objectives

The main objective of this project is to apply advanced Object-Oriented Programming concepts.

The application demonstrates:

- Inheritance
- Abstract classes
- Interfaces
- Polymorphism
- Method overriding
- Encapsulation
- ArrayList collections
- Custom exceptions
- CSV file reading and writing
- TXT report generation
- SOLID principles (SRP and OCP)
- JavaFX graphical user interface

---

# 📁 Project Structure

```text
UA3_Vehicle_Fleet_Management_System
│
├── src
│   │
│   ├── app
│   │   ├── Main.java
│   │   └── FleetApplication.java
│   │
│   ├── controller
│   │   └── VehicleController.java
│   │
│   ├── model
│   │   ├── Vehicle.java
│   │   ├── Car.java
│   │   ├── SUV.java
│   │   └── Truck.java
│   │
│   ├── interfaces
│   │   ├── Rentable.java
│   │   └── Maintainable.java
│   │
│   ├── service
│   │   ├── FleetManager.java
│   │   ├── RentalManager.java
│   │   ├── MaintenanceManager.java
│   │   ├── StatisticsManager.java
│   │   ├── CsvManager.java
│   │   └── ReportManager.java
│   │
│   ├── exceptions
│   │   ├── VehicleNotAvailableException.java
│   │   └── InvalidMileageException.java
│   │
│   └── utils
│
├── data
│   └── vehicles.csv
│
├── reports
│   └── fleet_report.txt
│
├── README.md
├── .gitignore
└── LICENSE
```

---

# 🚀 Features

## 🚗 Vehicle Management

The system manages different types of vehicles:

- Car
- SUV
- Truck

Each vehicle contains:

- Vehicle ID
- Brand
- Model
- Manufacturing year
- Mileage
- Availability status

The vehicle hierarchy is based on inheritance:

```text
              Vehicle
                 |
      -----------------------
      |          |          |
     Car        SUV       Truck
```

---

# 🚘 Rental Management

The rental module allows:

- Renting a vehicle
- Returning a vehicle
- Checking vehicle availability
- Calculating rental costs

Rental cost calculation uses polymorphism.

Each vehicle type provides its own implementation:

```java
calculateRentalCost(int days)
```

---

# 🛠 Maintenance Management

The maintenance module provides:

- Detection of vehicles requiring maintenance
- Maintenance reporting
- Maintenance completion tracking

Example rule:

```text
Mileage > 50000 km

        ↓

Maintenance required
```

---

# 📂 CSV Data Management

The application loads vehicle data from:

```text
data/vehicles.csv
```

CSV processing includes:

- Reading vehicle information
- Creating vehicle objects
- Validating input data
- Detecting invalid records
- Handling custom exceptions


Example CSV format:

```csv
id,type,brand,model,year,mileage,rented,extra

V001,Car,Toyota,Corolla,2022,15000,false,4

V002,SUV,BMW,X5,2021,35000,false,true

V003,Truck,Ford,F150,2020,70000,true,2.5
```

The CSV file contains invalid data to demonstrate error handling.

---

# 📊 Statistics

The system generates fleet statistics:

- Total number of vehicles
- Average mileage
- Estimated rental revenue
- Number of vehicles by type
- Vehicles requiring maintenance
- Fleet utilization information

---

# 📄 Report Generation

The application generates a TXT report:

```text
reports/fleet_report.txt
```

The report contains:

- Fleet summary
- Vehicle statistics
- Maintenance information
- Rental information

---

# 🖥 JavaFX Graphical Interface

The project includes a JavaFX graphical interface connected to the existing backend.

The graphical application provides:

- Vehicle table visualization
- CSV data loading
- Display of vehicle information:

  - ID
  - Type
  - Brand
  - Model
  - Year
  - Mileage
  - Availability


The architecture follows a layered design:

```text
+----------------------+
| JavaFX Application   |
+----------------------+
           |
           v
+----------------------+
| VehicleController    |
+----------------------+
           |
           v
+----------------------+
| FleetManager         |
+----------------------+
           |
           v
+----------------------+
| CsvManager           |
+----------------------+
           |
           v
+----------------------+
| vehicles.csv         |
+----------------------+
```

---

# 🏗 SOLID Principles Applied

## Single Responsibility Principle (SRP)

Each class has a specific responsibility:

| Class | Responsibility |
|---|---|
| FleetManager | Manage fleet vehicles |
| RentalManager | Manage rentals |
| MaintenanceManager | Manage maintenance |
| CsvManager | Load CSV data |
| StatisticsManager | Calculate statistics |
| ReportManager | Generate reports |

---

## Open/Closed Principle (OCP)

The application is designed to allow adding new vehicle types without modifying existing classes.

Example:

Adding a motorcycle:

```java
public class Motorcycle extends Vehicle
```

The existing services continue to work without modification.

---

# 🛠 Technologies Used

- Java 17
- JavaFX 17
- Object-Oriented Programming (OOP)
- ArrayList Collections
- File I/O
- CSV Processing
- Exception Handling
- Git & GitHub

---

# ▶️ Compile Console Application

```bash
javac -d bin src/interfaces/*.java src/model/*.java src/exceptions/*.java src/service/*.java src/app/Main.java
```

---

# ▶️ Run Console Application

```bash
java -cp bin app.Main
```

---

# ▶️ Run JavaFX Application

JavaFX SDK 17 is required.

Compile:

```bash
javac --module-path PATH_TO_JAVAFX/lib --add-modules javafx.controls -d bin src/**/*.java
```

Run:

```bash
java --module-path PATH_TO_JAVAFX/lib --add-modules javafx.controls -cp bin app.FleetApplication
```

---

# 📊 Example Output

```text
===== VEHICLE FLEET MANAGEMENT SYSTEM =====

Vehicles loaded : 14


===== RENTAL MANAGEMENT =====

Vehicle V001 rented successfully.

Rental price : 250.0 $


Vehicle V001 returned successfully.


===== STATISTICS =====

Average mileage : 32642.86 km

Estimated revenue : 5450.0 $
```

---

# 📌 Future Improvements

Possible future enhancements:

- Dashboard statistics
- Vehicle search functionality
- Add / Edit / Delete vehicles
- Complete rental interface
- Maintenance interface
- Database integration
- User authentication

---

# 📜 License

This project is provided for educational purposes.