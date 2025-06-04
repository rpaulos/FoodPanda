-- Auto creates a wallet everytime a new user is added
DELIMITER $$

CREATE TRIGGER create_wallet_after_customer_insert
AFTER INSERT ON customer
FOR EACH ROW
BEGIN
    INSERT INTO pandapay_wallet (customer_ID, customer_balance)
    VALUES (NEW.customer_ID, 0.00);
END$$

DELIMITER ;

-- Auto creates a wallet everytime a new restaurant is added
DELIMITER $$

CREATE TRIGGER create_wallet_after_owner_insert
AFTER INSERT ON business_owner
FOR EACH ROW
BEGIN
    INSERT INTO business_owner_pandapay_wallet (business_owner_ID, business_owner_balance)
    VALUES (NEW.business_owner_ID, 0.00);
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_update_price_range
AFTER INSERT ON product
FOR EACH ROW
BEGIN
    DECLARE avg_price DECIMAL(10,2);
    DECLARE pr_id VARCHAR(15);

    -- Calculate the average price for the restaurant
    SELECT AVG(product_price)
    INTO avg_price
    FROM product
    WHERE restaurant_ID = NEW.restaurant_ID;

    -- Determine the appropriate price_range_ID
    IF avg_price < 150 THEN
        SET pr_id = 'PR1'; -- Low
    ELSEIF avg_price BETWEEN 150 AND 350 THEN
        SET pr_id = 'PR2'; -- Medium
    ELSE
        SET pr_id = 'PR3'; -- High
    END IF;

    -- Update the restaurant table with the new price range
    UPDATE restaurant
    SET price_range_ID = pr_id
    WHERE restaurant_ID = NEW.restaurant_ID;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_update_price_range_update
AFTER UPDATE ON product
FOR EACH ROW
BEGIN
    DECLARE avg_price DECIMAL(10,2);
    DECLARE pr_id VARCHAR(15);

    -- Calculate the average price for the restaurant
    SELECT AVG(product_price)
    INTO avg_price
    FROM product
    WHERE restaurant_ID = NEW.restaurant_ID;

    -- Determine the appropriate price_range_ID
    IF avg_price < 150 THEN
        SET pr_id = 'PR1'; -- Low
    ELSEIF avg_price BETWEEN 150 AND 350 THEN
        SET pr_id = 'PR2'; -- Medium
    ELSE
        SET pr_id = 'PR3'; -- High
    END IF;

    -- Update the restaurant table with the new price range
    UPDATE restaurant
    SET price_range_ID = pr_id
    WHERE restaurant_ID = NEW.restaurant_ID;
END$$
END;

DELIMITER ;

DELIMITER $$

CREATE TRIGGER set_restaurant_header_path
AFTER INSERT ON restaurant
FOR EACH ROW
BEGIN
    UPDATE restaurant
    SET restaurant_header_path = CONCAT(
        'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\',
        NEW.restaurant_ID,
        '.png'
    )
    WHERE restaurant_ID = NEW.restaurant_ID;
END $$

DELIMITER ;

SHOW TRIGGERS;

