package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.util.List;

@TableName("questions")
public class Question {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userID;
    private Timestamp postTime;
    private Integer votes;
    private Integer views;
    private Boolean isAnswered;
    private Integer acceptedAnswerID;

    @TableField(exist = false)
    private List<Answer> answers;

    @TableField(exist = false)
    private List<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return this.id.equals(question.id);
    }
}
