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
