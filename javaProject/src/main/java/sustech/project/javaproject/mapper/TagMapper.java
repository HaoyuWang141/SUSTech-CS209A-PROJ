package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Tag;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

  @Select("select tag_name from tag natural join question_tag where question_id = #{questionId}")
  @Results({
      @Result(property = "tagName", column = "tag_name"),
  })
  List<Tag> selectByQuestionId(int questionId);
}
