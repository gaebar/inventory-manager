CREATE TABLE products (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    expiry_date DATE,
    time_duration_for_markdown INT,
    min_threshold INT,
    max_threshold INT,
    current_stock INT
);
