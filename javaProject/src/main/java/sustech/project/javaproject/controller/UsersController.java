package sustech.project.javaproject.controller;

import java.util.*;

import java.util.stream.Collectors;
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

  public static List<User> USERS;

  @GetMapping("/usersDistribution")
  public Map<String, Integer> usersDistribution(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("0", 0);
    map.put("1-10", 0);
    map.put("11-20", 0);
    map.put("16-20", 0);
    map.put("21-25", 0);
    map.put("26-30", 0);
    map.put("31-35", 0);
    map.put("36-40", 0);
    map.put("41-45", 0);
    map.put("46-50", 0);
    map.put(">50", 0);
    List<Question> questions = questionMapper.selectList(null);
    if (type.equals("all")) {
      for (Question q : questions) {
        int participants = q.getAnswerCount() + q.getCommentCount();
        if (participants == 0) {
          map.put("0", map.get("0") + 1);
        } else if (participants <= 10) {
          map.put("1-10", map.get("1-10") + 1);
        } else if (participants <= 20) {
          map.put("11-20", map.get("11-20") + 1);
        } else if (participants <= 25) {
          map.put("21-25", map.get("21-25") + 1);
        } else if (participants <= 30) {
          map.put("26-30", map.get("26-30") + 1);
        } else if (participants <= 35) {
          map.put("31-35", map.get("31-35") + 1);
        } else if (participants <= 40) {
          map.put("36-40", map.get("36-40") + 1);
        } else if (participants <= 45) {
          map.put("41-45", map.get("41-45") + 1);
        } else if (participants <= 50) {
          map.put("46-50", map.get("46-50") + 1);
        } else {
          map.put(">50", map.get(">50") + 1);
        }
      }
    } else if (type.equals("answer")) {
      for (Question question : questions) {
        int participants = question.getAnswerCount();
        if (participants == 0) {
          map.put("0", map.get("0") + 1);
        } else if (participants <= 10) {
          map.put("1-10", map.get("1-10") + 1);
        } else if (participants <= 20) {
          map.put("11-20", map.get("11-20") + 1);
        } else if (participants <= 25) {
          map.put("21-25", map.get("21-25") + 1);
        } else if (participants <= 30) {
          map.put("26-30", map.get("26-30") + 1);
        } else if (participants <= 35) {
          map.put("31-35", map.get("31-35") + 1);
        } else if (participants <= 40) {
          map.put("36-40", map.get("36-40") + 1);
        } else if (participants <= 45) {
          map.put("41-45", map.get("41-45") + 1);
        } else if (participants <= 50) {
          map.put("46-50", map.get("46-50") + 1);
        } else {
          map.put(">50", map.get(">50") + 1);
        }
      }
    } else if (type.equals("comment")) {
      for (Question question : questions) {
        int participants = question.getCommentCount();
        if (participants == 0) {
          map.put("0", map.get("0") + 1);
        } else if (participants <= 10) {
          map.put("1-10", map.get("1-10") + 1);
        } else if (participants <= 20) {
          map.put("11-20", map.get("11-20") + 1);
        } else if (participants <= 25) {
          map.put("21-25", map.get("21-25") + 1);
        } else if (participants <= 30) {
          map.put("26-30", map.get("26-30") + 1);
        } else if (participants <= 35) {
          map.put("31-35", map.get("31-35") + 1);
        } else if (participants <= 40) {
          map.put("36-40", map.get("36-40") + 1);
        } else if (participants <= 45) {
          map.put("41-45", map.get("41-45") + 1);
        } else if (participants <= 50) {
          map.put("46-50", map.get("46-50") + 1);
        } else {
          map.put(">50", map.get(">50") + 1);
        }
      }
    }
    return map;
  }

  @GetMapping("/participationAnalysis")
  public Map<String, Integer> getParticipationAnalysis(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Integer> userAmounts = new ArrayList<>();
    List<User> users = new ArrayList<>(USERS);
    System.out.println(users);
    if (type.equals("all")) {
      for (User u : users) {
        userAmounts.add(u.getQuestionNumber() + u.getAnswerNumber() + u.getCommentNumber());
      }
    } else if (type.equals("answer")) {
      for (User u : users) {
        userAmounts.add(u.getAnswerNumber());
      }
    } else if (type.equals("comment")) {
      for (User u : users) {
        userAmounts.add(u.getCommentNumber());
      }
    }
    int avg = (int) userAmounts.stream().mapToInt(Integer::intValue).average().orElse(0);
    int min = userAmounts.stream().mapToInt(Integer::intValue).min().orElse(0);
    int max = userAmounts.stream().mapToInt(Integer::intValue).max().orElse(0);
    map.put("avg", avg);
    map.put("min", min);
    map.put("max", max);
    System.out.println(map);
    return map;
  }

  @GetMapping("/isActive")
  public Map<String, Integer> getActiveUser() {
    Map<String, Integer> map = new LinkedHashMap<>();
    List<User> users = userMapper.selectAll();
    users.forEach(
        u -> map.put(u.getDisplayName(),
            u.getQuestionNumber() + u.getAnswerNumber() + u.getCommentNumber()));
    return map.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .limit(7)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
            LinkedHashMap::new));
  }
}
