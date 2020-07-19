-- auto-generated definition
create table address
(
    id         bigserial   not null
        constraint address_pkey
            primary key,
    created_at timestamp   not null,
    updated_at timestamp   not null,
    address    varchar(64),
    building   varchar(64),
    city       varchar(64),
    country    varchar(64),
    state      varchar(64),
    street     varchar(64),
    type       varchar(64) not null,
    zipcode    varchar(16),
    user_id  bigint
        constraint fk9crecabflys1uas80i1xgbqn0
            references users,
    created_by  bigint
    constraint fk9crecabflys1uas80i1xgbqn1
        references users
);

alter table address
    owner to root;

