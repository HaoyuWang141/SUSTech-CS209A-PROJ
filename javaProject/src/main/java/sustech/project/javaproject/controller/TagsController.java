package sustech.project.javaproject.controller;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Question;
import sustech.project.javaproject.entity.Tag;
import sustech.project.javaproject.mapper.QuestionMapper;
import sustech.project.javaproject.mapper.TagMapper;

@RestController
@RequestMapping("/tags")
@CrossOrigin
public class TagsController {

  @Autowired
  private TagMapper tagMapper;
  @Autowired
  private QuestionMapper questionMapper;

  /**
   * @return key: tag, value: threadNum
   */
  @GetMapping("/threadNum-tag")
  public Map<String, Integer> tagFrequenceDistribution() {

    Map<String, Integer> map = new LinkedHashMap<>();

    List<Question> questions = questionMapper.selectQuestions(null);
    for (Question question : questions) {
      for (Tag tag : question.getTags()) {
        if (map.containsKey(tag.getTagName()))
          map.put(tag.getTagName(), map.get(tag.getTagName()) + 1);
        else
          map.put(tag.getTagName(), 1);
      }
    }

    // FIXME
    map.put("java", 5); //
    map.put("cpp", 30); //
    map.put("python", 15); //
    return map;
  }

  /**
   * @return key: one tag, value: average upvotes over thread
   */
  @GetMapping("/avgUpvotes-tag")
  public Map<String, Integer> tagUpvotesDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Tag> tags = tagMapper.selectList(null);
    for (Tag tag : tags) {
      int upvotes = questionMapper.selectAvgUpvotesByTag(tag.getTagName());
      map.put(tag.getTagName(), upvotes);
    }
    return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(50).collect(
        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
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
    List<Tag> tags = tagMapper.selectList(null);
    for (Tag tag : tags) {
      int views = questionMapper.selectAvgViewsByTag(tag.getTagName());
      map.put(tag.getTagName(), views);
    }
    return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(50).collect(
        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
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
