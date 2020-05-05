ALTER TABLE product
    ADD COLUMN createdby BIGSERIAL NOT NULL,
    ADD CONSTRAINT FK_product_created_by FOREIGN KEY (createdby) REFERENCES users (id)
