# 🚗 Vehicle Fleet Management System

## 📋 Project Overview

The **Vehicle Fleet Management System** is a Java 17 application designed to manage a complete vehicle rental fleet.

The project combines advanced **Object-Oriented Programming concepts** with a modern **JavaFX graphical interface** to provide a professional fleet management solution.

The system allows users to:

* Load vehicle data from CSV files
* Manage vehicle rentals and returns
* Specify rental duration in days
* Calculate rental costs automatically
* Track generated revenue
* Monitor maintenance requirements
* Generate fleet statistics
* Produce detailed reports
* Visualize fleet information through a professional dashboard

This project was developed as part of the **UA3 – Advanced Object-Oriented Programming** assignment.

---

# 🎯 Objectives

The main objective of this project is to apply advanced Java programming concepts:

* Object-Oriented Programming (OOP)
* Inheritance
* Abstract classes
* Interfaces
* Polymorphism
* Encapsulation
* Method overriding
* Collections (`ArrayList`)
* Custom exceptions
* File management
* CSV processing
* MVC architecture
* JavaFX graphical interface
* SOLID principles

---

# 📁 Project Structure

```text
UA3_Vehicle_Fleet_Management_System
│
├── src
│
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
│   └── resources
│       ├── style.css
│       └── images
│           └── fleet_banner.png
│
├── data
│   └── vehicles.csv
│
├── reports
│   └── fleet_report.txt
│
├── screenshots
│   └── fleet_app.png
│
├── README.md
├── .gitignore
└── LICENSE
```

---

# 🚀 Main Features

## 🚗 Vehicle Management

The application supports multiple vehicle types:

* Car
* SUV
* Truck

Vehicle hierarchy:

```text
                 Vehicle
                    |
        ---------------------------
        |            |            |
       Car          SUV         Truck
```

Each vehicle contains:

* Vehicle ID
* Vehicle type
* Brand
* Model
* Manufacturing year
* Mileage
* Availability status
* Rental statistics

---

# 🚘 Rental Management

The rental module provides:

✅ Rent a vehicle
✅ Return a vehicle
✅ Check availability
✅ Choose rental duration
✅ Calculate rental price
✅ Track rental count
✅ Track total revenue generated

Rental calculation uses polymorphism:

```java
calculateRentalCost(int days)
```

Example:

```java
Vehicle vehicle = V001;

Rental duration = 5 days;

Total cost =
vehicle.calculateRentalCost(5);
```

---

# 📌 Rental Rules

* A rented vehicle cannot be rented again.
* A vehicle under maintenance can still be rented.
* Rental status is independent from maintenance status.
* Every successful rental updates statistics.

---

# 📊 Rental Statistics

Each vehicle stores:

```java
private int rentalCount;

private double totalRevenue;
```

The system calculates:

* Number of rentals
* Most rented vehicle
* Total fleet revenue
* Rental history information

---
# 🖥 JavaFX Graphical Interface

The project includes a professional JavaFX graphical interface designed for easy fleet management.

The interface provides an interactive dashboard and complete vehicle management tools.

---

# 🎨 User Interface Features

The JavaFX application provides:

✅ Vehicle table visualization
✅ Dynamic search system
✅ Vehicle rental
✅ Vehicle return
✅ Rental duration input
✅ Refresh functionality
✅ Double-click vehicle details
✅ Dashboard statistics
✅ Professional CSS styling
✅ Custom fleet banner image

---

# 🖼 Application Banner

The application uses a custom banner:

```text
resources/images/fleet_banner.png
```

The banner provides a professional visual identity for the fleet management application.

---

# 📊 Fleet Dashboard

The dashboard is divided into two professional cards:

## 🚗 Fleet Overview

Displays general fleet information:

```text
🚗 FLEET OVERVIEW


Total Vehicles : 100


🟢 Available : 70


🔴 Rented : 25


🟠 Maintenance : 5
```

Information displayed:

* Total number of vehicles
* Available vehicles
* Currently rented vehicles
* Vehicles requiring maintenance

---

## 📈 Fleet Analysis

Displays advanced fleet statistics:

```text
📊 FLEET ANALYSIS


Average Mileage : 32450 km


💰 Total Revenue : 2500 $


🏆 Most Used : Toyota Corolla
```

Information displayed:

* Average vehicle mileage
* Total generated revenue
* Most rented vehicle

---

# 🔎 Vehicle Search

The application includes a dynamic search system.

Users can search by:

* Vehicle ID
* Brand
* Model
* Vehicle type

Example:

```text
Search:

Toyota

Results:

V001 - Toyota Corolla
V010 - Toyota RAV4
```

---

# 🚘 Rental Interface

The rental interface allows users to:

1. Select a vehicle
2. Enter rental duration
3. Click the Rent button

Example:

```text
Vehicle:

BMW X5


Rental Days:

7


Action:

Rent
```

The application automatically:

* Checks availability
* Changes vehicle status
* Calculates rental cost
* Updates statistics
* Saves changes

---

# 🔄 Vehicle Status Management

Vehicle status is displayed dynamically:

| Status      | Display   |
| ----------- | --------- |
| Available   | 🟢 Green  |
| Rented      | 🔴 Red    |
| Maintenance | 🟡 Yellow |

Example:

```text
Toyota Corolla     🟢 Available

BMW X5              🔴 Rented

Ford Truck          🟡 Maintenance
```

---

# 🛠 Maintenance Management

The system automatically detects vehicles requiring maintenance.

Maintenance rule:

```text
Mileage > 50000 km

          ↓

Maintenance required
```

Features:

* Automatic maintenance detection
* Maintenance statistics
* Maintenance status display

---

# 📂 CSV Data Management

Vehicle data is stored in:

```text
data/vehicles.csv
```

The CSV module provides:

* CSV file loading
* Vehicle object creation
* Data validation
* Status synchronization
* Exception handling

Example:

```csv
id,type,brand,model,year,mileage,rented

V001,Car,Toyota,Corolla,2022,15000,false

V002,SUV,BMW,X5,2021,35000,false

V003,Truck,Ford,F150,2020,70000,true
```

---

# 📄 Report Generation

The application automatically generates:

```text
reports/fleet_report.txt
```

The report contains:

* Fleet summary
* Vehicle statistics
* Rental information
* Revenue information
* Maintenance information

Example:

```text
===== FLEET REPORT =====


Total Vehicles : 100

Available Vehicles : 75

Rented Vehicles : 20

Maintenance Vehicles : 5


Total Revenue : 3500 $
```

---

# 🏗 Application Architecture

The project follows a layered MVC architecture:

```text
+---------------------------+
|      JavaFX Interface     |
|   FleetApplication.java   |
+---------------------------+
             |
             v
+---------------------------+
|    VehicleController      |
+---------------------------+
             |
             v
+---------------------------+
|      Service Layer        |
| FleetManager              |
| RentalManager             |
| StatisticsManager         |
| MaintenanceManager        |
| CsvManager                |
+---------------------------+
             |
             v
+---------------------------+
|       Model Layer         |
| Vehicle                   |
| Car                       |
| SUV                       |
| Truck                     |
+---------------------------+
             |
             v
+---------------------------+
|        CSV Data           |
+---------------------------+
```

---
# 🏗 SOLID Principles Applied

The application follows the main SOLID design principles to improve maintainability, scalability, and code organization.

---

# 1️⃣ Single Responsibility Principle (SRP)

Each class has one specific responsibility:

| Class                | Responsibility                         |
| -------------------- | -------------------------------------- |
| `FleetManager`       | Manage vehicle collection              |
| `RentalManager`      | Manage vehicle rentals                 |
| `MaintenanceManager` | Manage maintenance operations          |
| `StatisticsManager`  | Calculate fleet statistics             |
| `CsvManager`         | Read and write CSV files               |
| `ReportManager`      | Generate fleet reports                 |
| `VehicleController`  | Connect JavaFX interface with services |

---

# 2️⃣ Open/Closed Principle (OCP)

The system is open for extension but closed for modification.

New vehicle types can be added without changing existing services.

Example:

```java
public class Motorcycle extends Vehicle {

    @Override
    public String getVehicleType(){
        return "Motorcycle";
    }

}
```

Existing components continue working without modification.

---

# 3️⃣ Liskov Substitution Principle (LSP)

All vehicle subclasses can replace the parent class:

```java
Vehicle vehicle;


vehicle = new Car();

vehicle = new SUV();

vehicle = new Truck();
```

The system works correctly with all vehicle types.

---

# 4️⃣ Interface Segregation Principle (ISP)

The application uses specific interfaces:

```java
Rentable
```

for rental operations.

```java
Maintainable
```

for maintenance operations.

Each class implements only the required behaviors.

---

# 5️⃣ Dependency Inversion Principle (DIP)

The controller communicates with service classes instead of directly managing data files.

Architecture:

```text
JavaFX

   ↓

VehicleController

   ↓

Services

   ↓

Models

   ↓

CSV
```

---

# 🛠 Technologies Used

The project was developed using:

| Technology         | Usage                     |
| ------------------ | ------------------------- |
| Java 17            | Main programming language |
| JavaFX 17          | Graphical interface       |
| CSS                | UI styling                |
| ArrayList          | Data collections          |
| File I/O           | File management           |
| CSV Processing     | Vehicle persistence       |
| Exception Handling | Error management          |
| Git                | Version control           |
| GitHub             | Source repository         |

---

# ▶️ Run Console Application

Compile:

```bash
javac -d bin src/interfaces/*.java src/model/*.java src/exceptions/*.java src/service/*.java src/app/Main.java
```

Run:

```bash
java -cp bin app.Main
```

---

# ▶️ Run JavaFX Application

JavaFX SDK 17 is required.

Compile:

```bash
javac \
--module-path PATH_TO_JAVAFX/lib \
--add-modules javafx.controls \
-d bin \
src/**/*.java
```

Run:

```bash
java \
--module-path PATH_TO_JAVAFX/lib \
--add-modules javafx.controls \
-cp bin \
app.FleetApplication
```

---

# 📸 Application Screenshot

The JavaFX application screenshot can be added here:

```text
screenshots/fleet_app.png
```

Example:

![Vehicle Fleet Management System](screenshots/fleet_app.png)

---

# 📌 Current Application Capabilities

The current version includes:

## Vehicle Management

✅ Load vehicles from CSV
✅ Display fleet table
✅ Search vehicles
✅ View vehicle details

## Rental Management

✅ Rent vehicles
✅ Return vehicles
✅ Select rental duration
✅ Calculate rental cost
✅ Track revenue

## Statistics

✅ Total vehicles
✅ Available vehicles
✅ Rented vehicles
✅ Maintenance vehicles
✅ Average mileage
✅ Total revenue
✅ Most rented vehicle

## Maintenance

✅ Automatic maintenance detection
✅ Maintenance statistics

## Reporting

✅ TXT fleet report generation

---

# 🚀 Future Improvements

Possible future enhancements:

## Database Integration

Replace CSV storage with:

* MySQL
* PostgreSQL
* MongoDB

---

## User Authentication

Add:

* Admin accounts
* Employee accounts
* Customer accounts

---

## Advanced Dashboard

Add:

* Charts
* Graphs
* Revenue visualization
* Rental trends

---

## Rental History

Add:

* Customer information
* Rental dates
* Previous rentals
* Invoice generation

---

## Export Features

Add:

* PDF reports
* Excel export
* Email reports

---

# 📜 License

This project is developed for educational purposes as part of the:

**UA3 – Advanced Object-Oriented Programming**

---

# 👨‍💻 Author

**Larbi Teraoui**

Vehicle Fleet Management System
Java 17 + JavaFX Application

---
