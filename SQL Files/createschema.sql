# table 생성
CREATE TABLE Users (
    user_id INTEGER NOT NULL AUTO_INCREMENT,
    email VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(11),
    PRIMARY KEY (user_id)
);

CREATE TABLE Shoes (
    shoes_id INTEGER NOT NULL AUTO_INCREMENT,
    shoes_name VARCHAR(64) NOT NULL,
    price INTEGER NOT NULL,
    release_date DATE NOT NULL,
    PRIMARY KEY (shoes_id)
);

CREATE TABLE Sizes (
    size_id INTEGER NOT NULL AUTO_INCREMENT,
    size_number INTEGER NOT NULL,
    PRIMARY KEY (size_id)
);

CREATE TABLE ShoesOptions (
    shoes_option_id INTEGER NOT NULL AUTO_INCREMENT,
    shoes_id INTEGER NOT NULL,
    size_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (shoes_option_id),
    FOREIGN KEY (shoes_id) REFERENCES Shoes(shoes_id),
    FOREIGN KEY (size_id) REFERENCES Sizes(size_id)
);

CREATE TABLE Orders (
    order_id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    shoes_option_id INTEGER NOT NULL,
    delivery_address VARCHAR(64) NOT NULL,
    delivery_status VARCHAR(64) NOT NULL,
    order_price INTEGER NOT NULL,
    payment_type VARCHAR(64) NOT NULL,
    ordered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (shoes_option_id) REFERENCES ShoesOptions(shoes_option_id)
);

# view 생성
CREATE VIEW ShoesProduct AS
SELECT ShoesOptions.shoes_option_id,
       Shoes.shoes_name,
       Sizes.size_number,
       Shoes.price
FROM ShoesOptions, Shoes, Sizes
WHERE ShoesOptions.shoes_id = Shoes.shoes_id
  AND ShoesOptions.size_id = Sizes.size_id;

# index 생성
CREATE index UserEmail_index on Users(email);
CREATE index ShoesName_index on Shoes(shoes_name);
CREATE index SizeNumber_index on Sizes(size_number);
CREATE index ShoesOptionQuantity_index on ShoesOptions(quantity);
CREATE index OrderDeliveryStatus_index on Orders(delivery_status);