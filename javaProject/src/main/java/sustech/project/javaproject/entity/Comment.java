package sustech.project.javaproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("comments")
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userID;
    private Timestamp postTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return this.id.equals(comment.id);
    }
}
