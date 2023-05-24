package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

  @Select("select * from users")
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "qestionNum", column = "id", one = @One(select = "sustech.project.javaproject.mapper.QuestionMapper.countByUserId")),
      @Result(property = "answerNum", column = "id", one = @One(select = "sustech.project.javaproject.mapper.AnswerMapper.countByUserId")),
      @Result(property = "commentNum", column = "id", one = @One(select = "sustech.project.javaproject.mapper.CommentMapper.countByUserId"))
  })
  public List<User> selectAll();
}
