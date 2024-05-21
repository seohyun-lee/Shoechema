CREATE TABLE Users (
    user_id INTEGER NOT NULL,
    email VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(11),
    PRIMARY KEY (user_id)
);

CREATE TABLE Shoes (
    shoes_id INTEGER NOT NULL,
    name VARCHAR(64) NOT NULL,
    price INTEGER NOT NULL,
    release_date DATE NOT NULL,
    PRIMARY KEY (shoes_id)
);

CREATE TABLE Sizes (
    size_id INTEGER NOT NULL,
    size_number INTEGER NOT NULL,
    PRIMARY KEY (size_id)
);

CREATE TABLE ShoesOptions (
    shoes_option_id INTEGER NOT NULL,
    shoes_id INTEGER NOT NULL,
    size_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (shoes_option_id),
    FOREIGN KEY (shoes_id) REFERENCES Shoes(shoes_id),
    FOREIGN KEY (size_id) REFERENCES Sizes(size_id)
);

CREATE TABLE Orders (
    order_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    shoes_option_id INTEGER NOT NULL,
    ordered_at DATETIME NOT NULL,
    delivery_address VARCHAR(64) NOT NULL,
    delivery_status VARCHAR(64) NOT NULL,
    order_price INTEGER NOT NULL,
    payment_type VARCHAR(64) NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (shoes_option_id) REFERENCES ShoesOptions(shoes_option_id)
);
