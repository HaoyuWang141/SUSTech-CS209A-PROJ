select id, answer_count + question.comment_count, answer_count, comment_count
from question;

select owner_id, count(*) from question group by owner_id;

select owner_id, count(*) from answer group by owner_id;

select owner_id, count(*) from comment group by owner_id;

select question.owner_id, answer.owner_id
from question
         join answer on question.id = answer.question_id;





