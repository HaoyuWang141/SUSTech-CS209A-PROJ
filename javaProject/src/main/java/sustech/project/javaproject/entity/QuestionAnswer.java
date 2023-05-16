package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("questions_answers")
public class QuestionAnswer {

    @TableId(type = IdType.AUTO)
    private Integer questionID;
    private Integer answerID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAnswer questionAnswer = (QuestionAnswer) o;
        return this.questionID.equals(questionAnswer.questionID) && this.answerID.equals(questionAnswer.answerID);
    }
}
