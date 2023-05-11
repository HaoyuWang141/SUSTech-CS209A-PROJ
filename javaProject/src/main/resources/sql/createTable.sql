drop table t_user, t_order;

create table t_user(
    id serial primary key ,
    username varchar,
    password varchar,
    birthday varchar
);

create table t_order(
    id int primary key ,
    uid int references t_user
);

insert into t_user (username, password, birthday) values ('a','1','aaa'),('b','2','bbb'),('c','3','ccc');
insert into t_order values (1,1),(2,1),(3,2),(4,2),(5,1);