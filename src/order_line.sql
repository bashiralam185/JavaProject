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

INSERT INTO order_line (pizza_type, pizza_price, pizza_quantity, pizza_size,
                        drink_type, drink_price, drink_quantity, drink_size,
                        fries_price,
                        fries_quantity,
                        fries_size)
VALUES (1, 5.59, 1, 0, 1, 3.59, 1, 0, 1,9,1);

INSERT INTO order_line (pizza_type, pizza_price, pizza_quantity, pizza_size,
                        drink_type, drink_price, drink_quantity, drink_size,
                        fries_price,
                        fries_quantity,
                        fries_size)
VALUES (1, 4.59, 2, 2, 2, 9.59, 2, 1,1,9,1,0);

INSERT INTO order_line (pizza_type, pizza_price, pizza_quantity, pizza_size,
                        drink_type, drink_price, drink_quantity, drink_size,
                        fries_price,
                        fries_quantity,
                        fries_size)
VALUES (1, 5.59, 1, 0, 0, 0.0, 0, 0,1,9,1,0);

SELECT * FROM order_line;
