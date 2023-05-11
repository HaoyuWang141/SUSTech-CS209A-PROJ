package sustech.project.javaproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswersController {

  public float noAnswerPercentage(){
    return 0;
  }
  public float avgAnswerNum(){
    return 0;
  }
  public float maxAnswerNum(){
    return 0;
  }
  public int[] answerNumAnalysis(){ // int[i] means the number of questions that num(answer of this question)==0
    return null;
  }

}
