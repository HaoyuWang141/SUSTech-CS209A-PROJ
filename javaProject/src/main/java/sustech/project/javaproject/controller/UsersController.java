package sustech.project.javaproject.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
import sustech.project.javaproject.entity.User;
import sustech.project.javaproject.mapper.QuestionMapper;
import sustech.project.javaproject.mapper.UserMapper;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private QuestionMapper questionMapper;

  @GetMapping("/usersDistribution")
  public Map<String, Integer> usersDistribution(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    if (type.equals("all")) {
      map.put("0", 0);
      map.put("1-5", 0);
      map.put("6-10", 0);
      map.put(">10", 0);
      List<Question> questions = questionMapper.selectQuestions(null);
      for (Question question : questions) {
        int size = question.getAnswers().size();
        for (Answer answer : question.getAnswers()) {
          size += answer.getComments().size();
        }
        if (size == 0) {
          map.put("0", map.get("0") + 1);
        } else if (size <= 5) {
          map.put("1-5", map.get("1-5") + 1);
        } else if (size <= 10) {
          map.put("6-10", map.get("6-10") + 1);
        } else {
          map.put(">10", map.get(">10") + 1);
        }
      }
    } else if (type.equals("answer")) {
      map.put("0", 0);
      map.put("1-5", 0);
      map.put("6-10", 0);
      map.put(">10", 0);
      List<Question> questions = questionMapper.selectQuestions(null);
      for (Question question : questions) {
        int size = question.getAnswers().size();
        if (size == 0) {
          map.put("0", map.get("0") + 1);
        } else if (size <= 5) {
          map.put("1-5", map.get("1-5") + 1);
        } else if (size <= 10) {
          map.put("6-10", map.get("6-10") + 1);
        } else {
          map.put(">10", map.get(">10") + 1);
        }
      }
    } else if (type.equals("comment")) {
      map.put("0", 0);
      map.put("1-5", 0);
      map.put("6-10", 0);
      map.put(">10", 0);
      List<Question> questions = questionMapper.selectQuestions(null);
      for (Question question : questions) {
        int size = 0;
        for (Answer answer : question.getAnswers()) {
          size += answer.getComments().size();
        }
        if (size == 0) {
          map.put("0", map.get("0") + 1);
        } else if (size <= 5) {
          map.put("1-5", map.get("1-5") + 1);
        } else if (size <= 10) {
          map.put("6-10", map.get("6-10") + 1);
        } else {
          map.put(">10", map.get(">10") + 1);
        }
      }
    }
    return map;
  }

  @GetMapping("/participationAnalysis")
  public Map<String, Integer> getParticipationAnalysis(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    if (type.equals("all")) {

    } else if (type.equals("answer")) {

    } else if (type.equals("comment")) {

    }
    map.put("avg", 10);
    map.put("min", 8);
    map.put("max", 18);
    return map;
  }

  @GetMapping("/UserNum-Time")
  public Map<String, Integer> userNumDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("0", 10);
    map.put("1-5", 20);
    map.put("6-10", 30);
    map.put(">10", 10);
    return map;
  }

  @GetMapping("/isActive")
  public Map<String, Integer> getActiveUser() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("a", 100);
    map.put("b", 60);
    map.put("c", 30);
    return map;
  }
}
