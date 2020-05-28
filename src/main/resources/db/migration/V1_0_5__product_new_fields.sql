create table feature
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    product_id INTEGER REFERENCES product (id)
);

create table tag
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    product_id INTEGER REFERENCES product (id)
);

ALTER TABLE product
    DROP COLUMN available_quantity,
    ADD COLUMN brand         VARCHAR(50) NOT NULL,
    ADD COLUMN color         VARCHAR(20) NOT NULL,
    ADD COLUMN category      VARCHAR(20) NOT NULL,
    ADD COLUMN category_type VARCHAR(10) NOT NULL,
    ADD COLUMN status        VARCHAR(10) NOT NULL,
    ADD COLUMN product_code  VARCHAR(50) NOT NULL,
    ADD COLUMN rating        BIGINT      NOT NULL,
    ADD COLUMN quantity      BIGINT      NOT NULL,
    ADD COLUMN availability  BOOLEAN     NOT NULL DEFAULT FALSE,
    ADD COLUMN popular       BOOLEAN     NOT NULL DEFAULT FALSE;


ALTER TABLE image
    ADD COLUMN is_main BOOLEAN NOT NULL DEFAULT FALSE;

