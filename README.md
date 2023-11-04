# Retail Inventory Management System

## Overview
Retail Inventory Management System is a comprehensive Java-based application designed to tackle real-world challenges in retail inventory management. Developed as a solo assignment under the mentorship of TechBridge Inc, this application addresses key pain points in the retail sector, inspired by insights from Wal-Mart US CEO Greg Foran.

> "We’ve got too much inventory in the back rooms and our processes are not where we want them to be and that’s causing some undue shrinkage and some out-of-stocks," said Wal-Mart US CEO Greg Foran.

_Source: [Business Insider](https://www.businessinsider.com/wal-marts-ceo-reveals-8-main-problems-2015-4)_

## Key Features
### Section A: Inventory Tracking
- Track minimum and maximum items for display
- Automatic notification for replenishment based on thresholds

### Section B: Expiry Date Management
- Track expiry dates for each product
- Send notifications for expired products

### Section C: Markdown Management
- Store Markdown Dates for each product
- Notify when a product needs to be marked down

## Project Requirements
For detailed project requirements, see [Project Requirements](ProjectRequirements.md).

##  Technologies and Tools Used
### Backend Development:
- **Backend:** [Java](https://www.java.com/) with [Spring Boot](https://spring.io/projects/spring-boot)
- **Database:** [MySQL](https://www.mysql.com/)
- **Object-Relational Mapping (ORM):** [Hibernate](https://hibernate.org/) with [JPA](https://jakarta.ee/specifications/persistence/)
- **Data Validation:** [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/)
- **Build and Dependency Management:** [Maven](https://maven.apache.org/)
  
### Environment and Configuration:
- ** GitHub Secrets:** Instead of the traditional .env file for environment variables, I experimented with GitHub Secrets to secure and manage sensitive information.

## User Interface
### Command Line Interface (CLI):
The application provides a Command Line Interface (CLI) for users to interact with the system. This interface ensures a seamless experience without the need for a GUI, making it lightweight and straightforward. Users can perform all necessary operations, like managing inventory and tracking expiry dates, directly from the command line.

--------------------------------
## User Interface
### Command Line Interface (CLI):
The application currently provides a Command Line Interface (CLI) for managing inventory operations such as tracking items and expiry dates. A list of available CLI commands can be found below:

- **List Inventory**: `java -jar inventory-manager.jar list`
- **Add Item**: `java -jar inventory-manager.jar add --name "Item Name" --quantity 10`
- **Update Item**: `java -jar inventory-manager.jar update --id 1 --name "New Item Name" --quantity 15`
- **Remove Item**: `java -jar inventory-manager.jar remove --id 1`

Future releases will aim to include a web-based interface for enhanced user interaction.

-----------------------------------

## Installation Steps

1. **Clone the Repository:**
```bash
git clone https://github.com/gaebar/inventory-manager.git
```

2. Navigate into the directory:
```bash
cd inventory-manager
```

3. Build the Project:
- For the default configuration (application.properties):
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=default
```

- To use the GitHub Secrets configuration (application-github.properties):
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=github
```

4. Run the application:
- **Using an IDE:**
  If you're using an IDE like IntelliJ IDEA or Eclipse, you can directly run the `InventoryManagerApplication` class.
  
- **Using Command Line:** 
  - **Default Configuration:**
    ```bash
    mvn spring-boot:run
    ```
     
  - **Using GitHub Secrets Configuration:**
    ```bash
    mvn spring-boot:run -Dspring.profiles.active=github
    ```

### Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

### License
[MIT License](LICENSE)

