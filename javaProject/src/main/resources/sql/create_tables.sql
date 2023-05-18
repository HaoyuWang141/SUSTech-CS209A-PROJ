drop database java2_project;
create database java2_project;

drop table tags, users, answers, comments, questions, questions_answers, questions_tags, answers_comments;

create table tags(
    tagName varchar primary key
);

create table users(
    userID integer primary key
);

create table answers(
    id integer primary key,
    userID integer references users,
    postTime timestamp,
    upvotes integer
);

create table comments(
    id integer primary key,
    userID integer references users,
    postTime timestamp
);

create table questions(
    id integer primary key,
    userID integer references users,
    postTime timestamp,
    upvotes integer,
    views integer,
    isAnswered boolean,
    acceptedAnswerID integer references answers (id)
);

create table questions_tags(
    questionID integer references questions (id),
    tagName varchar references tags,

    primary key (questionID, tagName)
);

create table questions_answers(
    questionID integer references questions (id),
    answerID integer references answers (id),

    primary key (questionID, answerID)
);

create table answers_comments(
    answerID integer references answers (id),
    commentID integer references comments (id),

    primary key (answerID, commentID)
);