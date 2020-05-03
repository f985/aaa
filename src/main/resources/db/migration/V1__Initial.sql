create table file
(
	id bigserial not null
		constraint file_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	extension varchar(255),
	name varchar(255),
	path varchar(255)
);

alter table file owner to root;

create table product
(
	id bigserial not null
		constraint product_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	available_quantity bigint not null,
	description varchar(255),
	name varchar(255) not null,
	price numeric(21,2) not null,
	type varchar(255) not null
);

alter table product owner to root;

create table image
(
	id bigserial not null
		constraint image_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	url varchar(255) not null,
	product_id_id bigint
		constraint fksmno7gwr418h9er61l56f48ns
			references product
);

alter table image owner to root;

create table users
(
	id bigserial not null
		constraint users_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	email varchar(255),
	name varchar(255),
	password varchar(255),
	role varchar(255),
	surname varchar(255)
);

alter table users owner to root;

create table orders
(
	id bigserial not null
		constraint orders_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	status varchar(255) not null,
	user_id bigint
		constraint fk32ql8ubntj5uh44ph9659tiih
			references users
);

alter table orders owner to root;

create table product_in_order
(
	id bigserial not null
		constraint product_in_order_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	description varchar(255),
	quantity bigint not null,
	total_price numeric(21,2) not null,
	order_id bigint
		constraint fkl37w7v97lwh833c4gc2qotnxt
			references orders,
	personalization_id bigint
		constraint uk_srwhg0jwsgtogbwxix2qj9vk4
			unique
		constraint fka4kpj5et1ain1ya6f13m32kcs
			references file,
	product_id bigint
		constraint fk7qpekps6q9cyrhax6hfu3m2gm
			references product
);

alter table product_in_order owner to root;

create table shipment
(
	id bigserial not null
		constraint shipment_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	address varchar(255) not null,
	carrier varchar(255),
	date timestamp not null,
	recipient_name varchar(255) not null,
	type varchar(255) not null,
	order_id bigint
		constraint uk_p06cong2injx54ipykoegys3w
			unique
		constraint fk8amw90d62x67honrwucvjado7
			references orders
);

alter table shipment owner to root;

