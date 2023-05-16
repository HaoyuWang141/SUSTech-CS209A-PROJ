package sustech.project.javaproject.controller;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        result = 100;
        break;
      case "hasAnswer":
        result = 70;
        break;
      case "hasAcceptedAnswer":
        result = 60;
        break;
      case "avgAnswerNum":
        result = 10;
        break;
      case "maxAnswerNum":
        result = 11;
        break;
      case "moreUpvotes":
        result = 12;
        break;
    }
    System.out.println(result);
    return result;
  }

  /**
   * @return: key: AnswerNumRange, value: ThreadNum
   */
  @GetMapping("/ThreadNum-AnswerNum")
  public Map<String, Integer> ansNumDistribution() {
    Map<String,Integer> map = new LinkedHashMap<>();
    map.put("0",10);
    map.put("1-5",20);
    map.put("6-10",30);
    map.put(">10",10);
    return map;
  }

  @GetMapping("/AnswerNum-Time")
  public Map<String, Integer> ansNumDistributionByTime() {
    Map<String,Integer> map = new LinkedHashMap<>();
    map.put("<1900",10);
    map.put("1900-2000",20);
    map.put("2000-2010",30);
    map.put("2010-2020",10);
    map.put(">2020",5);
    return map;
  }

  /**
   * @return: key: Resolution Time, value: ThreadNum
   * @ResolutionTime: "1 day", "10 days", "1 month", "6 months", "1 year", "more than 1 year"
   */
  @GetMapping("/ThreadNum-ResolutionTime")
  public Map<String, Integer> resolutionTimeDistribution() {
    Map<String,Integer> map = new LinkedHashMap<>();
    map.put("<1 day",10);
    map.put("<1 week",10);
    map.put("<1 month",20);
    map.put("<1 year",30);
    map.put(">1 year",10);
    return map;
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
