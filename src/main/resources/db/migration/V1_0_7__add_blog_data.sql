create table blogauthor
(
    id         bigserial    not null
        constraint blogauthor_pkey
            primary key,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    bio        text         not null,
    image      varchar(255) not null,
    name       varchar(255) not null,
    post_date  varchar(255) not null
);

alter table blogauthor
    owner to root;

create table blog
(
    id            bigserial    not null
        constraint blog_pkey
            primary key,
    created_at    timestamp    not null,
    updated_at    timestamp    not null,
    banner_img    varchar(255) not null,
    content       text         not null,
    image         varchar(255) not null,
    name          varchar(255) not null,
    post_type     varchar(255) not null,
    short_content text         not null,
    author_id     bigint
        constraint fk42q5481b1asn7c9o3v85362eq
            references blogauthor
);

alter table blog
    owner to root;

create table blogtag
(
    id         bigserial not null
        constraint blogtag_pkey
            primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    name       varchar(255)
        constraint uk_ge4n5bprw86r02iea80ns2d
            unique
);

alter table blogtag
    owner to root;

create table blog_blog_tags
(
    blog_id      bigint not null
        constraint fkh075l9hgoptriglkwq0bpd40a
            references blog,
    blog_tags_id bigint not null
        constraint fkafaa7rmr93t5iasvoi9ejy6uk
            references blogtag
);

alter table blog_blog_tags
    owner to root;

create table usercomment
(
    id         bigserial    not null
        constraint usercomment_pkey
            primary key,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    comment    text         not null,
    date       varchar(255) not null,
    image      varchar(255) not null,
    name       varchar(255) not null,
    blog_id    bigint
        constraint fkpv6vdep77n9vm8hbbf86ppyw8
            references blog
);

alter table usercomment
    owner to root;

create table usersubcomment
(
    id             bigserial    not null
        constraint usersubcomment_pkey
            primary key,
    created_at     timestamp    not null,
    updated_at     timestamp    not null,
    comment        text         not null,
    date           varchar(255) not null,
    image          varchar(255) not null,
    name           varchar(255) not null,
    usercomment_id bigint
        constraint fk4d50qtwg613kwfpkfr95tqhus
            references usercomment
);

alter table usersubcomment
    owner to root;

INSERT INTO public.blogauthor (id, created_at, updated_at, bio, image, name, post_date) VALUES (1, '2020-07-13 21:02:16.992279', '2020-07-13 21:02:16.992279', 'There''s lot of hate out there for a text that amounts to little more than garbled words in an old language. The villagers are out there with a vengeance to get that Frankenstein, wielding torches and pitchforks, wanting to tar and feather it at the least, running it out of town in shame', 'assets/images/user-1.jpg', 'Billy Wade', 'Sept 12, 2019');
INSERT INTO public.blogauthor (id, created_at, updated_at, bio, image, name, post_date) VALUES (2, '2020-07-13 21:02:23.943081', '2020-07-13 21:02:23.943081', 'If the copy becomes distracting in the design then you are doing something wrong or they are discussing copy changes. It might be a bit annoying but you could tell them that that discussion would be best suited for another time.', 'assets/images/user-2.jpg', 'Luke Hobbs', 'Sept 16, 2019');
INSERT INTO public.blogauthor (id, created_at, updated_at, bio, image, name, post_date) VALUES (3, '2020-07-13 21:02:31.647832', '2020-07-13 21:02:31.647832', 'Websites in professional use templating systems. Commercial publishing platforms and content management systems ensure that you can show different text, different data using the same template. When it''s about controlling hundreds of articles, product pages', 'assets/images/user-3.jpg', 'Demeon Phil', 'Sept 14, 2019');

INSERT INTO public.blogtag (id, created_at, updated_at, name) VALUES (1, '2020-07-13 21:02:40.649123', '2020-07-13 21:02:40.649123', 'Shopping');
INSERT INTO public.blogtag (id, created_at, updated_at, name) VALUES (2, '2020-07-13 21:02:43.596444', '2020-07-13 21:02:43.596444', 'Online');
INSERT INTO public.blogtag (id, created_at, updated_at, name) VALUES (3, '2020-07-13 21:02:48.076155', '2020-07-13 21:02:48.076155', 'New Arrival');

INSERT INTO public.blog (id, created_at, updated_at, banner_img, content, image, name, post_type, short_content, author_id) VALUES (1, '2020-07-13 21:03:14.319078', '2020-07-13 21:03:14.331667', 'assets/images/blog-detail-1.jpg', '<p> <span class=''dropcap accent-color''>L</span>orem ipsum dolor sit amet consectetur adipisicing elit. Animi ea libero nobis hic impedit similique, iste possimus. Libero delectus hic tempore suscipit blanditiis? Odit consequuntur facilis debitis quam accusamus ducimus? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p> <p>Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). sometimes on purpose (injected humour and the like). </p> <p> Lorem ipsum dolor sit amet consectetur adipisicing elit. Animi ea libero nobis hic impedit similique, iste possimus. Libero delectus hic tempore suscipit blanditiis? Odit consequuntur facilis debitis quam accusamus ducimus? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p> <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p>', 'assets/images/post-1.jpg', 'Here are some of best online shopping sites for you in europe.', 'Featured post.', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 1);
INSERT INTO public.blog (id, created_at, updated_at, banner_img, content, image, name, post_type, short_content, author_id) VALUES (3, '2020-07-13 21:12:15.857802', '2020-07-13 21:12:15.862908', 'assets/images/blog-detail-3.jpg', '<p> <span class=''dropcap accent-color''>T</span>onsectetur adipisicing elit. Animi ea libero nobis hic impedit similique, iste possimus. Libero delectus hic tempore suscipit blanditiis? Odit consequuntur facilis debitis quam accusamus ducimus? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p> <p>Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). sometimes on purpose (injected humour and the like). </p> <p> Lorem ipsum dolor sit amet consectetur adipisicing elit. Animi ea libero nobis hic impedit similique, iste possimus. Libero delectus hic tempore suscipit blanditiis? Odit consequuntur facilis debitis quam accusamus ducimus? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p> <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p>', 'assets/images/post-3.jpg', 'Festival Sale has lots of amazing offer for men and woman', 'Featured post.', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 2);
INSERT INTO public.blog (id, created_at, updated_at, banner_img, content, image, name, post_type, short_content, author_id) VALUES (2, '2020-07-13 21:08:21.226815', '2020-07-13 21:10:38.316439', 'assets/images/blog-detail-2.jpg', '<p> <span class=''dropcap accent-color''>C</span>onsectetur adipisicing elit. Animi ea libero nobis hic impedit similique, iste possimus. Libero delectus hic tempore suscipit blanditiis? Odit consequuntur facilis debitis quam accusamus ducimus? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p> <p>Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). sometimes on purpose (injected humour and the like). </p> <p> Lorem ipsum dolor sit amet consectetur adipisicing elit. Animi ea libero nobis hic impedit similique, iste possimus. Libero delectus hic tempore suscipit blanditiis? Odit consequuntur facilis debitis quam accusamus ducimus? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p> <p> It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. </p>', 'assets/images/post-2.jpg', 'Get awesome offers on new collection that is available at all stores.', 'Featured post.', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', 3);

INSERT INTO public.blog_blog_tags (blog_id, blog_tags_id) VALUES (1, 1);
INSERT INTO public.blog_blog_tags (blog_id, blog_tags_id) VALUES (1, 2);
INSERT INTO public.blog_blog_tags (blog_id, blog_tags_id) VALUES (2, 1);
INSERT INTO public.blog_blog_tags (blog_id, blog_tags_id) VALUES (2, 3);
INSERT INTO public.blog_blog_tags (blog_id, blog_tags_id) VALUES (3, 1);
INSERT INTO public.blog_blog_tags (blog_id, blog_tags_id) VALUES (3, 3);

INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (1, '2020-07-13 21:17:33.836030', '2020-07-13 21:17:33.836030', 'Do you like Cheese Whiz? Spray tan? Fake eyelashes? That''s what is Lorem Ipsum to many—it rubs them the wrong way, all the way. It''s unreal, uncanny, makes you wonder if something is wrong, it seems to seek your attention for all the wrong reasons. ', 'Sept 13, 2019', 'assets/images/user-2.jpg', 'Jimmy Beck', 1);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (2, '2020-07-13 21:18:20.230116', '2020-07-13 21:18:20.230116', 'You begin with a text, you sculpt information, you chisel away what''s not needed, you come to the point, make things clear, add value, you''re a content person, you like words. Design is no afterthought, far from it, but it comes in a deserved second. ', 'Sept 13, 2019', 'assets/images/user-3.jpg', 'Jessica Reed', 1);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (3, '2020-07-13 21:19:03.222778', '2020-07-13 21:19:03.222778', 'But. A big but: Lorem Ipsum is not t the root of the problem, it just shows what''s going wrong. Chances are there wasn''t collaboration, communication, and checkpoints, there wasn''t a process agreed upon or specified with the granularity required', 'Sept 15, 2019', 'assets/images/user-4.jpg', 'Jeff Mackoay', 1);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (4, '2020-07-13 21:22:42.779599', '2020-07-13 21:22:42.779599', 'Do you like Cheese Whiz? Spray tan? Fake eyelashes? That''s what is Lorem Ipsum to many—it rubs them the wrong way, all the way. It''s unreal, uncanny, makes you wonder if something is wrong, it seems to seek your attention for all the wrong reasons. ', 'Sept 13, 2019', 'assets/images/user-1.jpg', 'Emily Barn', 3);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (5, '2020-07-13 21:23:13.956464', '2020-07-13 21:23:13.956464', 'You begin with a text, you sculpt information, you chisel away what''s not needed, you come to the point, make things clear, add value, you''re a content person, you like words. Design is no afterthought, far from it, but it comes in a deserved second. ', 'Sept 13, 2019', 'assets/images/user-4.jpg', 'Lara Wilson', 3);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (6, '2020-07-13 21:23:49.885243', '2020-07-13 21:23:49.885243', 'But. A big but: Lorem Ipsum is not t the root of the problem, it just shows what''s going wrong. Chances are there wasn''t collaboration, communication, and checkpoints, there wasn''t a process agreed upon or specified with the granularity required', 'Sept 15, 2019', 'assets/images/user-2.jpg', 'Jaby Gibbs', 3);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (7, '2020-07-13 21:24:05.707679', '2020-07-13 21:24:05.707679', 'But. A big but: Lorem Ipsum is not t the root of the problem, it just shows what''s going wrong. Chances are there wasn''t collaboration, communication, and checkpoints, there wasn''t a process agreed upon or specified with the granularity required', 'Sept 15, 2019', 'assets/images/user-2.jpg', 'Jaby Gibbs', 2);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (8, '2020-07-13 21:24:33.403467', '2020-07-13 21:24:33.403467', 'Do you like Cheese Whiz? Spray tan? Fake eyelashes? That''s what is Lorem Ipsum to many—it rubs them the wrong way, all the way. It''s unreal, uncanny, makes you wonder if something is wrong, it seems to seek your attention for all the wrong reasons. ', 'Sept 13, 2019', 'assets/images/user-4.jpg', 'Lara Wilson', 2);
INSERT INTO public.usercomment (id, created_at, updated_at, comment, date, image, name, blog_id) VALUES (9, '2020-07-13 21:25:29.575367', '2020-07-13 21:25:29.575367', 'You begin with a text, you sculpt information, you chisel away what''s not needed, you come to the point, make things clear, add value, you''re a content person, you like words. Design is no afterthought, far from it, but it comes in a deserved second. ', 'Sept 13, 2019', 'assets/images/user-1.jpg', 'Lara Wilson', 2);

INSERT INTO public.usersubcomment (id, created_at, updated_at, comment, date, image, name, usercomment_id) VALUES (1, '2020-07-13 21:20:11.493458', '2020-07-13 21:20:11.493458', 'You begin with a text, you sculpt information, you chisel away what''s not needed, you come to the point, make things clear, add value, you''re a content person, you like words. Design is no afterthought, far from it, but it comes in a deserved second. ', 'Sept 13, 2019', 'assets/images/user-3.jpg', 'Jessica Reed', 1);
INSERT INTO public.usersubcomment (id, created_at, updated_at, comment, date, image, name, usercomment_id) VALUES (2, '2020-07-13 21:20:49.048895', '2020-07-13 21:20:49.048895', 'But. A big but: Lorem Ipsum is not t the root of the problem, it just shows what''s going wrong. Chances are there wasn''t collaboration, communication, and checkpoints, there wasn''t a process agreed upon or specified with the granularity required', 'Sept 15, 2019', 'assets/images/user-4.jpg', 'Jeff Mackoay', 1);
