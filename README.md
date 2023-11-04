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
The application offers an interactive Command Line Interface (CLI) for all inventory management, including adding, updating, listing, and removing inventory items.

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
3. Ensure Java, Maven and MySQL are installed and properly configured on your system before 
   starting. The application is tested with Java 17, Maven 3.9.4, and MySQL 8.1.0.

4. **Start and Configure the Database:**
- Start a MySQL server on your local machine.
```bash
mysql.server start
```
- Log in to the MySQL shell as the root user. Enter your MySQL password when prompted.
```bash
mysql -u root -p
```
- Create a new MySQL database named `inventory_manager`.
```bash
mysql> CREATE DATABASE inventory_manager;
```
- Exit the MySQL shell.
```bash
mysql> exit
```
4.Build and Run the Application:
- Using an IDE like IntelliJ IDEA or Eclipse, run the `InventoryManagerApplication` class 
  setting the Spring Boot 'local' profile.
- From the command line:
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=local
    ```
5. Package the Application:
```
mvn package
```

6. Run the Application from the Jar File, setting the Spring Boot 'local' profile and the 
   database connection string. The database connection string should be in the format
   `jdbc:mysql://<username>:<password>@<host>:<port>/<database_name>`. For example:
```
java -Dspring.profiles.active=local -Dspring.datasource.url=jdbc:mysql://root:root@localhost:3306/inventory_manager -jar inventory-manager-0.0.1-SNAPSHOT.jar
```

### Contributing
Your contributions are welcome! If you're interested in improving the Retail Inventory Management System, please feel free to fork the repository and submit a pull request. For substantial changes, please open an issue first to discuss what you propose. Check out the [Contribution Guidelines](CONTRIBUTING.md) for more details on how to contribute effectively.

### License
The Retail Inventory Management System is made available as open source under the [MIT License](LICENSE), which provides a broad permission to use, modify, and distribute the software.

