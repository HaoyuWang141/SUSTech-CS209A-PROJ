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
import sustech.project.javaproject.JavaProjectApplication;
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
        queryWrapper.eq("is_answered", true);
        result = questionMapper.selectCount(queryWrapper);
        break;
      case "hasAcceptedAnswer":
        queryWrapper.eq("has_accepted_answer", true);
        result = questionMapper.selectCount(queryWrapper);
        break;
      case "moreUpvotes":
        result = questionMapper.countMoreUpvotes();
        break;
    }
    System.out.println(status + " answer number: " + result);
    return result;
  }

  /**
   * @return: key: time("mm,yyyy"), value: ThreadNum (per month)
   */
  @GetMapping("/QuestionNum-Time")
  public Map<String, Integer> questionNumDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("2008-2010", 0);
    map.put("2011-2015", 0);
    map.put("2016-2020", 0);
    map.put("2020-2023", 0);
    List<Question> questions = questionMapper.selectList(null);
    for (Question question : questions) {
      String year = question.getCreationDate().toString().substring(0, 4);
      if (year.compareTo("2008") >= 0 && year.compareTo("2010") <= 0) {
        map.put("2008-2010", map.get("2008-2010") + 1);
      } else if (year.compareTo("2011") >= 0 && year.compareTo("2015") <= 0) {
        map.put("2011-2015", map.get("2011-2015") + 1);
      } else if (year.compareTo("2016") >= 0 && year.compareTo("2020") <= 0) {
        map.put("2016-2020", map.get("2016-2020") + 1);
      } else if (year.compareTo("2020") >= 0 && year.compareTo("2023") <= 0) {
        map.put("2020-2023", map.get("2020-2023") + 1);
      }
    }
    System.out.println("QuestionNum-Time: " + map);
    return map;
  }

  @GetMapping("/get")
  public List<Question> getQuestions(String status) {
    List<Question> questions = new ArrayList<>();
    QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
    switch (status) {
      case "all":
        break;
      case "has answer":
        questions = questions.stream().filter(Question::getIsAnswered).toList();
        break;
      case "has no answer":
        questions = questions.stream().filter(q -> !q.getIsAnswered()).toList();
        break;
      case "has accepted answer":
        questions = questions.stream().filter(Question::getHasAcceptedAnswer).toList();
        break;
      case "has no accepted answer":
        questions = questions.stream().filter(q -> !q.getHasAcceptedAnswer()).toList();
        break;
      default:
        return null;
    }
    return questions;
  }
}
