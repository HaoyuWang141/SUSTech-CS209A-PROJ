package sustech.project.javaproject.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@CrossOrigin
public class TagsController {

  @GetMapping("/withJavaAnalysis")
  public Map<String, Integer> withJavaAnalysis() {
    return null;
  }

  @GetMapping("/getUpvotesAnalysis")
  public String upvotesAnalysis() {
    return null;
  }

  @GetMapping("/getViewsAnalysis")
  public String viewAnalysis() {
    return null;
  }
}
