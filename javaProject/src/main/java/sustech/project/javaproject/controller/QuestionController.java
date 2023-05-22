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
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {

  @Autowired
  QuestionMapper questionMapper;
  @Autowired
  AnswerMapper answerMapper;

  @GetMapping("/getNum")
  public long getNum(String status) {
    long result = 0;
    QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
    switch (status) {
      case "all":
        result = questionMapper.selectCount(null);
        break;
      case "hasAnswer":
        queryWrapper.eq("isAnswered", true);
        result = questionMapper.selectCount(queryWrapper);
        break;
      case "hasAcceptedAnswer":
        queryWrapper.ne("acceptedAnswerID", null);
        result = questionMapper.selectCount(queryWrapper);
        break;
      case "moreUpvotes":
        queryWrapper.ne("acceptedAnswerID", null);
        List<Question> questions = questionMapper.selectQuestions(queryWrapper);
        for (Question question : questions) {
          Answer acceptedAnswer = answerMapper.selectById(question.getAcceptedAnswerID());
          for (Answer answer : question.getAnswers()) {
            if (answer.getUpvotes() > acceptedAnswer.getUpvotes()) {
              result++;
              break;
            }
          }
        }
        break;
    }
    return result;
  }

  /**
   * @return: key: time("mm,yyyy"), value: ThreadNum (per month)
   */
  @GetMapping("/QuestionNum-Time")
  public Map<String, Integer> questionNumDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("<1999", 0);
    map.put("2000-2005", 0);
    map.put("2006-2010", 0);
    map.put("2011-2015", 0);
    map.put("2016-2020", 0);
    map.put(">2020", 0);
    List<Question> questions = questionMapper.selectList(null);
    for (Question question : questions) {
      String year = question.getPostTime().toString().substring(0, 4);
      if (year.compareTo("1999") < 0) {
        map.put("<1999", map.get("<1999") + 1);
      } else if (year.compareTo("2000") >= 0 && year.compareTo("2005") <= 0) {
        map.put("2000-2005", map.get("2000-2005") + 1);
      } else if (year.compareTo("2006") >= 0 && year.compareTo("2010") <= 0) {
        map.put("2006-2010", map.get("2006-2010") + 1);
      } else if (year.compareTo("2011") >= 0 && year.compareTo("2015") <= 0) {
        map.put("2011-2015", map.get("2011-2015") + 1);
      } else if (year.compareTo("2016") >= 0 && year.compareTo("2020") <= 0) {
        map.put("2016-2020", map.get("2016-2020") + 1);
      } else {
        map.put(">2020", map.get(">2020") + 1);
      }
    }
    return map;
  }

  @GetMapping("/get")
  public List<Question> getQuestions(String status) {
    List<Question> questions = null;
    QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
    switch (status) {
      case "all":
        questions = questionMapper.selectQuestions(null);
        break;
      case "has answer":
        queryWrapper.eq("isAnswered", true);
        questions = questionMapper.selectQuestions(queryWrapper);
        break;
      case "has no answer":
        queryWrapper.eq("isAnswered", false);
        questions = questionMapper.selectQuestions(queryWrapper);
        break;
      case "has accepted answer":
        queryWrapper.ne("acceptedAnswerID", null);
        questions = questionMapper.selectQuestions(queryWrapper);
        break;
      case "has no accepted answer":
        queryWrapper.eq("acceptedAnswerID", null);
        questions = questionMapper.selectQuestions(queryWrapper);
        break;
    }
    return questions;
  }
}
