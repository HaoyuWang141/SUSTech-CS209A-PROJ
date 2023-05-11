package sustech.project.javaproject.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acceptedAnswer")
@CrossOrigin
public class AcceptedAnswerController {




  @GetMapping("/getMoreUpvotesPercentage")
  public float getPercentage(){
    return 0;
  }
}
