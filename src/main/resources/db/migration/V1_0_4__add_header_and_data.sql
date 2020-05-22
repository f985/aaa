create table header
(
    id         bigserial not null
        constraint header_pkey
            primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    icon       varchar(255),
    name       varchar(255),
    state      varchar(255),
    mega       boolean,
    type       varchar(255)
);

alter table header
    owner to root;

create table header_child
(
    id         bigserial not null
        constraint header_child_pkey
            primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    icon       varchar(255),
    name       varchar(255),
    state      varchar(255),
    type       varchar(255),
    header_id  bigint
        constraint fkm3x3dyu4s5gsewbco45q9mtb4
            references header
);

alter table header_child
    owner to root;

create table header_child_element
(
    id          bigserial not null
        constraint header_child_element_pkey
            primary key,
    created_at  timestamp not null,
    updated_at  timestamp not null,
    icon        varchar(255),
    name        varchar(255),
    state       varchar(255),
    query_state varchar(255),
    type        varchar(255),
    child_id    bigint
        constraint fk9d49lhm2qnb8t04ixsvxa3aeh
            references header_child
);

alter table header_child_element
    owner to root;

INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (1, '2020-05-14 21:27:00.101000', '2020-05-14 21:27:00.101000', 'home', 'HOME', 'home', false, 'SUB');
INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (2, '2020-05-14 21:28:38.419000', '2020-05-14 21:28:38.419000', 'pages', 'SHOP', '', false, 'SUB');
INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (3, '2020-05-14 21:29:42.923000', '2020-05-14 21:29:42.923000', 'party_mode', 'ACCESSORIES',
        'products/accessories', false, 'LINK');
INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (4, '2020-05-14 21:30:35.487000', '2020-05-14 21:30:35.487000', 'party_mode', 'CATEGORIES', 'products', true,
        'SUB');
INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (5, '2020-05-14 21:31:49.297000', '2020-05-14 21:31:49.297000', 'pages', 'PAGES', 'pages', false, 'SUB');
INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (6, '2020-05-14 21:32:29.811000', '2020-05-14 21:32:29.811000', 'perm_contact_calendar', 'CONTACT US', 'contact',
        false, 'LINK');
INSERT INTO header (id, created_at, updated_at, icon, name, state, mega, type)
VALUES (7, '2020-05-14 21:33:11.180000', '2020-05-14 21:33:11.180000', 'perm_identity', 'ADMIN PANEL', 'admin-panel',
        false, 'LINK');

INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (1, '2020-05-14 21:36:40.673000', '2020-05-14 21:36:40.673000', 'home', 'HOME ONE', 'home', 'LINK', 1);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (2, '2020-05-14 21:37:03.339000', '2020-05-14 21:37:03.339000', 'home', 'HOME TWO', 'home-two', 'LINK', 1);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (3, '2020-05-14 21:37:21.024000', '2020-05-14 21:37:21.024000', 'home', 'HOME THREE', 'home-three', 'LINK', 1);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (4, '2020-05-14 21:38:19.866000', '2020-05-14 21:38:19.866000', 'arrow_right_alt', 'PRODUCT DETAILS',
        'products/men/4', 'LINK', 2);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (5, '2020-05-14 21:39:17.574000', '2020-05-14 21:39:17.574000', 'arrow_right_alt', 'CART', 'cart', 'LINK', 2);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (6, '2020-05-14 21:39:40.019000', '2020-05-14 21:39:40.019000', 'arrow_right_alt', 'CHECKOUT', 'checkout',
        'LINK', 2);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (7, '2020-05-14 21:40:00.847000', '2020-05-14 21:40:00.847000', 'arrow_right_alt', 'PAYMENT', 'checkout/payment',
        'LINK', 2);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (8, '2020-05-14 21:41:20.654000', '2020-05-14 21:41:20.654000', 'arrow_right_alt', 'MEN', 'men', 'SUB', 4);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (9, '2020-05-14 21:41:42.782000', '2020-05-14 21:41:42.782000', 'arrow_right_alt', 'WOMEN', 'woman', 'SUB', 4);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (10, '2020-05-14 21:42:00.320000', '2020-05-14 21:42:00.320000', 'arrow_right_alt', 'GADGETS', 'gadgets', 'SUB',
        4);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (11, '2020-05-14 22:13:52.362000', '2020-05-14 22:13:52.362000', 'arrow_right_alt', 'ABOUT', 'about', 'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (12, '2020-05-14 22:18:02.907000', '2020-05-14 22:18:02.907000', 'arrow_right_alt', 'TERM AND CONDITION',
        'term-condition', 'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (13, '2020-05-14 22:18:31.177000', '2020-05-14 22:18:31.177000', 'arrow_right_alt', 'PRIVACY POLICY',
        'privacy-policy', 'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (14, '2020-05-14 22:19:04.976000', '2020-05-14 22:19:04.976000', 'arrow_right_alt', 'BLOG DETAIL',
        'blogs/detail', 'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (15, '2020-05-14 22:19:22.149000', '2020-05-14 22:19:22.149000', 'arrow_right_alt', 'FAQ', 'faq', 'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (16, '2020-05-14 22:19:39.607000', '2020-05-14 22:19:39.607000', 'arrow_right_alt', '404 PAGE', 'not-found',
        'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (17, '2020-05-14 22:19:58.173000', '2020-05-14 22:19:58.173000', 'arrow_right_alt', 'UserDetails Profile',
        'account/profile', 'LINK', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (18, '2020-05-14 22:20:36.791000', '2020-05-14 22:20:36.791000', 'supervised_user_circle', 'SESSION', 'session',
        'SUB_CHILD', 5);
INSERT INTO header_child (id, created_at, updated_at, icon, name, state, type, header_id)
VALUES (19, '2020-05-14 22:33:28.345000', '2020-05-14 22:33:28.345000', 'arrow_right_alt', 'ACCESSORIES', 'accessories',
        'SUB', 4);

INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (1, '2020-05-14 21:58:55.119000', '2020-05-14 21:58:55.119000', 'arrow_right_alt', 'JEAN', 'products/men',
        'Jeans', 'QUERY_PARAMS', 8);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (2, '2020-05-14 21:59:12.593000', '2020-05-14 21:59:12.593000', 'arrow_right_alt', 'JACKETS', 'products/men',
        'Jackets', 'QUERY_PARAMS', 8);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (3, '2020-05-14 22:00:05.836000', '2020-05-14 22:00:05.836000', 'arrow_right_alt', 'SHIRT', 'products/men',
        'Shirt', 'QUERY_PARAMS', 8);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (4, '2020-05-14 22:00:24.190000', '2020-05-14 22:00:24.190000', 'arrow_right_alt', 'T-SHIRT', 'products/men',
        'T-Shirt', 'QUERY_PARAMS', 8);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (5, '2020-05-14 22:02:09.074000', '2020-05-14 22:02:09.074000', 'arrow_right_alt', 'DRESS', 'products/woman',
        'Dresses', 'QUERY_PARAMS', 9);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (6, '2020-05-14 22:02:25.024000', '2020-05-14 22:02:25.024000', 'arrow_right_alt', 'SHIRT', 'products/woman',
        'Shirt', 'QUERY_PARAMS', 9);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (7, '2020-05-14 22:02:49.114000', '2020-05-14 22:02:49.114000', 'arrow_right_alt', 'T-SHIRT', 'products/woman',
        'T-Shirt', 'QUERY_PARAMS', 9);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (8, '2020-05-14 22:03:31.282000', '2020-05-14 22:03:31.282000', 'arrow_right_alt', 'HEADPHONE',
        'products/gadgets', 'Headphone', 'QUERY_PARAMS', 10);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (9, '2020-05-14 22:04:10.787000', '2020-05-14 22:04:10.787000', 'arrow_right_alt', 'SMARTPHONE',
        'products/gadgets', 'Smartphone', 'QUERY_PARAMS', 10);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (10, '2020-05-14 22:04:24.245000', '2020-05-14 22:04:24.245000', 'arrow_right_alt', 'WATCH', 'products/gadgets',
        'Watch', 'QUERY_PARAMS', 10);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (11, '2020-05-14 22:04:34.940000', '2020-05-14 22:04:34.940000', 'arrow_right_alt', 'SPEAKER',
        'products/gadgets', 'Speaker', 'QUERY_PARAMS', 10);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (12, '2020-05-14 22:05:04.178000', '2020-05-14 22:05:04.178000', 'arrow_right_alt', 'LAPTOP ACCESSORIES',
        'products/accessories', 'Laptap', 'QUERY_PARAMS', 19);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (13, '2020-05-14 22:05:29.617000', '2020-05-14 22:05:29.617000', 'arrow_right_alt', 'JEWELLERY',
        'products/accessories', 'Jewellery', 'QUERY_PARAMS', 19);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (14, '2020-05-14 22:24:43.232000', '2020-05-14 22:24:43.232000', 'arrow_right_alt', 'SIGN IN', 'session/signin',
        null, 'LINK', 18);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (15, '2020-05-14 22:25:30.827000', '2020-05-14 22:25:30.827000', 'arrow_right_alt', 'REGISTER', 'session/signup',
        null, 'LINK', 18);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (16, '2020-05-14 22:25:53.523000', '2020-05-14 22:25:53.523000', 'arrow_right_alt', 'FORGET PASSWORD',
        'session/forgot-password', null, 'LINK', 18);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (17, '2020-05-14 22:26:14.789000', '2020-05-14 22:26:14.789000', 'arrow_right_alt', 'THANK YOU',
        'session/thank-you', null, 'LINK', 18);
INSERT INTO header_child_element (id, created_at, updated_at, icon, name, state, query_state, type, child_id)
VALUES (18, '2020-05-14 22:05:04.178000', '2020-05-14 22:05:04.178000', 'arrow_right_alt', 'BELTS',
        'products/accessories', 'Belts', 'QUERY_PARAMS', 19);
