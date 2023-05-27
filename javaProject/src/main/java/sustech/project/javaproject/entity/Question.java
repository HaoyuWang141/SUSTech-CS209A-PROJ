package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import sustech.project.javaproject.config.TimestampDeserializer;

@TableName("question")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Question {

  @TableId(type = IdType.AUTO)
  @JsonProperty("question_id")
  private Integer id;

  private User owner;

  @JsonProperty("is_answered")
  private boolean isAnswered;

  private Integer viewCount;

  @TableField(exist = false)
  private Integer acceptedAnswerId;

  @JsonProperty("score")
  private Integer upvotes;

  @JsonDeserialize(using = TimestampDeserializer.class)
  private Timestamp creationDate;

  @TableField(exist = false)
  private List<Answer> answers;

  @TableField(exist = false)
  private List<Comment> comments;

  @TableField(exist = false)
  private List<String> tags;

  public Question() {
    answers = new ArrayList<>();
    comments = new ArrayList<>();
    tags = new ArrayList<>();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public boolean getIsAnswered() {
    return isAnswered;
  }

  public void setIsAnswered(boolean isAnswered) {
    this.isAnswered = isAnswered;
  }

  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  public Integer getAcceptedAnswerId() {
    return acceptedAnswerId;
  }

  public void setAcceptedAnswerId(Integer acceptedAnswerId) {
    this.acceptedAnswerId = acceptedAnswerId;
  }

  public Integer getUpvotes() {
    return upvotes;
  }

  public void setUpvotes(Integer upvotes) {
    this.upvotes = upvotes;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return id.equals(question.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    Optional<User> owner = Optional.ofNullable(this.owner);
    return "Question{" +
        "id=" + id +
        ", owner=" + (owner.isPresent() ? owner.get() : "null") +
        ", isAnswered=" + isAnswered +
        ", viewCount=" + viewCount +
        ", acceptedAnswerId=" + acceptedAnswerId +
        ", upvotes=" + upvotes +
        ", creationDate=" + creationDate +
        ", answers=" + answers.toString() +
        ", comments=" + comments.toString() +
        ", tags=" + tags.toString() +
        '}';
  }
}
