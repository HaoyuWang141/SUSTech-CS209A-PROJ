package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("answers_comments")
public class AnswerComment {

  @TableId(type = IdType.AUTO)
  private Integer answerID;
  private Integer commentID;

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    AnswerComment answerComment = (AnswerComment) o;
    return this.answerID.equals(answerComment.answerID) && this.commentID.equals(
        answerComment.commentID);
  }
}
