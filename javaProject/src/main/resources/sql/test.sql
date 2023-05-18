select * from answers join questions_answers on id = answerID where id = 1;
select * from answers;
select * from questions_answers;

select avg(upvotes) from questions join questions_tags qt on questions.id = qt.questionid where qt.tagName = '';

select * from comments join answers_comments ac on comments.id = ac.commentID;