DROP DATABASE IF EXISTS caddey;



CREATE DATABASE caddey;

USE caddey;

CREATE TABLE users (
    Email VARCHAR(255) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL
);


CREATE TABLE stock (
    ProductID VARCHAR(255) PRIMARY KEY,
    Detail TEXT,
    Company VARCHAR(255),
    Quantity INT
);



CREATE TABLE sale (
    SaleID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID VARCHAR(255),
    Company VARCHAR(255),
    Date DATE,
    Payment VARCHAR(255),
    Quantity INT,
    CashierName VARCHAR(255),
    FOREIGN KEY (ProductID) REFERENCES stock(ProductID)
);
