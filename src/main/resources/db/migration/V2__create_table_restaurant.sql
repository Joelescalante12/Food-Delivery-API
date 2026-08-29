CREATE TABLE restaurant(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    fulfillment_type VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,

    PRIMARY KEY (id)
)