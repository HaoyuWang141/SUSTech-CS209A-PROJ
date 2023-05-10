package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
  @Select("select * from t_order where uid = #{uid}")
  List<Order> selectByUid(int uid);
}