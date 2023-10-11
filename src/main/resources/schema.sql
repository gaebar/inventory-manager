DROP TABLE IF EXISTS products;

CREATE TABLE products (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    expiry_date DATE NOT NULL,
    time_duration_for_markdown INT NOT NULL,
    min_threshold INT NOT NULL,
    max_threshold INT NOT NULL,
    current_stock INT NOT NULL
);
