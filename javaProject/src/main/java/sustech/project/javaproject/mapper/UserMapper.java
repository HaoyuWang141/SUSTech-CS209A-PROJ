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

  @Select("select * from t_user")
  @Results({
      @Result(property = "accountId", column = "account_id"),
      @Result(property = "displayName", column = "display_name"),
      @Result(property = "qestionNum", column = "account_id", one = @One(select = "sustech.project.javaproject.mapper.QuestionMapper.countByUserId")),
      @Result(property = "answerNum", column = "account_id", one = @One(select = "sustech.project.javaproject.mapper.AnswerMapper.countByUserId")),
      @Result(property = "commentNum", column = "account_id", one = @One(select = "sustech.project.javaproject.mapper.CommentMapper.countByUserId"))
  })
  public List<User> selectAll();

  @Select("select * from t_user where account_id = #{id} or account_id = -1")
  @Results({
      @Result(property = "accountId", column = "account_id"),
      @Result(property = "questionNum", column = "account_id", one = @One(select = "sustech.project.javaproject.mapper.QuestionMapper.countByUserId")),
      @Result(property = "answerNum", column = "account_id", one = @One(select = "sustech.project.javaproject.mapper.AnswerMapper.countByUserId")),
      @Result(property = "commentNum", column = "account_id", one = @One(select = "sustech.project.javaproject.mapper.CommentMapper.countByUserId"))
  })
  public User selectById(long id);
}
