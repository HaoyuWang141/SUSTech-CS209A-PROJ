package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

  @Select("select * from comments join answers_comments ac on comments.id = ac.commentID where answerID = #{answerID}")
  List<Comment> selectByAnswerID(long questionID);
}
