package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("questions_tags")
public class QuestionTag {

    @TableId(type = IdType.AUTO)
    private Integer questionID;
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTag questionTag = (QuestionTag) o;
        return this.questionID.equals(questionTag.questionID) && this.tagName.equals(questionTag.tagName);
    }
}
