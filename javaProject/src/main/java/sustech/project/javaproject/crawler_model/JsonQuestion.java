package sustech.project.javaproject.crawler_model;



import sustech.project.javaproject.crawler_model.JsonUser;

import java.util.Arrays;

public class JsonQuestion {
    private String[] tags;
    private JsonUser owner;
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

    public Integer getQuestion_id() {
        return question_id;
    }

    public Integer getAccepted_answer_id() {
        return accepted_answer_id;
    }

    public Boolean getIs_answered() {
        return is_answered;
    }
}
