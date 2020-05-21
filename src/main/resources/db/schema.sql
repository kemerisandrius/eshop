CREATE TABLE Products
(
    product_id  INT UNSIGNED      NOT NULL AUTO_INCREMENT,
    title       VARCHAR(50)       NOT NULL,
    description VARCHAR(255)      NOT NULL,
    price       NUMERIC(12, 2)    NOT NULL,
    create_date TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id)
);

CREATE TABLE Customers
(
    customer_id  INT UNSIGNED   NOT NULL    AUTO_INCREMENT,
    mobile VARCHAR(255)         NOT NULL,
    user_name VARCHAR(255)      NOT NULL UNIQUE,
    name VARCHAR(255)           NOT NULL,
    last_name VARCHAR(255)      NOT NULL,
    address VARCHAR(255)        NOT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE Users
(
    id          INT UNSIGNED   NOT NULL    AUTO_INCREMENT,
    username    VARCHAR(255)   NOT NULL,
    password    VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Authorities
(
    username    VARCHAR(255)   NOT NULL,
    authority   VARCHAR(255)   NOT NULL,
    PRIMARY KEY (username)
);
