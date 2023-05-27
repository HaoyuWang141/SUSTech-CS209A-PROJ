package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Answer;

@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

  @Select("select * from answer where question_id = #{questionId}")
  @Results({
      @Result(property = "id", column = "answer_id"),
      @Result(property = "questionId", column = "question_id"),
      @Result(property = "owner", column = "owner_id", one = @One(select = "sustech.project.javaproject.mapper.UserMapper.selectById")),
      @Result(property = "creationDate", column = "creation_date"),
      @Result(property = "upvotes", column = "upvotes"),
      @Result(property = "isAccepted", column = "is_accepted"),
  })
  List<Answer> selectByQuestionId(int questionId);

  @Select("select count(*) from answer where owner_id = #{userId}")
  int countByUserId(int userId);
}
