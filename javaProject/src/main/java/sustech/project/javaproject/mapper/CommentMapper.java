package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

  @Select("select * from comment where question_iD = #{questionID}")
  @Results({
      @Result(property = "id", column = "comment_id"),
      @Result(property = "questionId", column = "question_id"),
      @Result(property = "owner", column = "owner_id", one = @One(select = "sustech.project.javaproject.mapper.UserMapper.selectById")),
      @Result(property = "creationDate", column = "creation_date"),
  })
  List<Comment> selectByQuestionId(int questionID);

  @Select("select count(*) from comment where owner_id = #{userId}")
  int countByUserId(int userId);
}
