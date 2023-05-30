select answer_count+comment_count from question;

select answer_count,comment_count,answer_count+question.comment_count from question;
select count(*) from question where question.comment_count>100;


























