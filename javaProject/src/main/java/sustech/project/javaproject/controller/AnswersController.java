package sustech.project.javaproject.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.model.Analysis.AnswerNumAnalysis;
import sustech.project.javaproject.model.Analysis.HasAnswerAnalysis;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswersController {

  @GetMapping("/getNum")
  public int getNum(String status){
    int result = -1;
    switch (status){
      case "all": break;
      case "hasAnswer":break;
      case "hasAcceptedAnswer": break;
      case "avgAnswerNum": break;
      case "maxAnswerNum": break;
    }
    return result;
  }

  @GetMapping("/QuestionNum-AnswerNum")
  public Map<Integer,Integer> ansNumDistribution() { // int[i] means the number of questions that num(answer of this question)==0
    return null;
  }

  @GetMapping("/getResolutionTime")
  public Map<String, Integer> resolutionAnalysis(){
    return null;
  }

  @GetMapping("/getByStatus")
  public List<Answer> getAnswers(String status) {
    return null;
  }

}
