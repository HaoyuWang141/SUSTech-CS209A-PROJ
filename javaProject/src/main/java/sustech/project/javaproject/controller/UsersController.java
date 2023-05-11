package sustech.project.javaproject.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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

  @GetMapping("/participationAnalysis")
  public Map<String, Integer> getParticipationAnalysis(String type, String req) {
    Map<String, Integer> result = null;
    if (type.equals("answer")) {
      switch (req) {
        case "average":
          break;
        case "max":
          break;
        case "min":
          break;
      }
    } else if (type.equals("comment")) {

    }
    return result;
  }

  @GetMapping("/isActive")
  public List<String> getActiveUser() {
    return null;
  }

}
