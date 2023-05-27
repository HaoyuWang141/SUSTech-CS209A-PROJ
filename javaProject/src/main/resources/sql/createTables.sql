--drop database java2_project;
--create database java2_project;

drop table tag, t_user, answer, comment, question, question_tag;

create table tag
(
    tag_name varchar primary key
);

create table t_user
(
    account_id   integer primary key,
    display_name varchar
);

create table question
(
    id                 integer primary key,
    owner_id           integer references t_user,
    is_answered        boolean,
    viewCount          integer,
    upvotes            integer,
    creation_date      timestamp
);

create table answer
(
    id            integer primary key,
    question_id   integer references question,
    owner_id      integer references t_user,
    creation_date timestamp,
    upvotes       integer,
    is_accepted   boolean
);

create table comment
(
    id            integer primary key,
    question_id   integer references question,
    owner_id      integer references t_user,
    creation_date timestamp
);

create table question_tag
(
    question_id integer references question,
    tag_name    varchar references tag,

    primary key (question_id, tag_name)
);