package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Objects;

@TableName("tag")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Tag {

  @TableId
  private String tagName;

  private Integer questionNumber;

  private Integer avgViews;

  private Integer avgUpvotes;

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public Integer getAvgUpvotes() {
    return avgUpvotes;
  }

  public void setAvgUpvotes(Integer avgUpvotes) {
    this.avgUpvotes = avgUpvotes;
  }

  public Integer getQuestionNumber() {
    return questionNumber;
  }

  public void setQuestionNumber(Integer questionNumber) {
    this.questionNumber = questionNumber;
  }

  public Integer getAvgViews() {
    return avgViews;
  }

  public void setAvgViews(Integer avgViews) {
    this.avgViews = avgViews;
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

  @Override
  public int hashCode() {
    return Objects.hash(tagName);
  }

  @Override
  public String toString() {
    return "Tag{" +
        "tagName='" + tagName + '\'' +
        ", questionNumber=" + questionNumber +
        ", avgViews=" + avgViews +
        ", avgUpvotes=" + avgUpvotes +
        '}';
  }
}
