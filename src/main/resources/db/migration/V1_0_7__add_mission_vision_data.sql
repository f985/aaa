create table missionvision
(
    id          bigserial    not null
        constraint missionvision_pkey
            primary key,
    created_at  timestamp    not null,
    updated_at  timestamp    not null,
    content     text         not null,
    heading     varchar(255) not null,
    image       varchar(255) not null,
    sub_heading text         not null
);

alter table missionvision
    owner to root;

INSERT INTO public.missionvision (created_at, updated_at, content, heading, image, sub_heading) VALUES ('2020-07-19 00:04:39.438999', '2020-07-19 00:04:39.438999', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quaerat quibusdam cum blanditiis voluptas, voluptates hic eius maxime dolorum saepe quae animi eveniet nulla dolor dicta laborum unde molestias ab magni.Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quaerat quibusdam cum blanditiis voluptas, voluptates hic eius maxime dolorum saepe quae animi eveniet nulla dolor dicta laborum unde molestias ab magni.', 'Our Mission', 'assets/images/our-mission.jpg', 'nulla dolor dicta laborum unde molestias ab magni.Lorem ipsum dolor sit amet,consectetur adipisicing elit.');
INSERT INTO public.missionvision (created_at, updated_at, content, heading, image, sub_heading) VALUES ('2020-07-19 00:04:43.000999', '2020-07-19 00:04:43.000999', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quaerat quibusdam cum blanditiis voluptas, voluptates hic eius maxime dolorum saepe quae animi eveniet nulla dolor dicta laborum unde molestias ab magni.Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quaerat quibusdam cum blanditiis voluptas, voluptates hic eius maxime dolorum saepe quae animi eveniet nulla dolor dicta laborum unde molestias ab magni.', 'Our Mission', 'assets/images/our-mission.jpg', 'nulla dolor dicta laborum unde molestias ab magni.Lorem ipsum dolor sit amet,consectetur adipisicing elit.');
