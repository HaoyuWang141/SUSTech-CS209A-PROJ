package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Tag;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

  @Select("select * from tags join questions_tags on id = tagID where questionID = #{questionID}")
  List<Tag> selectByQuestionID(long questionID);
}
