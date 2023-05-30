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

  // public static List<User> USERS;

  @GetMapping("/usersDistribution")
  public Map<String, Integer> usersDistribution(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Question> questions = questionMapper.selectList(null);
    if (type.equals("all")) {
      map.put("0", 0);
      for (int i = 1; i <= 191; i += 10) {
        map.put(i + "-" + (i + 9), 0);
      }
      map.put(">200", 0);
      for (Question q : questions) {
        int participants = q.getAnswerCount() + q.getCommentCount();
        if (participants == 0) {
          map.put("0", map.get("0") + 1);
        } else if (participants <= 200) {
          int key = participants / 10 * 10 + 1;
          map.put(key + "-" + (key + 9), map.get(key + "-" + (key + 9)) + 1);
        } else {
          map.put(">200", map.get(">200") + 1);
        }
      }
    } else if (type.equals("answer")) {
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
      map.put("51-55", 0);
      map.put("56-60", 0);
      map.put("61-65", 0);
      map.put("66-70", 0);
      map.put(">70", 0);
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
        } else if (participants <= 55) {
          map.put("51-55", map.get("51-55") + 1);
        } else if (participants <= 60) {
          map.put("56-60", map.get("56-60") + 1);
        } else if (participants <= 65) {
          map.put("61-65", map.get("61-65") + 1);
        } else if (participants <= 70) {
          map.put("66-70", map.get("66-70") + 1);
        } else {
          map.put(">70", map.get(">70") + 1);
        }
      }
    } else if (type.equals("comment")) {
      map.put("0", 0);
      for (int i = 1; i <= 121; i += 10) {
        map.put(i + "-" + (i + 9), 0);
      }
      map.put(">130", 0);
      for (Question question : questions) {
        int participants = question.getCommentCount();
        if (participants == 0) {
          map.put("0", map.get("0") + 1);
        } else if (participants <= 10) {
          map.put("1-10", map.get("1-10") + 1);
        } else if (participants <= 20) {
          map.put("11-20", map.get("11-20") + 1);
        } else if (participants <= 30) {
          map.put("21-30", map.get("21-30") + 1);
        } else if (participants <= 40) {
          map.put("31-40", map.get("31-40") + 1);
        } else if (participants <= 50) {
          map.put("41-50", map.get("41-50") + 1);
        } else if (participants <= 60) {
          map.put("51-60", map.get("51-60") + 1);
        } else if (participants <= 70) {
          map.put("61-70", map.get("61-70") + 1);
        } else if (participants <= 80) {
          map.put("71-80", map.get("71-80") + 1);
        } else if (participants <= 90) {
          map.put("81-90", map.get("81-90") + 1);
        } else if (participants <= 100) {
          map.put("91-100", map.get("91-100") + 1);
        } else if (participants <= 110) {
          map.put("101-110", map.get("101-110") + 1);
        } else if (participants <= 120) {
          map.put("111-120", map.get("111-120") + 1);
        } else if (participants <= 130) {
          map.put("121-130", map.get("121-130") + 1);
        } else {
          map.put(">130", map.get(">130") + 1);
        }
      }
    }
    System.out.println(map);
    return map;
  }

  @GetMapping("/participationAnalysis")
  public Map<String, Integer> getParticipationAnalysis(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Integer> userAmounts = new ArrayList<>();
    List<User> users = userMapper.selectList(null);
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
