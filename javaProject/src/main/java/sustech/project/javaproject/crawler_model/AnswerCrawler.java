package sustech.project.javaproject.crawler_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerCrawler {
    private UserCrawler owner;
    private Boolean is_accepted;
    private Integer score;
    private Long creation_date;
    private Integer answer_id;
    private Integer question_id;
}
