package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.sql.Timestamp;
import java.util.Objects;
import sustech.project.javaproject.config.TimestampDeserializer;

@TableName("comment")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Comment {

  @TableId
  @JsonProperty("comment_id")
  private Integer id;

  @JsonProperty("post_id")
  private Integer questionId;

  @TableField(exist = false)
  private User owner;

  @JsonDeserialize(using = TimestampDeserializer.class)
  private Timestamp creationDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    if (owner == null || owner.getAccountId() == null) {
      this.owner = null;
      return;
    }
    this.owner = owner;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return id.equals(comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", questionId=" + questionId +
        ", owner=" + owner +
        ", creationDate=" + creationDate +
        '}';
  }
}
