package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@TableName("t_order")
public class Order {
  private Integer id;
  private Integer uid;
  @TableField(exist = false)
  private List<User> users;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", uid=" + uid +
        ", users=" + users +
        '}';
  }
}
