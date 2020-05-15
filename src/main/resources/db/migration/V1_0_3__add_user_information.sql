ALTER TABLE users
    DROP COLUMN name,
    DROP COLUMN surname,
    ADD COLUMN first_name varchar(35),
    ADD COLUMN last_name varchar(40),
    ADD COLUMN gender varchar(10),
    ADD COLUMN date_of_birth date,
    ADD COLUMN city varchar(35),
    ADD COLUMN mobile_number varchar(15),
    ADD COLUMN reset_date timestamp,
    ADD COLUMN reset_key varchar(40),
    ADD COLUMN activated boolean not null,
    ADD COLUMN activation_key varchar(40)
