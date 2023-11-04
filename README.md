# Retail Inventory Management System

> "We’ve got too much inventory in the back rooms and our processes are not where we want them to be and that’s causing some undue shrinkage and some out-of-stocks," said Wal-Mart US CEO Greg Foran.

_Source: [Business Insider](https://www.businessinsider.com/wal-marts-ceo-reveals-8-main-problems-2015-4)_

## Overview
The Retail Inventory Management System is a Java-based application created to address the intricate needs of inventory management in retail. Developed with the guidance of TechBridge Inc., this project is inspired by the practical challenges faced by retailers, as highlighted by industry leaders like Greg Foran. It streamlines the inventory process, enhances visibility, and reduces losses due to overstocking and out-of-stock scenarios.

## Overview
The Retail Inventory Management System is a Java-based application created to address the intricate needs of inventory management in retail. Developed with the guidance of TechBridge Inc., this project is inspired by the practical challenges faced by retailers, as highlighted by industry leaders like Greg Foran. It streamlines the inventory process, enhances visibility, and reduces losses due to overstocking and out-of-stock scenarios.

## Key Features
- **Inventory Tracking**: Monitor inventory levels, automate restock alerts, and manage stock thresholds.
- **Expiry Date Management**: Keep an eye on product expiry dates and receive alerts for approaching or past expiration.
- **Markdown Management**: Track and manage markdown schedules to optimize inventory turnover.

Detailed project requirements can be found in the [Project Requirements](ProjectRequirements.md) document.

## Technologies and Tools Used
- **Backend**: [Java](https://www.java.com/) with [Spring Boot](https://spring.io/projects/spring-boot) for creating RESTful services.
- **Database**: [MySQL](https://www.mysql.com/) for data persistence.
- **ORM**: [Hibernate](https://hibernate.org/) with [JPA](https://jakarta.ee/specifications/persistence/) for efficient database operations.
- **Data Validation**: Enforced by the [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/).
- **Build and Dependency Management**: Managed with [Maven](https://maven.apache.org/).
- **Environment and Configuration**: Secured with GitHub Secrets for sensitive information management.

## User Interface
The application offers a Command Line Interface (CLI) for all inventory interactions, including adding, updating, listing, and removing inventory items. This allows for a nimble experience ideal for various environments, from small businesses to enterprise back-office operations.

A full list of CLI commands and their usage can be found below:

- **List Inventory**: `java -jar inventory-manager.jar list`
- **Add Item**: `java -jar inventory-manager.jar add --name "Item Name" --quantity 10`
- **Update Item**: `java -jar inventory-manager.jar update --id 1 --name "New Item Name" --quantity 15`
- **Remove Item**: `java -jar inventory-manager.jar remove --id 1`

For future enhancements, we are exploring the addition of a web-based interface for an even more intuitive user experience.

## Installation Steps
To get started with the Retail Inventory Management System, follow these steps:


## Installation Steps
To get started with the Retail Inventory Management System, follow these steps:

1. **Clone the Repository:**
```bash
git clone https://github.com/gaebar/inventory-manager.git
```

2. Navigate to the Project Directory:
```bash
cd inventory-manager
```

3. Build the Application:
- With default settings:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=default
```
- Using GitHub Secrets configuration:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=github
```

4. Run the Application:
- Using an IDE like IntelliJ IDEA or Eclipse, run the `InventoryManagerApplication` class.
- From the command line:
    ```bash
    mvn spring-boot:run
    ```
Ensure Java and Maven are installed and properly configured on your system before starting.


### Contributing
Your contributions are welcome! If you're interested in improving the Retail Inventory Management System, please feel free to fork the repository and submit a pull request. For substantial changes, please open an issue first to discuss what you propose. Check out the [Contribution Guidelines](CONTRIBUTING.md) for more details on how to contribute effectively.

### License
The Retail Inventory Management System is made available as open source under the [MIT License](LICENSE), which provides a broad permission to use, modify, and distribute the software.

