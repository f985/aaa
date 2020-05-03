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

create table product
(
	id bigserial not null
		constraint product_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	description varchar(255),
	name varchar(255),
	quantity varchar(255),
	type varchar(255),
	created_by bigint
		constraint fkstb290bdq1jf21dnnc91ap27p
			references users
);

