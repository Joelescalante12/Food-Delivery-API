CREATE TABLE addresses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    entity_type VARCHAR(50) NOT NULL,
    entity_id BIGINT NOT NULL,
    mapbox_id VARCHAR(255) NOT NULL,
    label VARCHAR(50),
    is_default BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),

    INDEX idx_addresses_entity (entity_type, entity_id)
)
