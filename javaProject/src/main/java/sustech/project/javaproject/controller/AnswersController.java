package sustech.project.javaproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Question;
import sustech.project.javaproject.mapper.AnswerMapper;
import sustech.project.javaproject.mapper.QuestionMapper;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswersController {

  @Autowired
  QuestionMapper questionMapper;
  @Autowired
  AnswerMapper answerMapper;

  @GetMapping("/getNum")
  public long getNum(String status) {
    long result = 0;
    switch (status) {
      case "avg":
        result = questionMapper.avgAnswerNum();
        break;
      case "min":
        result = questionMapper.minAnswerNum();
        break;
      case "max":
        result = questionMapper.maxAnswerNum();
        break;
      case "all":
        result = answerMapper.selectCount(null);
        break;
    }
    System.out.println(status + " answer number: " + result);
    return result;
  }

  /**
   * @return: key: AnswerNumRange, value: ThreadNum
   */
  @GetMapping("/QuestionNum-AnswerNum")
  public Map<String, Integer> ansNumDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("<5", 0);
    map.put("6-10", 0);
    map.put("11-20", 0);
    map.put("21-30", 0);
    map.put("31-40", 0);
    map.put("41-50", 0);
    map.put(">50", 0);
    List<Question> questions = questionMapper.selectList(null);
    for (Question question : questions) {
      int ansNum = question.getAnswerCount();
      if (ansNum < 5) {
        map.put("<5", map.get("<5") + 1);
      } else if (ansNum < 10) {
        map.put("6-10", map.get("6-10") + 1);
      } else if (ansNum < 20) {
        map.put("11-20", map.get("11-20") + 1);
      } else if (ansNum < 30) {
        map.put("21-30", map.get("21-30") + 1);
      } else if (ansNum < 40) {
        map.put("31-40", map.get("31-40") + 1);
      } else if (ansNum < 50) {
        map.put("41-50", map.get("41-50") + 1);
      } else {
        map.put(">50", map.get(">50") + 1);
      }
    }
    System.out.println("ansNumDistribution: ");
    System.out.println(map);
    System.out.println();
    return map;
  }

  @GetMapping("/AnswerNum-Time")
  public Map<String, Integer> ansNumDistributionByTime() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("2008-2010", 0);
    map.put("2011-2015", 0);
    map.put("2016-2020", 0);
    map.put("2020-2023", 0);
    List<Answer> answers = answerMapper.selectList(null);
    for (Answer answer : answers) {
      int year = answer.getCreationDate().toLocalDateTime().getYear();
      if (year < 2011) {
        map.put("2008-2010", map.get("2008-2010") + 1);
      } else if (year < 2016) {
        map.put("2011-2015", map.get("2011-2015") + 1);
      } else if (year < 2020) {
        map.put("2016-2020", map.get("2016-2020") + 1);
      } else {
        map.put("2020-2023", map.get("2020-2023") + 1);
      }
    }
    System.out.println("AnswerNum-Time: " + map);
    return map;
  }

  /**
   * @return: key: Resolution Time, value: ThreadNum
   * @ResolutionTime: "1 day", "1 week", "1 month", "1 year", "more than 1 year"
   */
  @GetMapping("/ThreadNum-ResolutionTime")
  public Map<String, Integer> resolutionTimeDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("1s", 0);
    map.put("10 min", 0);
    map.put("1 day", 0);
    map.put("10 day", 0);
    map.put("1 month", 0);
    map.put("6 month", 0);
    map.put("1 year", 0);
    map.put("> 1 year", 0);
    QueryWrapper<Question> wrapper = new QueryWrapper<>();
    wrapper.eq("has_accepted_answer", true);
    List<Question> questions = questionMapper.selectQuestionsWithResolutionTime(wrapper);
    for (Question question : questions) {
      Integer resolutionTime = question.getResolutionTime();
      if (resolutionTime < 10) {
        map.put("1s", map.get("1s") + 1);
      } else if (resolutionTime < 60 * 10) {
        map.put("10 min", map.get("10 min") + 1);
      } else if (resolutionTime < 60 * 60 * 24) {
        map.put("1 day", map.get("1 day") + 1);
      } else if (resolutionTime < 60 * 60 * 24 * 10) {
        map.put("10 day", map.get("10 day") + 1);
      } else if (resolutionTime < 60 * 60 * 24 * 30) {
        map.put("1 month", map.get("1 month") + 1);
      } else if (resolutionTime < 60 * 60 * 24 * 30 * 6) {
        map.put("6 month", map.get("6 month") + 1);
      } else if (resolutionTime < 60 * 60 * 24 * 365) {
        map.put("1 year", map.get("1 year") + 1);
      } else {
        map.put("> 1 year", map.get("> 1 year") + 1);
      }
    }
    return map;
  }
}
