package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Answer;

@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

  @Select("select * from answer join questions_answers on id = answerID where questionID = #{questionID}")
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "userID", property = "userID"),
      @Result(column = "postTime", property = "postTime"),
      @Result(column = "upvotes", property = "upvotes"),
      @Result(column = "id", property = "comments",
          many = @Many(select = "sustech.project.javaproject.mapper.CommentMapper.selectByAnswerID"))
  })
  List<Answer> selectByQuestionID(long questionID);
}
