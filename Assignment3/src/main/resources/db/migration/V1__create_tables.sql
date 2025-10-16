CREATE TABLE IF NOT EXISTS Shop (
    id          INT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    location    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Warehouse (
    id          INT PRIMARY KEY

);

CREATE TABLE IF NOT EXISTS Product (
    id          INT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    price       INT,
    quantity    INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
    id          INT PRIMARY KEY,
    firstName   VARCHAR(255) NOT NULL,
    lastName    VARCHAR(255) NOT NULL,
    title       VARCHAR(255),
    salary      INT NOT NULL,
    shopId      INT,
    FOREIGN KEY (shopId) REFERENCES Shop(id)
);

CREATE TABLE IF NOT EXISTS Incident (
    id          INT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

