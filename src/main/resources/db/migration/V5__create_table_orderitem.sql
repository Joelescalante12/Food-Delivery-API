CREATE TABLE order_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(19,2) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
            REFERENCES orders(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_order_item_menu
        FOREIGN KEY (menu_id)
            REFERENCES menuitem(id)
);