-- 1. Number of fresh apples on the shelf needs to be between 10 lbs and 45 lbs.
INSERT INTO inventory_manager.products (product_id, product_name, expiry_date, time_duration_for_markdown, min_threshold, max_threshold, current_stock)
VALUES  (1, 'Fresh Apples', '2023-11-21', 7, 10, 45, 5);

-- 2. The max number of lawn mowers of a given type that can be on display is 5 and the minimum is 2.
INSERT INTO inventory_manager.products (product_id, product_name, expiry_date, time_duration_for_markdown, min_threshold, max_threshold, current_stock)
VALUES  (2, 'Lawn Mower Model 5000', '2026-04-18', 15, 2, 5, 1);

-- 3. The maximum number of baby diapers pack of a type/brand that can be on display is 15, the minimum is 5.
INSERT INTO inventory_manager.products (product_id, product_name, expiry_date, time_duration_for_markdown, min_threshold, max_threshold, current_stock)
VALUES  (3, 'Baby Diapers Infant Comfort Plus', '2027-12-09', 5, 5, 15, 8);

-- 4. Number of Organic Bananas on the shelf needs to be between 20 and 60.
INSERT INTO inventory_manager.products (product_id, product_name, expiry_date, time_duration_for_markdown, min_threshold, max_threshold, current_stock)
VALUES  (4, 'Organic Bananas', '2023-05-15', 10, 20, 60, 30);
