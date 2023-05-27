package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Objects;

@TableName("question_tag")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuestionTag {

  @TableId
  private Integer questionId;

  @TableId
  private String tagName;

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

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
    QuestionTag that = (QuestionTag) o;
    return questionId.equals(that.questionId) && tagName.equals(that.tagName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionId, tagName);
  }

  @Override
  public String toString() {
    return "QuestionTag{" +
        "questionId=" + questionId +
        ", tagName='" + tagName + '\'' +
        '}';
  }
}
