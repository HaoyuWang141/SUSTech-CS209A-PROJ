package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@TableName("t_order")
public class Order {
  private int id;
  private int uid;
  @TableField(exist = false)
  private List<User> users;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
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
