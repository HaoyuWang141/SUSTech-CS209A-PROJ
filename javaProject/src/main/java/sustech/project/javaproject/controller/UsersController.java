package sustech.project.javaproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

  @GetMapping("/participationAnalysis")
  public String getparticipationAnalysis() {
    return null;
  }

  @GetMapping("/getUserAnalysis")
  public String getUserAnalysis() {
    return null;
  }
}
