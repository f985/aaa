create table header
(
	id bigserial not null
		constraint header_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	disabled boolean not null,
	icon varchar(255),
	name varchar(255),
	order_number integer,
	state varchar(255),
	mega boolean not null,
	type varchar(255)
);

alter table header owner to root;

create table headerchild
(
	id bigserial not null
		constraint headerchild_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	disabled boolean not null,
	icon varchar(255),
	name varchar(255),
	order_number integer,
	state varchar(255),
	type varchar(255),
	header_id bigint
		constraint fk8j83lfwyo1nmr35qw5dnvly53
			references header
);

alter table headerchild owner to root;

create table headerchildelement
(
	id bigserial not null
		constraint headerchildelement_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	disabled boolean not null,
	icon varchar(255),
	name varchar(255),
	order_number integer,
	state varchar(255),
	query_state varchar(255),
	type varchar(255),
	child_id bigint
		constraint fkqb318mof43m5m8ggks0w261qb
			references headerchild
);

alter table headerchildelement owner to root;



INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (1, '2020-05-14 21:27:00.101000', '2020-05-14 21:27:00.101000', false, 'home', 'HOME', 1, 'home', false, 'SUB');
INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (2, '2020-05-14 21:28:38.419000', '2020-05-14 21:28:38.419000', false, 'pages', 'SHOP', 2, '', false, 'SUB');
INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (3, '2020-05-14 21:29:42.923000', '2020-05-14 21:29:42.923000', false, 'party_mode', 'ACCESSORIES', 3,
        'products/accessories', false, 'LINK');
INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (4, '2020-05-14 21:30:35.487000', '2020-05-14 21:30:35.487000', false, 'party_mode', 'CATEGORIES', 4, 'products', true,
        'SUB');
INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (5, '2020-05-14 21:31:49.297000', '2020-05-14 21:31:49.297000', false, 'pages', 'PAGES', 5, 'pages', false, 'SUB');
INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (6, '2020-05-14 21:32:29.811000', '2020-05-14 21:32:29.811000', false, 'perm_contact_calendar', 'CONTACT US', 6, 'contact',
        false, 'LINK');
INSERT INTO header (id, created_at, updated_at, disabled, icon, name, order_number, state, mega, type)
VALUES (7, '2020-05-14 21:33:11.180000', '2020-05-14 21:33:11.180000', false, 'perm_identity', 'ADMIN PANEL', 7, 'admin-panel',
        false, 'LINK');

INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (1, '2020-05-14 21:36:40.673000', '2020-05-14 21:36:40.673000', false, 'home', 'HOME ONE', 1, 'home', 'LINK', 1);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (2, '2020-05-14 21:37:03.339000', '2020-05-14 21:37:03.339000', false, 'home', 'HOME TWO', 2, 'home-two', 'LINK', 1);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (3, '2020-05-14 21:37:21.024000', '2020-05-14 21:37:21.024000', false, 'home', 'HOME THREE', 3, 'home-three', 'LINK', 1);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (4, '2020-05-14 21:38:19.866000', '2020-05-14 21:38:19.866000', false, 'arrow_right_alt', 'PRODUCT DETAILS', 1,
        'products/men/4', 'LINK', 2);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (5, '2020-05-14 21:39:17.574000', '2020-05-14 21:39:17.574000', false, 'arrow_right_alt', 'CART', 2, 'cart', 'LINK', 2);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (6, '2020-05-14 21:39:40.019000', '2020-05-14 21:39:40.019000', false, 'arrow_right_alt', 'CHECKOUT', 3, 'checkout',
        'LINK', 2);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (7, '2020-05-14 21:40:00.847000', '2020-05-14 21:40:00.847000', false, 'arrow_right_alt', 'PAYMENT', 4, 'checkout/payment',
        'LINK', 2);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (8, '2020-05-14 21:41:20.654000', '2020-05-14 21:41:20.654000', false, 'arrow_right_alt', 'MEN', 1, 'men', 'SUB', 4);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (9, '2020-05-14 21:41:42.782000', '2020-05-14 21:41:42.782000', false, 'arrow_right_alt', 'WOMEN', 2, 'woman', 'SUB', 4);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (10, '2020-05-14 21:42:00.320000', '2020-05-14 21:42:00.320000', false, 'arrow_right_alt', 'GADGETS', 3, 'gadgets', 'SUB',
        4);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (11, '2020-05-14 22:13:52.362000', '2020-05-14 22:13:52.362000', false, 'arrow_right_alt', 'ABOUT', 1, 'about', 'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (12, '2020-05-14 22:18:02.907000', '2020-05-14 22:18:02.907000', false, 'arrow_right_alt', 'TERM AND CONDITION', 2,
        'term-condition', 'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (13, '2020-05-14 22:18:31.177000', '2020-05-14 22:18:31.177000', false, 'arrow_right_alt', 'PRIVACY POLICY', 3,
        'privacy-policy', 'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (14, '2020-05-14 22:19:04.976000', '2020-05-14 22:19:04.976000', false, 'arrow_right_alt', 'BLOG DETAIL', 4,
        'blogs/detail', 'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (15, '2020-05-14 22:19:22.149000', '2020-05-14 22:19:22.149000', false, 'arrow_right_alt', 'FAQ', 5, 'faq', 'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (16, '2020-05-14 22:19:39.607000', '2020-05-14 22:19:39.607000', false, 'arrow_right_alt', '404 PAGE', 6, 'not-found',
        'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (17, '2020-05-14 22:19:58.173000', '2020-05-14 22:19:58.173000', false, 'arrow_right_alt', 'UserDetails Profile', 7, 
        'account/profile', 'LINK', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (18, '2020-05-14 22:20:36.791000', '2020-05-14 22:20:36.791000', false, 'supervised_user_circle', 'SESSION', 8, 'session',
        'SUB_CHILD', 5);
INSERT INTO headerchild (id, created_at, updated_at, disabled, icon, name, order_number, state, type, header_id)
VALUES (19, '2020-05-14 22:33:28.345000', '2020-05-14 22:33:28.345000', false, 'arrow_right_alt', 'ACCESSORIES', 4, 'accessories',
        'SUB', 4);

INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (1, '2020-05-14 21:58:55.119000', '2020-05-14 21:58:55.119000', false, 'arrow_right_alt', 'JEAN', 1, 'products/men',
        'Jeans', 'QUERY_PARAMS', 8);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (2, '2020-05-14 21:59:12.593000', '2020-05-14 21:59:12.593000', false, 'arrow_right_alt', 'JACKETS', 2, 'products/men',
        'Jackets', 'QUERY_PARAMS', 8);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (3, '2020-05-14 22:00:05.836000', '2020-05-14 22:00:05.836000', false, 'arrow_right_alt', 'SHIRT', 3, 'products/men',
        'Shirt', 'QUERY_PARAMS', 8);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (4, '2020-05-14 22:00:24.190000', '2020-05-14 22:00:24.190000', false, 'arrow_right_alt', 'T-SHIRT', 4, 'products/men',
        'T-Shirt', 'QUERY_PARAMS', 8);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (5, '2020-05-14 22:02:09.074000', '2020-05-14 22:02:09.074000', false, 'arrow_right_alt', 'DRESS', 1, 'products/woman',
        'Dresses', 'QUERY_PARAMS', 9);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (6, '2020-05-14 22:02:25.024000', '2020-05-14 22:02:25.024000', false, 'arrow_right_alt', 'SHIRT', 2, 'products/woman',
        'Shirt', 'QUERY_PARAMS', 9);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (7, '2020-05-14 22:02:49.114000', '2020-05-14 22:02:49.114000', false, 'arrow_right_alt', 'T-SHIRT', 3, 'products/woman',
        'T-Shirt', 'QUERY_PARAMS', 9);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (8, '2020-05-14 22:03:31.282000', '2020-05-14 22:03:31.282000', false, 'arrow_right_alt', 'HEADPHONE', 1,
        'products/gadgets', 'Headphone', 'QUERY_PARAMS', 10);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (9, '2020-05-14 22:04:10.787000', '2020-05-14 22:04:10.787000', false, 'arrow_right_alt', 'SMARTPHONE', 2,
        'products/gadgets', 'Smartphone', 'QUERY_PARAMS', 10);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (10, '2020-05-14 22:04:24.245000', '2020-05-14 22:04:24.245000', false, 'arrow_right_alt', 'WATCH', 3, 'products/gadgets',
        'Watch', 'QUERY_PARAMS', 10);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (11, '2020-05-14 22:04:34.940000', '2020-05-14 22:04:34.940000', false, 'arrow_right_alt', 'SPEAKER', 4,
        'products/gadgets', 'Speaker', 'QUERY_PARAMS', 10);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (12, '2020-05-14 22:05:04.178000', '2020-05-14 22:05:04.178000', false, 'arrow_right_alt', 'LAPTOP ACCESSORIES', 1,
        'products/accessories', 'Laptap', 'QUERY_PARAMS', 19);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (13, '2020-05-14 22:05:29.617000', '2020-05-14 22:05:29.617000', false, 'arrow_right_alt', 'JEWELLERY', 2,
        'products/accessories', 'Jewellery', 'QUERY_PARAMS', 19);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (14, '2020-05-14 22:24:43.232000', '2020-05-14 22:24:43.232000', false, 'arrow_right_alt', 'SIGN IN', 1, 'session/signin',
        null, 'LINK', 18);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (15, '2020-05-14 22:25:30.827000', '2020-05-14 22:25:30.827000', false, 'arrow_right_alt', 'REGISTER', 2, 'session/signup',
        null, 'LINK', 18);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (16, '2020-05-14 22:25:53.523000', '2020-05-14 22:25:53.523000', false, 'arrow_right_alt', 'FORGET PASSWORD', 3,
        'session/forgot-password', null, 'LINK', 18);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (17, '2020-05-14 22:26:14.789000', '2020-05-14 22:26:14.789000', false, 'arrow_right_alt', 'THANK YOU', 4,
        'session/thank-you', null, 'LINK', 18);
INSERT INTO headerchildelement (id, created_at, updated_at, disabled, icon, name, order_number, state, query_state, type, child_id)
VALUES (18, '2020-05-14 22:05:04.178000', '2020-05-14 22:05:04.178000', false, 'arrow_right_alt', 'BELTS', 3,
        'products/accessories', 'Belts', 'QUERY_PARAMS', 19);
