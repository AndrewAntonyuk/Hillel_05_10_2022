CREATE TABLE IF NOT EXISTS user
(
    id            INT(10)        AUTO_INCREMENT,
    first_name    VARCHAR(255)   NOT NULL,
    last_name     VARCHAR(255)   NOT NULL,
    age           INT(10)        NOT NULL,
    email         VARCHAR(255)   UNIQUE NOT NULL,
    login         VARCHAR(255)   UNIQUE NOT NULL,
    user_password VARCHAR(255)   NOT NULL,
    balance       DECIMAL(10, 2) NOT NULL,
    is_blocked    BOOLEAN        NOT NULL DEFAULT FALSE,
    user_role     VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cart
(
    id      INT            AUTO_INCREMENT,
    user_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id          INT(11)        AUTO_INCREMENT,
    cost        DECIMAL(10, 2) DEFAULT 0,
    create_date DATE           NOT NULL,
    user_id     INT(10)        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id           INT(10)        AUTO_INCREMENT,
    product_name VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    quantity     INT(10)        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS purchase
(
    id            INT(10)        AUTO_INCREMENT,
    product_id    INT(10)        NOT NULL,
    purchase_name VARCHAR(255)   NOT NULL,
    price         DECIMAL(10, 2) NOT NULL,
    quantity      INT(10)        NOT NULL,
    cart_id       INT(10),
    order_id      INT(10),
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (cart_id) REFERENCES cart (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);

