package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.TableId;

public class Api {
  @TableId
  private String name;
  private Integer count;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
