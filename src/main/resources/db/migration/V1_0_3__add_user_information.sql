ALTER TABLE users
    DROP COLUMN name,
    ADD COLUMN first_name varchar(35),
    ADD COLUMN last_name varchar(40),
    ADD COLUMN gender varchar(10),
    ADD COLUMN date_of_birth date,
    ADD COLUMN city varchar(35),
    ADD COLUMN mobile_number varchar(15)
