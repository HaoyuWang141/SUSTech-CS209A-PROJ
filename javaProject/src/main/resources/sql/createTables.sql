--drop database java2_project;
--create database java2_project;

drop table tag, t_user, answer, comment, question, question_tag;

create table tag
(
    tag_name varchar primary key,
    question_number integer,
    avg_views integer,
    avg_upvotes integer
);

create table t_user
(
    account_id   integer primary key,
    display_name varchar,
    question_number integer,
    answer_number integer,
    comment_number integer
);

create table question
(
    id                 integer primary key,
    owner_id           integer references t_user,
    is_answered        boolean,
    has_accepted_answer boolean,
    view_count          integer,
    upvotes            integer,
    creation_date      timestamp,
    answer_count       integer,
    comment_count      integer
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

drop table api;
create table api
(
    name varchar primary key,
    count integer
);