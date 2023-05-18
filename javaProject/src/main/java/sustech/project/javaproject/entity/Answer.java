package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@TableName("answers")
public class Answer {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private Integer userID;
  private Timestamp postTime;

  private Integer upvotes;

  @TableField(exist = false)
  private List<Comment> comments;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public Timestamp getPostTime() {
    return postTime;
  }

  public void setPostTime(Timestamp postTime) {
    this.postTime = postTime;
  }

  public Integer getUpvotes() {
    return upvotes;
  }

  public void setUpvotes(Integer upvotes) {
    this.upvotes = upvotes;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Answer answer = (Answer) o;
    return this.id.equals(answer.id);
  }
}
