-- Interpolated Mapbox ids (urn:mbxadr-itp:...) embed the search text, so they can exceed 255 chars.
ALTER TABLE addresses MODIFY mapbox_id VARCHAR(1024) NOT NULL;
