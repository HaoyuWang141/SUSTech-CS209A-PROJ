package sustech.project.javaproject.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Comment;
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
    map.put("0", 0);
    map.put("1-5", 0);
    map.put("6-10", 0);
    map.put(">10", 0);
    if (type.equals("all")) {
      List<Question> questions = questionMapper.selectQuestions(null);
      for (Question question : questions) {
        Set<User> users = new HashSet<>();  // FIXME: 此处用HashSet可能导致需重写User的toHash()方法
        for (Answer answer : question.getAnswers()) {
          users.add(userMapper.selectById(answer.getUserID()));
          for (Comment comment : answer.getComments()) {
            users.add(userMapper.selectById(comment.getUserID()));
          }
        }
        if (users.size() == 0) {
          map.put("0", map.get("0") + 1);
        } else if (users.size() <= 5) {
          map.put("1-5", map.get("1-5") + 1);
        } else if (users.size() <= 10) {
          map.put("6-10", map.get("6-10") + 1);
        } else {
          map.put(">10", map.get(">10") + 1);
        }
      }
    } else if (type.equals("answer")) {
      List<Question> questions = questionMapper.selectQuestions(null);
      for (Question question : questions) {
        Set<User> users = new HashSet<>();
        for (Answer answer : question.getAnswers()) {
          users.add(userMapper.selectById(answer.getUserID()));
        }
        if (users.size() == 0) {
          map.put("0", map.get("0") + 1);
        } else if (users.size() <= 5) {
          map.put("1-5", map.get("1-5") + 1);
        } else if (users.size() <= 10) {
          map.put("6-10", map.get("6-10") + 1);
        } else {
          map.put(">10", map.get(">10") + 1);
        }
      }
    } else if (type.equals("comment")) {
      List<Question> questions = questionMapper.selectQuestions(null);
      for (Question question : questions) {
        Set<User> users = new HashSet<>();
        for (Answer answer : question.getAnswers()) {
          for (Comment comment : answer.getComments()) {
            users.add(userMapper.selectById(comment.getUserID()));
          }
        }
        if (users.size() == 0) {
          map.put("0", map.get("0") + 1);
        } else if (users.size() <= 5) {
          map.put("1-5", map.get("1-5") + 1);
        } else if (users.size() <= 10) {
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
    List<Integer> userAmounts = new ArrayList<>();
    List<Question> questions = questionMapper.selectQuestions(null);

    if (type.equals("all")) {
      for (Question question : questions) {
        Set<User> users = new HashSet<>();
        for (Answer answer : question.getAnswers()) {
          users.add(userMapper.selectById(answer.getUserID()));
          for (Comment comment : answer.getComments()) {
            users.add(userMapper.selectById(comment.getUserID()));
          }
        }
        userAmounts.add(users.size());
      }
    } else if (type.equals("answer")) {
      for (Question question : questions) {
        Set<User> users = new HashSet<>();
        for (Answer answer : question.getAnswers()) {
          users.add(userMapper.selectById(answer.getUserID()));
        }
        userAmounts.add(users.size());
      }
    } else if (type.equals("comment")) {
      for (Question question : questions) {
        Set<User> users = new HashSet<>();
        for (Answer answer : question.getAnswers()) {
          for (Comment comment : answer.getComments()) {
            users.add(userMapper.selectById(comment.getUserID()));
          }
        }
        userAmounts.add(users.size());
      }
    }

    long threadAmount = questionMapper.selectCount(null);
    int avg = (int) userAmounts.stream().mapToInt(Integer::intValue).average().orElse(0);
    int min = userAmounts.stream().mapToInt(Integer::intValue).min().orElse(0);
    int max = userAmounts.stream().mapToInt(Integer::intValue).max().orElse(0);
    map.put("avg", avg);
    map.put("min", min);
    map.put("max", max);
    return map;
  }

  @GetMapping("/isActive")
  public Map<String, Integer> getActiveUser() {
    Map<String, Integer> map = new LinkedHashMap<>();
    List<User> users = userMapper.selectAll();
    users.forEach(
        u -> map.put(u.getUsername(), u.getQestionNum() + u.getAnswerNum() + u.getCommentNum()));
    return map.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .limit(10)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
            LinkedHashMap::new));
  }
}
