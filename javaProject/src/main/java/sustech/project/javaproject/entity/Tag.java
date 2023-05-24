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
  private Integer questionNum;
  @TableField(exist = false)
  private Integer views;

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public Integer getUpvotes() {
    return upvotes;
  }

  public void setUpvotes(Integer upvotes) {
    this.upvotes = upvotes;
  }

  public Integer getQuestionNum() {
    return questionNum;
  }

  public void setQuestionNum(Integer questionNum) {
    this.questionNum = questionNum;
  }

  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
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
