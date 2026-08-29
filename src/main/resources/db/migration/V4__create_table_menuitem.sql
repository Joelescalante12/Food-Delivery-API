CREATE TABLE menuitem(
    id BIGINT NOT NULL AUTO_INCREMENT,
    restaurant_id BIGINT NOT NULL,
    name VARCHAR(255),
    description VARCHAR(500),
    price DECIMAL(19,2),
    is_available BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME,

    PRIMARY KEY (id),

    CONSTRAINT fk_menuitem_restaurant
      FOREIGN KEY (restaurant_id)
          REFERENCES restaurant(id)
);