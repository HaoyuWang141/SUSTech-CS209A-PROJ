package sustech.project.javaproject.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.User;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

  @GetMapping("/usersDistribution")
  public Map<String, Integer> usersDistribution(String type) {
    Map<String, Integer> map = new LinkedHashMap<>();
    if (type.equals("all")) {

    } else if (type.equals("answer")) {

    } else if (type.equals("comment")) {

    }
    map.put("0", 10);
    map.put("1-5", 20);
    map.put("6-10", 30);
    map.put(">10", 10);
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
