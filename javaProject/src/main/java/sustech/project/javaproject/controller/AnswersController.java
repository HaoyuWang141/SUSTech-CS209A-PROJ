package sustech.project.javaproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.ArrayList;
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
    }
    System.out.println(result);
    return result;
  }

  /**
   * @return: key: AnswerNumRange, value: ThreadNum
   */
  @GetMapping("/QuestionNum-AnswerNum")
  public Map<String, Integer> ansNumDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("0", 0);
    map.put("1-5", 0);
    map.put("5-10", 0);
    map.put("10-20", 0);
    map.put("20-50", 0);
    map.put(">50", 0);
    List<Question> questions = questionMapper.selectQuestions(null);
    for (Question question : questions) {
      int ansNum = question.getAnswers().size();
      if (ansNum == 0) {
        map.put("0", map.get("0") + 1);
      } else if (ansNum < 5) {
        map.put("<5", map.get("<5") + 1);
      } else if (ansNum < 10) {
        map.put("5-10", map.get("5-10") + 1);
      } else if (ansNum < 20) {
        map.put("10-20", map.get("10-20") + 1);
      } else if (ansNum < 50) {
        map.put("20-50", map.get("20-50") + 1);
      } else {
        map.put(">50", map.get(">50") + 1);
      }
    }
    return map;
  }

  @GetMapping("/AnswerNum-Time")
  public Map<String, Integer> ansNumDistributionByTime() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("<1999", 0);
    map.put("2000-2005", 0);
    map.put("2006-2010", 0);
    map.put("2011-2015", 0);
    map.put("2016-2020", 0);
    map.put(">2020", 0);
    List<Answer> answers = answerMapper.selectList(null);
    for (Answer answer : answers) {
      int year = answer.getPostTime().toLocalDateTime().getYear();
      if (year < 1999) {
        map.put("<1999", map.get("<1999") + 1);
      } else if (year < 2006) {
        map.put("2000-2005", map.get("2000-2005") + 1);
      } else if (year < 2011) {
        map.put("2006-2010", map.get("2006-2010") + 1);
      } else if (year < 2016) {
        map.put("2011-2015", map.get("2011-2015") + 1);
      } else if (year < 2021) {
        map.put("2016-2020", map.get("2016-2020") + 1);
      } else {
        map.put(">2020", map.get(">2020") + 1);
      }
    }
    return map;
  }

  /**
   * @return: key: Resolution Time, value: ThreadNum
   * @ResolutionTime: "1 day", "10 days", "1 month", "6 months", "1 year", "more than 1 year"
   */
  @GetMapping("/ThreadNum-ResolutionTime")
  public Map<String, Integer> resolutionTimeDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("<1 day", 0);
    map.put("<1 week", 0);
    map.put("<1 month", 0);
    map.put("<1 year", 0);
    map.put(">1 year", 0);
    QueryWrapper<Question> wrapper = new QueryWrapper<>();
    wrapper.eq("isAnswered", true);
    List<Question> questions = questionMapper.selectQuestions(wrapper);
    for (Question question : questions) {
      Answer acceptedAnswer = answerMapper.selectById(question.getAcceptedAnswerID());
      int resolutionTime = (int) (
          (acceptedAnswer.getPostTime().getTime() - question.getPostTime().getTime()) / (1000 * 60
              * 60 * 24));
      if (resolutionTime < 1) {
        map.put("<1 day", map.get("<1 day") + 1);
      } else if (resolutionTime < 7) {
        map.put("<1 week", map.get("<1 week") + 1);
      } else if (resolutionTime < 30) {
        map.put("<1 month", map.get("<1 month") + 1);
      } else if (resolutionTime < 365) {
        map.put("<1 year", map.get("<1 year") + 1);
      } else {
        map.put(">1 year", map.get(">1 year") + 1);
      }
    }
    return map;
  }
}
