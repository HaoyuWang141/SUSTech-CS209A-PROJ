package sustech.project.javaproject.controller;

import java.net.Inet4Address;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Answer;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswersController {

  @GetMapping("/getNum")
  public int getNum(String status) {
    int result = -1;
    switch (status) {
      case "all":
        break;
      case "hasAnswer":
        break;
      case "hasAcceptedAnswer":
        break;
      case "avgAnswerNum":
        break;
      case "maxAnswerNum":
        break;
      case "MoreUpvotes":
        break;
    }
    return result;
  }

  /**
   * @return: key: AnswerNum, value: ThreadNum
   */
  @GetMapping("/ThreadNum-AnswerNum")
  public Map<Integer, Integer> ansNumDistribution() {
    return null;
  }

  /**
   * @return: key: Resolution Time, value: ThreadNum
   * @ResolutionTime: "1 day", "10 days", "1 month", "6 months", "1 year", "more than 1 year"
   */
  @GetMapping("/ThreadNum-ResolutionTime")
  public Map<String, Integer> resolutionTimeDistribution() {
    return null;
  }

  @GetMapping("/getByStatus")
  public List<Answer> getAnswers(String status) {
    switch (status) {
      case "all":break;
      case "has answer":break;
      case "has no answer": break;
      case "has accepted answer": break;
      case "has no accepted answer":break;
    }
    return null;
  }

}
