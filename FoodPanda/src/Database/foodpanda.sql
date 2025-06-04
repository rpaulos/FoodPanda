CREATE DATABASE foodpanda;
USE foodpanda;

-- creates the admin table
CREATE TABLE admins (
	admin_ID VARCHAR(10) PRIMARY KEY,
    admin_username VARCHAR(20) UNIQUE NOT NULL,
    admin_password VARCHAR(30) NOT NULL,
    admin_email VARCHAR(100) UNIQUE NOT NULL,
    admin_first_name VARCHAR(20) NOT NULL,
    admin_last_name VARCHAR(20) NOT NULL,
    admin_date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE price_range (
	price_Range_ID VARCHAR(15) PRIMARY KEY,
    price_range_level VARCHAR(20) NOT NULL
);

-- creates the table for storing location of customers (no dependecies)
CREATE TABLE customer_location (
	customer_location_ID VARCHAR(15) PRIMARY KEY,
    city VARCHAR(20) NOT NULL,
    address VARCHAR(500) NOT NULL,
    zip_code VARCHAR(4) NOT NULL
);

-- creates the table for storing location of restaurants (no dependecies)
CREATE TABLE restaurant_location (
	restaurant_location_ID VARCHAR(15) PRIMARY KEY,
    city VARCHAR(20) NOT NULL,
    address VARCHAR(500) NOT NULL,
    zip_code VARCHAR(4) NOT NULL
);

-- creates restaurant table (depends on restaurant_location)
CREATE TABLE restaurant (
	restaurant_ID VARCHAR(15) PRIMARY KEY,
    restaurant_location_ID VARCHAR(15) NOT NULL,
    restaurant_name VARCHAR(100) NOT NULL,
    price_range_ID VARCHAR(15),
    
    FOREIGN KEY (restaurant_location_ID) 
    REFERENCES restaurant_location(restaurant_location_ID)
	ON DELETE CASCADE,
    
    FOREIGN KEY (price_range_ID)
    REFERENCES price_range(price_range_ID)
);

CREATE TABLE product (
	product_ID VARCHAR(15) PRIMARY KEY,
    restaurant_ID VARCHAR(15) NOT NULL,
    product_name VARCHAR(20) NOT NULL,
    product_desc VARCHAR(500) NOT NULL,
    product_quantity INT NOT NULL,
    product_price DECIMAL (10, 2) NOT NULL,
    
    FOREIGN KEY (restaurant_ID)
    REFERENCES restaurant(restaurant_ID)
    ON DELETE CASCADE
);

-- creates customer table
CREATE TABLE customer (
	customer_ID VARCHAR(15) PRIMARY KEY,
    customer_location_ID VARCHAR(15) NOT NULL,
    customer_phone_number VARCHAR(11) UNIQUE NOT NULL,
    customer_email VARCHAR(100) UNIQUE NOT NULL,
    customer_first_name VARCHAR(30) NOT NULL,
    customer_last_name VARCHAR(30) NOT NULL,
    customer_date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (customer_location_ID) 
    REFERENCES customer_location(customer_location_ID)
    ON DELETE CASCADE
);

-- creates business_owner table (tristan 05/26/2025)
CREATE TABLE business_owner (
    business_owner_ID VARCHAR(15) PRIMARY KEY,
    restaurant_ID VARCHAR(15) NOT NULL,
    owner_first_name VARCHAR(30) NOT NULL,
    owner_last_name VARCHAR(30) NOT NULL,
    owner_email VARCHAR(100) UNIQUE NOT NULL,
    owner_password VARCHAR(30) NOT NULL,
    FOREIGN KEY (restaurant_ID)
    REFERENCES restaurant(restaurant_ID)
    ON DELETE CASCADE
);

-- creates wallet with foreign key to who owns it
CREATE TABLE pandapay_wallet (
	customer_ID VARCHAR(15) PRIMARY KEY,
    customer_balance DECIMAL(10, 2) NOT NULL,
    
    FOREIGN KEY (customer_ID) 
    REFERENCES customer(customer_ID)
    ON DELETE CASCADE
);

-- creates wallet with foreign key to who owns it
CREATE TABLE business_owner_pandapay_wallet (
	business_owner_ID VARCHAR(15) PRIMARY KEY,
    business_owner_balance DECIMAL(10, 2) NOT NULL,
    
    FOREIGN KEY (business_owner_ID) 
    REFERENCES business_owner(business_owner_ID)
    ON DELETE CASCADE
);