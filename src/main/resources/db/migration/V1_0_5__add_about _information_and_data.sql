create table about_information
(
	id bigserial not null
		constraint about_information_pkey
			primary key,
	created_at timestamp not null,
	updated_at timestamp not null,
	content text not null,
	heading text not null,
	image varchar(255) not null,
	sub_heading text not null
);

alter table about_information owner to root;

INSERT INTO public.about_information (id, created_at, updated_at, content, heading, image, sub_heading) VALUES (1, '2020-06-11 17:01:46.419000', '2020-06-11 17:01:48.419000', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Aliquam dolore neque consequatur harum suscipit hic nisi voluptate nulla deserunt! Ducimus odit nisi aliquam non quae exercitationem? Impedit dolores aliquam suscipit! Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor eaque illo, dolore voluptates ipsum ratione. At eius earum voluptatibus aperiam porro a quo, obcaecati dolor distinctio provident iusto libero ducimus?', 'Providing the optimize E-commerce servicing to millions of customers form last 10 year', 'assets/images/about-us.jpg', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Cupiditate nihil non quo corrupti, laborum hic,');
