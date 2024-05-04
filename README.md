# 2024 Final Assignment - Programming Languages 2

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/18a-_yEU)

## Project Overview
This Java application manages and displays data from SuperStore, with a focus on orders and customer statistics, through an interactive user interface.

## Features
- **Data Processing:** Analyzes and processes order data. (CSV file is included)
- **Customer Search:** Allows searching for customers by name or ID.
- **Order Search:** Enables searching for orders by ID.
- **Customer Summary:** Displays a table summarizing all orders made by a selected customer.
- **Order Details:** Provides detailed views of specific orders.
- **Statistics Generation:** Offers six statistics buttons for various insights.
- **Data Export:** Exports sales reports in `sales-report_<date>.txt` format.

## Technology Stack
- **Java**
- **JavaFX** - For GUI components.
- **Gradle** - For building and managing dependencies.

## Project Structure
```app/
├── build/
│   └── libs/
│       └── app.jar
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── main/
    │   │   │   └── Launcher.java // entry class
    │   │   └── SuperStore/
    │   │       ├── Address.java // Address
    │   │       ├── App.java // javaFx
    │   │       ├── CategoryInfo.java // Record, Category
    │   │       ├── Customer.java // Customer
    │   │       ├── CustomerMapUtils.java // statistics methods for statistics
    │   │       ├── FileDataProcessor.java // processor for read file from Inputstream (fits JAR)
    │   │       ├── FileOutput.java // not in use
    │   │       ├── InstanceGenerator.java // process stream to objects
    │   │       ├── Order.java // Order
    │   │       ├── Product.java // Product
    │   │       ├── propertyCustomerStat.java // for adding cell into table
    │   │       └── propertySalesStat.java // for adding cell into table
    │   └── resources/
    │       ├── SuperStoreOrders.csv // source set
    │       └── SuperStoreReturns.csv // source set
```

                
## Getting Started
To build and run the project, follow these steps:
1. **Build the Project with Dependencies**
   ```bash
   ./gradlew clean shadowJar --refresh-dependencies
2. **Run the Application**  
   java -jar ./app/build/libs/app.jar
3. **View JAR Contents**
   jar tf ./app/build/libs/app.jar > app\build\resources\jar_contents.txt

## Version History
- v1.0.0-alpha - Initial pre-release : Known issues
- v2.0.0 - Functional GUI : Configure ShadowJar for javaFx
- v3.0.0 - GUI release : Require test suites

## Contact
- Project Lead: Yannan Zhang
- Email: yannan.zhang@tuni.fi
