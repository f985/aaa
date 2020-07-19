create table contact
(
    id         bigserial    not null
        constraint contact_pkey
            primary key,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    address    text         not null,
    call       text         not null,
    info       text         not null,
    mail       varchar(255) not null
);

alter table contact
    owner to root;

INSERT INTO contact (created_at, updated_at, address, call, info, mail) VALUES ('2020-07-18 14:01:26.555518', '2020-07-18 14:01:26.555518', '1899 Cemetery Street,MEDFORD - 1452451 New York', '+123 90394903', 'If you have any problems,suggestions and feedback then please feel free to contact with us. Choose our communication soruces. We will love to hear from you.', 'support@theironnetwork.org');
