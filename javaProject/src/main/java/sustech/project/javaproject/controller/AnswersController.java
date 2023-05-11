package sustech.project.javaproject.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Answer;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswersController {

  @GetMapping("/getHasAnswerAnalysis")
  public String hasAnsAnalysis() {
    return null;
  }

  @GetMapping("/getAnswerNumAnalysis")
  public int[] ansNumAnalysis() { // int[i] means the number of questions that num(answer of this question)==0
    return null;
  }

  @GetMapping("/getByStatus")
  public List<Answer> getAnswers(String status) {
    return null;
  }

}
