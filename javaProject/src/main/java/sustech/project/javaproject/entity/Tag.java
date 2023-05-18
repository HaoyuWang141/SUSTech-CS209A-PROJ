package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tags")
public class Tag {

  @TableId(type = IdType.AUTO)
  private String tagName;
  @TableField(exist = false)
  private Integer upvotes;
  @TableField(exist = false)
  private Integer threadNum;
  @TableField(exist = false)
  private Integer views;

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tag tag = (Tag) o;
    return this.tagName.equals(tag.tagName);
  }

}
