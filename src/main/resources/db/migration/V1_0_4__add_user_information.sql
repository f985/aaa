create table header
(
	id bigserial not null
		constraint header_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	icon varchar(255),
	name varchar(255),
	state varchar(255),
	mega boolean,
	type varchar(255)
);

alter table header owner to root;

create table header_child
(
	id bigserial not null
		constraint header_child_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	icon varchar(255),
	name varchar(255),
	state varchar(255),
	type varchar(255)
);

alter table header_child owner to root;

create table header_children
(
	header_id bigint not null
		constraint fk6tib1lyn6trs96vh0kowy0syf
			references header,
	children_id bigint not null
		constraint fkd7qw8p4dwi73sayc7r6mal6rr
			references header_child
);

alter table header_children owner to root;

create table header_child_element
(
	id bigserial not null
		constraint header_child_element_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	icon varchar(255),
	name varchar(255),
	state varchar(255),
	type varchar(255)
);

alter table header_child_element owner to root;

create table header_child_children
(
	header_child_id bigint not null
		constraint fk65hfk76k5itbu0dnojuwc3llr
			references header_child,
	children_id bigint not null
		constraint fk2dstl8q4gu2yjjvwfnxks9j1x
			references header_child_element
);

alter table header_child_children owner to root;

