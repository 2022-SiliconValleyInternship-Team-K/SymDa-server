create database symdadb;

create table diary (
                       diary_id bigint auto_increment,
                       content varchar(1000),
                       weather enum('SUNNY', 'CLOUDY', 'RAINY', 'FOGGY', 'SNOWY'),
                       created_at datetime default current_timestamp,
                       emotion enum('JOY', 'SAD', 'SURPRISED', 'ANGER', 'FEAR', 'HATE', 'NEUTRAL'),
                       month varchar(255),
                       primary key (diary_id),
                       user_id bigint,
                       question_id bigint
);

create table question (
                          question_id bigint auto_increment,
                          question varchar(255),
                          primary key (question_id)
);

create table image (
                       image_id bigint auto_increment,
                       image varchar(255),
                       created_at datetime default current_timestamp,
                       primary key (image_id),
                       diary_id bigint
);

create table comment (
                         comment_id bigint auto_increment,
                         comment varchar(255),
                         music_url varchar(255),
                         primary key (comment_id),
                         diary_id bigint
);

create table user (
                      user_id bigint auto_increment,
                      email varchar(255),
                      primary key (user_id)
);

alter table diary add foreign key(user_id) references user(user_id) on delete cascade;

alter table diary add foreign key(question_id) references question(question_id) on delete cascade;

alter table comment add foreign key(diary_id) references diary(diary_id) on delete cascade;

alter table image add foreign key(diary_id) references diary(diary_id) on delete cascade;