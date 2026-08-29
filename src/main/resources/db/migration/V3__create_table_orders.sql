CREATE TABLE orders(
    id BIGINT NOT NULL AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    address VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_price DECIMAL NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,

    PRIMARY KEY(id),

    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id),

    CONSTRAINT fk_order_restaurant
        FOREIGN KEY (restaurant_id)
        REFERENCES restaurant(id)
)