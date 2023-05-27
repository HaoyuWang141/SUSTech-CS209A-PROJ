select min(t.cnt) from
    (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;

select max(t.cnt) from
    (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;

select avg(t.cnt) from
    (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;

select tag_name from tag natural join question_tag where question_id = 29500;

















