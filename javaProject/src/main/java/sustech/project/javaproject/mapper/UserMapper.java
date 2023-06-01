package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
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
      @Result(property = "questionNumber", column = "question_number"),
      @Result(property = "answerNumber", column = "answer_number"),
      @Result(property = "commentNumber", column = "comment_number")
  })
  List<User> selectAll();
}
