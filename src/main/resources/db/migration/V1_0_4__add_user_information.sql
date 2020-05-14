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
	type varchar(255),
	header_id bigint
		constraint fkm3x3dyu4s5gsewbco45q9mtb4
			references header
);

alter table header_child owner to root;

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
	type varchar(255),
	child_id bigint
		constraint fk9d49lhm2qnb8t04ixsvxa3aeh
			references header_child
);

alter table header_child_element owner to root;

