--drop database java2_project;
--create database java2_project;

drop table tags, users, answers, comments, questions, questions_answers, questions_tags, answers_comments;

create table tags(
    tag_name varchar primary key
);

create table users(
    id integer primary key
);

create table answers(
    id integer primary key,
    user_i_d integer references users,
    post_time timestamp,
    upvotes integer
);

create table comments(
    id integer primary key,
    user_i_d integer references users,
    post_time timestamp
);

create table questions(
    id integer primary key,
    user_i_d integer references users,
    post_time timestamp,
    upvotes integer,
    views integer,
    is_answered boolean,
    accepted_answer_i_d integer references answers (id)
);

create table questions_tags(
    question_i_d integer references questions (id),
    tag_name varchar references tags,

    primary key (question_i_d, tag_name)
);

create table questions_answers(
    question_i_d integer references questions (id),
    answer_i_d integer references answers (id),

    primary key (question_i_d, answer_i_d)
);

create table answers_comments(
    answer_i_d integer references answers (id),
    comment_i_d integer references comments (id),

    primary key (answer_i_d, comment_i_d)
);