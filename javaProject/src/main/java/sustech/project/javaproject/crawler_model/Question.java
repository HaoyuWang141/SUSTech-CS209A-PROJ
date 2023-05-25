package sustech.project.javaproject.crawler_model;

import java.sql.Timestamp;
import java.util.Arrays;

public class Question {
    private String[] tags;
    private User owner;
    private Boolean is_answered;
    private Integer view_count;
    private Integer accepted_answer_id;
    private Integer score;
    private Long creation_date;
    private Integer question_id;

    @Override
    public String toString() {
        return "Question{" +
                "tags=" + Arrays.toString(tags) +
                ", owner=" + owner +
                ", is_answered=" + is_answered +
                ", view_count=" + view_count +
                ", accepted_answer_id=" + accepted_answer_id +
                ", score=" + score +
                ", creation_date=" + creation_date +
                ", question_id=" + question_id +
                '}';
    }
}
