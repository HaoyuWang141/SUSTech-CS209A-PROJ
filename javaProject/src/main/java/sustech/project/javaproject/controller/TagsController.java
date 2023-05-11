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

  /**
   *
   * @return key: tag, value: threadNum
   */
  @GetMapping("/threadNum-tag")
  public Map<String, Integer> tagFrequenceDistribution() {
    return null;
  }

  /**
   *
   * @return key: combination of tags, value: average upvotes over thread
   */
  @GetMapping("/avgUpvotes-tags")
  public Map<String, Integer> tagUpvotesDistribution() {
    return null;
  }

  /**
   *
   * @return key: combination of tags, value: average views over thread
   */
  @GetMapping("/avgViews-tags")
  public Map<String, Integer> tagViewsDistribution() {
    return null;
  }
}
