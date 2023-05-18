package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Question;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

  @Select("select * from questions")
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "userID", property = "userID"),
      @Result(column = "postTime", property = "postTime"),
      @Result(column = "votes", property = "votes"),
      @Result(column = "views", property = "views"),
      @Result(column = "isAnswered", property = "isAnswered"),
      @Result(column = "acceptedAnswerID", property = "acceptedAnswerID"),
      @Result(column = "id", property = "answers",
          many = @Many(select = "sustech.project.javaproject.mapper.AnswerMapper.selectByQuestionID")),
      @Result(column = "id", property = "tags",
          many = @Many(select = "sustech.project.javaproject.mapper.TagMapper.selectByQuestionID"))
  })
  List<Question> selectQuestions(QueryWrapper<Question> wrapper);

  @Select("")
  Long avgAnswerNum();

  @Select("")
  Long minAnswerNum();

  @Select("")
  Long maxAnswerNum();

  @Select("select avg(upvotes) from questions join questions_tags qt on questions.id = qt.questionid where qt.tagName = #{tagName}")
  int selectAvgUpvotesByTag(String tagName);

  @Select("select avg(views) from questions join questions_tags qt on questions.id = qt.questionid where qt.tagName = #{tagName}")

  int selectAvgViewsByTag(String tagName);
}
