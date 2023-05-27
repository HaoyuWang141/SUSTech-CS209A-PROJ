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

  @Select("select * from question")
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "owner", column = "owner_id", one = @One(select = "sustech.project.javaproject.mapper.UserMapper.selectById")),
      @Result(property = "isAnswered", column = "is_answered"),
      @Result(property = "hasAcceptedAnswer", column = "has_accepted_answer"),
      @Result(property = "viewCount", column = "view_count"),
      @Result(property = "upvotes", column = "upvotes"),
      @Result(property = "creationDate", column = "creation_date"),
      @Result(property = "answers", column = "id", many = @Many(select = "sustech.project.javaproject.mapper.AnswerMapper.selectByQuestionId")),
      @Result(property = "comments", column = "id", many = @Many(select = "sustech.project.javaproject.mapper.CommentMapper.selectByQuestionId")),
      @Result(property = "tags", column = "id", many = @Many(select = "sustech.project.javaproject.mapper.TagMapper.selectByQuestionId")),
  })
  List<Question> selectQuestions(QueryWrapper<Question> wrapper);

  @Select("select avg(t.cnt) from (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;")
  double avgAnswerNum();

  @Select("select min(t.cnt) from (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;")
  double minAnswerNum();

  @Select("select max(t.cnt) from (select count(*) cnt from question join answer on question.id = answer.question_id group by question_id) as t;")
  double maxAnswerNum();

  @Select("select count(*) from question where owner_id = #{userId}")
  int countByUserId(int userId);
}
