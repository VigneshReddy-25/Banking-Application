# Banking Application

A console-based banking application built with **Java, JDBC, and MySQL**. The project demonstrates object-oriented programming, layered application structure, custom exception handling, and persistent storage of accounts and transaction records.

## Features

- Create a standard bank account
- Create savings accounts with an interest rate
- Create current accounts with an overdraft limit
- Deposit money into an account
- Withdraw money from an account
- View an individual account
- View all accounts
- View transaction history for an account
- View all transaction records
- Close an account
- Store account and transaction data in MySQL
- Use custom exceptions to handle invalid details, invalid amounts, missing accounts, insufficient balance, and database errors

> Features listed here reflect the project's intended menu and current implementation. Verify each feature in your local version before publishing.

## Technologies Used

- **Java** (JDK 21)
- **JDBC** — connecting Java to the database
- **MySQL** — storing account and transaction data
- **MySQL Connector/J** — JDBC driver
- **Eclipse IDE** — development environment

## Project Structure

```text
Banking-Application/
└── src/
    ├── Exception/
    │   └── Custom exception classes
    ├── main/
    │   └── BankingApplication.java
    ├── model/
    │   ├── Account.java
    │   ├── SavingsAccount.java
    │   ├── CurrentAccount.java
    │   └── Transaction.java
    ├── repository/
    │   ├── AccountRepository.java
    │   └── TransactionRepository.java
    ├── service/
    │   └── AccountService.java
    └── util/
        └── DatabaseConnection.java
```

## Database

The application uses a MySQL database named `bankingapplication` with two main tables:

### `account`

Stores account information such as:

- `account_number`
- `account_holder_name`
- `balance`
- `account_type`
- `interest_rate`
- `overdraft_limit`

### `transactions`

Stores transaction information such as:

- `transaction_id`
- `account_number`
- `transaction_type`
- `amount`
- `balance_after_transaction`

The transaction table's `account_number` references the account table's `account_number` through a foreign key.

## Setup and Run

### 1. Requirements

Install the following:

- JDK 21 or a compatible JDK
- MySQL Server
- MySQL Workbench (optional, for managing the database)
- MySQL Connector/J
- Eclipse IDE or another Java IDE

### 2. Create the database

In MySQL Workbench, create or select the database:

```sql
CREATE DATABASE IF NOT EXISTS bankingapplication;
USE bankingapplication;
```

Create the `account` and `transactions` tables using the schema configured for this project. Ensure that:

- `account.account_number` is a primary key and auto-incrementing.
- `transactions.transaction_id` is a primary key and auto-incrementing.
- `transactions.account_number` has a foreign key referencing `account.account_number`.
- The table and column names match the SQL queries in the repository classes.

### 3. Configure the database connection

Open `src/util/DatabaseConnection.java` and verify the connection settings:

```java
String url = "jdbc:mysql://localhost:3306/bankingapplication";
String uname = "YOUR_MYSQL_USERNAME";
String pwd = "YOUR_MYSQL_PASSWORD";
```

Replace the username and password with your local MySQL credentials. **Do not commit real passwords or other secrets to a public repository.** For a public project, move credentials to environment variables or a local configuration file excluded from Git.

### 4. Add MySQL Connector/J

Ensure the MySQL Connector/J driver is on the project's build path. In Eclipse, you can add the driver JAR through the project's Java Build Path, or manage it with your build tool if the project uses one.

### 5. Run the application

Run `main.BankingApplication` from Eclipse. Use the console menu to create accounts, deposit or withdraw money, and view account or transaction records.

## Application Menu

The console menu currently includes:

1. Create Account
2. Create Savings Account
3. Create Current Account
4. Deposit
5. Withdraw
6. View Account
7. View All Accounts
8. View Account Transactions
9. View All Transactions
10. Close Account
0. Exit

## Architecture

The code is organized into layers:

- **`model`** — represents accounts and transactions.
- **`repository`** — performs database operations using JDBC.
- **`service`** — contains banking operations and validation.
- **`main`** — provides the console-based user interface.
- **`util`** — creates database connections.
- **`Exception`** — contains custom exception classes.

## Learning Outcomes

Through this project, I practiced:

- Classes, objects, inheritance, and polymorphism
- Encapsulation and method overriding
- Separating model, service, and repository responsibilities
- Connecting Java applications to MySQL using JDBC
- Executing parameterized SQL statements with `PreparedStatement`
- Retrieving generated IDs from the database
- Handling database errors and custom exceptions
- Modeling account types and transaction history

## Future Improvements

- Add database transactions so balance changes and transaction records succeed or fail together
- Replace permanent account deletion with account closure/status tracking to preserve financial history
- Move database credentials to environment variables or secure configuration
- Add automated unit and integration tests
- Add a graphical or web-based user interface

## Author

**Vignesh Reddy**

GitHub: [Banking-Application](https://github.com/VigneshReddy-25/Banking-Application)

---

This project was developed for learning and demonstration purposes. It is not intended for handling real customer funds or production banking operations.
