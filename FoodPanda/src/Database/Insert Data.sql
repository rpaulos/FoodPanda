INSERT INTO restaurant_location (restaurant_location_ID, city, address, zip_code) VALUES
('RL001', 'Makati', '123 Ayala Ave', '1226'),
('RL002', 'Quezon City', '45 Katipunan Ave', '1108'),
('RL003', 'Taguig', '11th Ave, BGC', '1630');

INSERT INTO price_range (price_range_ID, price_range_level) VALUES
('PR001', '₱'),
('PR002', '₱₱'),
('PR003', '₱₱₱');

INSERT INTO restaurant (
    restaurant_ID,
    restaurant_location_ID,
    restaurant_name,
    price_range_ID,
    restaurant_header_path
) VALUES 
('REST001', 'RL001', 'Mang Inasal', 'PR001', 'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\mang_inasal.jpg'),
('REST002', 'RL002', 'Shakey\'s Pizza', 'PR002', 'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\shakeys.jpg'),
('REST003', 'RL003', 'Conti\'s Bakeshop & Restaurant', 'PR003', 'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\contis.jpg'),
('REST004', 'RL001', 'Jollibee', 'PR001', 'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\jollibee.jpg'),
('REST005', 'RL002', 'Army Navy', 'PR002', 'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\armynavy.jpg');


UPDATE restaurant
SET restaurant_header_path = CONCAT(
    'C:\\Users\\Rae\\Desktop\\FoodPanda\\FoodPanda\\src\\User Interface\\Restaurant Header\\',
    restaurant_ID,
    '.png'
)
WHERE restaurant_ID IN (
    'R_RLMAN_00001_001',
    'R_RLMAN_00002_001',
    'R_RLMAN_00003_001',
    'R_RLMAN_00005_001'
);

DELETE FROM restaurant
WHERE restaurant_ID = 'R_RLMAN_00005_001';

DELETE FROM restaurant_location
WHERE restaurant_location_ID = "RLMAN_00005";

