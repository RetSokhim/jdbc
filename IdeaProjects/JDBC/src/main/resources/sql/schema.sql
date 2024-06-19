CREATE TABLE IF NOT EXISTS product(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    price_per_unit FLOAT NOT NULL ,
    active_for_sell BIT NOT NULL
);

INSERT INTO product (name, price_per_unit, active_for_sell) VALUES
('Product A', 10.99, B'1'),
('Product B', 15.49, B'0'),
('Product C', 7.99, B'1'),
('Product D', 20.00, B'1'),
('Product E', 5.75, B'0');
