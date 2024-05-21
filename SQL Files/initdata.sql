-- Insert data into Users table
INSERT INTO Users (user_id, email, password, address, phone_number) VALUES
(1, 'alice@example.com', 'passwordAlice', '123 Maple Street, Cityville', '01012345678'),
(2, 'bob@example.com', 'passwordBob', '456 Oak Avenue, Townsville', '01087654321'),
(3, 'carol@example.com', 'passwordCarol', '789 Pine Lane, Villageburg', '01011223344'),
(4, 'dave@example.com', 'passwordDave', '101 Cedar Road, Hamletton', '01044332211'),
(5, 'emily@example.com', 'passwordEmily', '202 Birch Boulevard, Metropolis', '01066778899');

-- Insert data into Shoes table
INSERT INTO Shoes (shoes_id, name, price, release_date) VALUES
(1, 'Eclipse Runners', 120000, '2023-03-01'),
(2, 'Urban Explorers', 150000, '2023-04-15'),
(3, 'Wind Sneakers', 80000, '2023-05-10'),
(4, 'Genuine Boots', 250000, '2023-07-05'),
(5, 'Ocean Sandals', 60000, '2023-09-01'),
(6, 'Nova Loafers', 110000, '2023-12-15');

-- Insert data into Sizes table
INSERT INTO Sizes (size_id, size_number) VALUES
(1, 220),
(2, 230),
(3, 240),
(4, 250),
(5, 260),
(6, 270),
(7, 280),
(8, 290),
(9, 300);

-- Insert data into ShoesOptions table
INSERT INTO ShoesOptions (shoes_option_id, shoes_id, size_id, quantity) VALUES
(1, 1, 3, 100),  -- Eclipse Runners, Size 240
(2, 1, 4, 80),   -- Eclipse Runners, Size 250
(3, 2, 6, 50),   -- Urban Explorers, Size 270
(4, 2, 7, 40),   -- Urban Explorers, Size 280
(5, 3, 2, 60),   -- Wind Sneakers, Size 230
(6, 3, 3, 70),   -- Wind Sneakers, Size 240
(7, 4, 8, 90),   -- Genuine Boots, Size 290
(8, 4, 9, 110), -- Genuine Boots, Size 300 
(9, 5, 5, 65),  -- Ocean Sandals, Size 260
(10, 5, 6, 55),  -- Ocean Sandals, Size 270
(11, 6, 4, 75),  -- Nova Loafers, Size 250
(12, 6, 5, 65);  -- Nova Loafers, Size 260

-- Insert data into Orders table
INSERT INTO Orders (order_id, user_id, shoes_option_id, ordered_at, delivery_address, delivery_status, order_price, payment_type) VALUES
(1, 1, 1, '2024-03-01 12:00:00', '123 Maple Street, Cityville', 'Delivered', 120000, 'Credit Card'),
(2, 2, 3, '2024-04-02 14:30:00', '456 Oak Avenue, Townsville', 'Delivered', 150000, 'Credit Card'),
(3, 3, 5, '2024-05-03 10:15:00', '789 Pine Lane, Villageburg', 'Delivered', 80000, 'Credit Card'),
(4, 5, 11, '2024-05-10 09:20:00', '202 Birch Boulevard, Metropolis', 'Shipped', 110000, 'Bank Transfer'),
(5, 4, 8, '2024-05-15 13:55:00', '123 Maple Street, Cityville', 'Shipped', 250000, 'Credit Card'),
(6, 2, 10, '2024-05-17 11:30:00', '456 Oak Avenue, Townsville', 'Processing', 60000, 'Bank Transfer');

-- 추루 삭제..?
select * from Users;
select * from Shoes;
select * from Sizes;
select * from ShoesOptions;
select * from Orders;