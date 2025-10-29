-- Serial type
-- https://stackoverflow.com/questions/70249351/error-null-value-in-column-id-of-relation-xxx-violates-not-null-constraint


CREATE TABLE IF NOT EXISTS Shop (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    location        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Warehouse (
    id              SERIAL PRIMARY KEY,
    shopId          INT NOT NULL,
    quantity        INT NOT NULL,
    isFull          BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (shopId) REFERENCES Shop(id)
);

CREATE TABLE IF NOT EXISTS Product (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    price           INT,
    quantity        INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    title           VARCHAR(255),
    salary          INT NOT NULL,
    shopId          INT,
    isFired         BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (shopId) REFERENCES Shop(id)
);

CREATE TABLE IF NOT EXISTS Incident (
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(255),
    shopId          INT NOT NULL,
    isHandled       BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (shopId) REFERENCES Shop(id)
);

