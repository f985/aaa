INSERT INTO users (id, created_at, updated_at, email, password, role, first_name, last_name, gender, date_of_birth,
                   city, mobile_number, reset_date, reset_key, activated, activation_key)
VALUES (1, '2020-05-18 23:44:44.385281', '2020-05-18 23:44:44.385281', 'artur.vaganyan96@gmail.com',
        '$2a$11$iQfTuN94n2ND3FG262oHL.sbUF7OkjQxbL3DZ.9mwoClJqG0fxBk2', 'CUSTOMER', 'Artur', 'Vahanyan', null, null,
        null, null, null, null, false, 'cf2ed372-5ecd-4348-bbfb-eb34fd1fa4f5');

INSERT INTO product (id, created_at, updated_at, available_quantity, description, name, price, type, createdby)
VALUES (1, '2020-05-18 23:42:43.000000', '2020-05-18 23:42:45.000000', 10, 'White Dry Wine', 'Kataro', 10.00, 'WINE', 1);
