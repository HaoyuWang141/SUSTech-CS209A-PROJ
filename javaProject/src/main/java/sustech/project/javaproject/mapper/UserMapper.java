package sustech.project.javaproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sustech.project.javaproject.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

 // query all users
 @Select("select * from user")
 public List<User> find();

 @Insert("insert into user (username,password,birthday) values (#{username},#{password},#{birthday})")
 public int insert(User user);

  @Select("select * from t_user")
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "username", property = "username"),
      @Result(column = "password", property = "password"),
      @Result(column = "id", property = "orders", javaType = List.class,
      many = @Many(select = "sustech.project.javaproject.mapper.OrderMapper.selectByUid"))
  })
  List<User> SelectAllUserAndOrders();
}
