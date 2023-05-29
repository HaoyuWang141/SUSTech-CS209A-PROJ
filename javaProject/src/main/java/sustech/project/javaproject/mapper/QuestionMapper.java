package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Question;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
  @Select("""
      select question_id, extract(epoch from (answer.creation_date - question.creation_date))as resolution_time
      from question
               join answer on question.id = answer.question_id
      where answer.is_accepted = true;
      """)
  @Results({
      @Result(property = "id", column = "question_id"),
      @Result(property = "resolutionTime", column = "resolution_time"),
  })
  List<Question> selectQuestionsWithResolutionTime(QueryWrapper<Question> wrapper);

  @Select("select * from question")
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "tags", column = "id", many = @Many(select = "sustech.project.javaproject.mapper.TagMapper.selectByQuestionId")),
  })
  List<Question> selectQuestionsWithTags(QueryWrapper<Question> wrapper);

  @Select("select avg(t.cnt) from (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;")
  int avgAnswerNum();

  @Select("select min(t.cnt) from (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;")
  int minAnswerNum();

  @Select("select max(t.cnt) from (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;")
  int maxAnswerNum();

  @Select("select count(*) from question where owner_id = #{userId}")
  int countByUserId(int userId);

  @Select("""
       with t1 as (select question_id, answer.id as answer_id, answer.upvotes, answer.is_accepted
                   from question
                            join answer on question.id = answer.question_id
                   where has_accepted_answer = true),
            t2 as (select question_id, max(upvotes) from t1 group by question_id)
       select count(*)
       from t1
                natural join t2 as t3
       where is_accepted = true
         and upvotes < t3.max;
      """)
  int countMoreUpvotes();
}
