package sustech.project.javaproject.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threads")
@CrossOrigin
public class ThreadController {

  /**
   * @return: key: time("mm,yyyy"), value: ThreadNum (per month)
   */
  @GetMapping("/ThreadNum-time")
  public Map<String, Integer> threadNumDistribution() {
    return null;
  }
}
