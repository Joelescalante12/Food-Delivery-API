-- The previous free-text addresses have no Mapbox id, so they are discarded.
-- address_id is nullable because the addresses row is created after its owner.

ALTER TABLE customer
    DROP COLUMN address,
    ADD COLUMN address_id BIGINT NULL,
    ADD CONSTRAINT fk_customer_address
        FOREIGN KEY (address_id)
        REFERENCES addresses(id);

ALTER TABLE restaurant
    DROP COLUMN address,
    ADD COLUMN address_id BIGINT NULL,
    ADD CONSTRAINT fk_restaurant_address
        FOREIGN KEY (address_id)
        REFERENCES addresses(id);

ALTER TABLE orders
    DROP COLUMN address,
    ADD COLUMN address_id BIGINT NULL,
    ADD CONSTRAINT fk_order_address
        FOREIGN KEY (address_id)
        REFERENCES addresses(id);
