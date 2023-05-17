package sustech.project.javaproject.controller;

import java.util.LinkedHashMap;
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
   * @return key: tag, value: threadNum
   */
  @GetMapping("/threadNum-tag")
  public Map<String, Integer> tagFrequenceDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("java", 10);
    map.put("python", 20);
    map.put("c++", 30);
    map.put("c", 10);
    map.put("c#", 20);
    return map;
  }

  /**
   * @return key: one tag, value: average upvotes over thread
   */
  @GetMapping("/avgUpvotes-tag")
  public Map<String, Integer> tagUpvotesDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("java", 10);
    map.put("python", 20);
    map.put("c++", 30);
    map.put("c", 10);
    map.put("c#", 20);
    map.put("java1", 10);
    map.put("python1", 20);
    map.put("c++1", 30);
    map.put("c1", 10);
    map.put("c#1", 20);
    map.put("java2", 10);
    map.put("python2", 20);
    map.put("c++2", 30);
    map.put("c2", 10);
    map.put("c#2", 20);
    return map;
  }

  /**
   * @return key: combination of tags, value: average upvotes over thread
   */
  @GetMapping("/avgUpvotes-tags")
  public Map<String, Integer> tagsUpvotesDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("java", 10);
    map.put("python", 20);
    map.put("c++", 30);
    map.put("c", 10);
    map.put("c#", 20);
    return map;
  }

  /**
   * @return key: one tag, value: average views over thread
   */
  @GetMapping("/avgViews-tag")
  public Map<String, Integer> tagViewsDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("java", 10);
    map.put("python", 20);
    map.put("c++", 30);
    map.put("c", 10);
    map.put("c#", 20);
    map.put("java1", 10);
    map.put("python1", 20);
    map.put("c++1", 30);
    map.put("c1", 10);
    map.put("c#1", 20);
    map.put("java2", 10);
    map.put("python2", 20);
    map.put("c++2", 30);
    map.put("c2", 10);
    map.put("c#2", 20);
    return map;
  }

  /**
   * @return key: combination of tags, value: average views over thread
   */
  @GetMapping("/avgViews-tags")
  public Map<String, Integer> tagsViewsDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("java", 10);
    map.put("python", 20);
    map.put("c++", 30);
    map.put("c", 10);
    map.put("c#", 20);
    return map;
  }
}
