package sustech.project.javaproject.crawler_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Arrays;
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionCrawler {
    private String[] tags;
    private UserCrawler owner;
    private Boolean is_answered;
    private Integer view_count;
    private Integer accepted_answer_id;
    private Integer score;
    private Long creation_date;
    private Integer question_id;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public UserCrawler getOwner() {
        return owner;
    }

    public void setOwner(UserCrawler owner) {
        this.owner = owner;
    }

    public Boolean getIs_answered() {
        return is_answered;
    }

    public void setIs_answered(Boolean is_answered) {
        this.is_answered = is_answered;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Integer getAccepted_answer_id() {
        return accepted_answer_id;
    }

    public void setAccepted_answer_id(Integer accepted_answer_id) {
        this.accepted_answer_id = accepted_answer_id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Long creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

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
