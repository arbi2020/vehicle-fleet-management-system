# 🚗 Vehicle Fleet Management System

## 📋 Project Overview

The **Vehicle Fleet Management System** is a Java application developed to manage a rental vehicle fleet. It allows users to load vehicle data from a CSV file, manage rentals and returns, track vehicle maintenance, generate fleet statistics, and create summary reports.

This project was developed as part of the **UA3 – Advanced Object-Oriented Programming** assignment.

---

## 🎯 Objectives

The application demonstrates the use of object-oriented programming concepts by implementing:

* Inheritance
* Abstract classes
* Interfaces
* Polymorphism
* ArrayList collections
* Custom exceptions
* CSV file reading
* File generation (TXT report)
* SOLID principles (SRP and OCP)

---

## 📁 Project Structure

```text
UA3_Vehicle_Fleet_Management_System
│
├── src
│   ├── app
│   │   └── Main.java
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

## 🚀 Features

### Vehicle Management

* Manage multiple vehicle types:

  * Car
  * SUV
  * Truck
* Store vehicle information:

  * ID
  * Brand
  * Model
  * Year
  * Mileage
  * Availability

### Rental Management

* Rent a vehicle
* Return a vehicle
* Calculate rental cost using polymorphism
* Prevent renting unavailable vehicles

### Maintenance Management

* Detect vehicles requiring maintenance
* Report maintenance operations
* Complete maintenance tasks

### CSV Data Management

* Load vehicle data from a CSV file
* Validate vehicle information
* Ignore invalid records
* Handle custom exceptions

### Statistics

Generate useful fleet statistics:

* Total number of vehicles
* Average mileage
* Estimated rental revenue
* Number of vehicles by type
* Vehicles requiring maintenance

### Report Generation

Generate a text report containing:

* Fleet summary
* Statistics
* Maintenance information

---

## 🛠 Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Java Collections (`ArrayList`)
* File I/O (`BufferedReader`, `FileWriter`)
* Exception Handling
* Git & GitHub

---

## 📂 CSV File Format

Example:

```csv
id,type,brand,model,year,mileage,rented,extra
V001,Car,Toyota,Corolla,2022,15000,false,4
V002,SUV,BMW,X5,2021,35000,false,true
V003,Truck,Ford,F150,2020,70000,true,2.5
```

The project also includes invalid data to demonstrate custom exception handling.

---

## ▶️ How to Compile

```bash
javac -d bin src/interfaces/*.java src/model/*.java src/exceptions/*.java src/service/*.java src/app/*.java
```

---

## ▶️ How to Run

```bash
java -cp bin app.Main
```

---

## 📊 Example Output

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

## 🏗 Object-Oriented Concepts Applied

* Inheritance
* Abstract Class
* Interfaces
* Method Overriding
* Polymorphism
* Encapsulation
* Custom Exceptions
* SOLID Principles

---

## 📜 License

This project is provided for educational purposes.
