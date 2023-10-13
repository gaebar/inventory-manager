-- Drop tables if they exist
DROP TABLE IF EXISTS inventory_manager.products;
DROP TABLE IF EXISTS inventory_manager.notifications;

-- Create products table
CREATE TABLE inventory_manager.products (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    expiry_date DATE NOT NULL,
    time_duration_for_markdown INT NOT NULL,
    min_threshold INT NOT NULL,
    max_threshold INT NOT NULL,
    current_stock INT NOT NULL
);

-- Create notifications table
CREATE TABLE inventory_manager.notifications (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     product_id BIGINT,
     message TEXT,
     timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (product_id) REFERENCES inventory_manager.products(product_id)
);