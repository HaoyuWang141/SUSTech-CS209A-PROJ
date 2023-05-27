package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Objects;

@TableName("t_user")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {

  @TableId
  @JsonProperty("account_id")
  private Integer accountId;

  @JsonProperty("display_name")
  private String displayName;

  @TableField(exist = false)
  private Integer questionNum;

  @TableField(exist = false)
  private Integer answerNum;

  @TableField(exist = false)
  private Integer commentNum;

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Integer getQuestionNum() {
    return questionNum;
  }

  public void setQuestionNum(Integer questionNum) {
    this.questionNum = questionNum;
  }

  public Integer getAnswerNum() {
    return answerNum;
  }

  public void setAnswerNum(Integer answerNum) {
    this.answerNum = answerNum;
  }

  public Integer getCommentNum() {
    return commentNum;
  }

  public void setCommentNum(Integer commentNum) {
    this.commentNum = commentNum;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return accountId.equals(user.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }

  @Override
  public String toString() {
    return "User{" +
        "accountId=" + accountId +
        ", displayName='" + displayName + '\'' +
        ", questionNum=" + questionNum +
        ", answerNum=" + answerNum +
        ", commentNum=" + commentNum +
        '}';
  }
}
