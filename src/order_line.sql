CREATE TABLE order_line (
    order_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    pizza_type INT,
    pizza_price NUMERIC(5, 2),
    pizza_quantity INT,
    pizza_size INT,
    drink_type INT,
    drink_price NUMERIC(5, 2),
    drink_quantity INT,
    drink_size INT,
    fries_size INT,
    fries_price NUMERIC(5, 2),
    fries_quantity INT,

    PRIMARY KEY(order_id)
);

SELECT * FROM order_line;
/*Execute this SQL Code to make a table in the Database with name order_line*/
