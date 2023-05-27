package sustech.project.javaproject.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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
import sustech.project.javaproject.model.CombinationOfTags;

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
  @GetMapping("/QuestionNum-tag")
  public Map<String, Integer> tagFrequenceDistribution() {

    Map<String, Integer> map = new LinkedHashMap<>();

    List<Question> questions = questionMapper.selectQuestions(null);
    List<Tag> tags = tagMapper.selectList(null);
    for (Question question : questions) {
      for (String tag : question.getTags()) {
        if (map.containsKey(tag)) {
          map.put(tag, map.get(tag) + 1);
        } else {
          map.put(tag, 1);
        }
      }
    }

    // FIXME
    map.put("java", 5); //
    map.put("cpp", 30); //
    map.put("python", 15); //
    map.put("json", 36); //
    map.put("spring", 20); //
    return map;
  }

  /**
   * @param CombinationNum 1, 2, 3
   * @return key: one tag, value: average upvotes over thread
   */
  @GetMapping("/avgUpvotes-tag")
  public Map<String, Double> tagUpvotesDistribution(String CombinationNum) {
    Map<String, Double> map = new LinkedHashMap<>();
    List<Tag> tags = tagMapper.selectList(null);
    List<CombinationOfTags> list = new ArrayList<>();
    List<Question> questions = questionMapper.selectQuestions(null);

    switch (CombinationNum) {
      case "1":
        for (Tag tag : tags) {
          map.put(tag.getTagName(), (double) tag.getUpvotes());
        }
        break;
      case "2":
        tags = tags.stream().sorted(Comparator.comparing(Tag::getUpvotes).reversed())
            .limit(10).collect(Collectors.toList());
        for (int i = 0; i < tags.size(); i++) {
          for (int j = i + 1; j < tags.size(); j++) {
            CombinationOfTags combinationOfTags = new CombinationOfTags();
            combinationOfTags.addTag(tags.get(i));
            combinationOfTags.addTag(tags.get(j));
            list.add(combinationOfTags);
          }
        }
        for (Question q : questions) {
          if (q.getTags().size() < 2) {
            continue;
          }
          for (CombinationOfTags c : list) {
            if (new HashSet<>(q.getTags()).containsAll(c.getTags())) {
              c.setQuestionNum(c.getQuestionNum() + 1);
              c.setUpvotes(c.getUpvotes() + q.getUpvotes());
            }
          }
        }
        for (CombinationOfTags c : list) {
          c.setAvgUpvotes(((double) c.getUpvotes()) / ((double) c.getQuestionNum()));
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getAvgUpvotes());
        }
        break;
      case "3":
        tags = tags.stream().sorted(Comparator.comparing(Tag::getUpvotes).reversed())
            .limit(10).collect(Collectors.toList());
        for (int i = 0; i < tags.size(); i++) {
          for (int j = i + 1; j < tags.size(); j++) {
            for (int k = j + 1; k < tags.size(); k++) {
              CombinationOfTags combinationOfTags = new CombinationOfTags();
              combinationOfTags.addTag(tags.get(i));
              combinationOfTags.addTag(tags.get(j));
              combinationOfTags.addTag(tags.get(k));
              list.add(combinationOfTags);
            }
          }
        }
        for (Question q : questions) {
          if (q.getTags().size() < 3) {
            continue;
          }
          for (CombinationOfTags c : list) {
            if (new HashSet<>(q.getTags()).containsAll(c.getTags())) {
              c.setQuestionNum(c.getQuestionNum() + 1);
              c.setUpvotes(c.getUpvotes() + q.getUpvotes());
            }
          }
        }
        for (CombinationOfTags c : list) {
          c.setAvgUpvotes(((double) c.getUpvotes()) / ((double) c.getQuestionNum()));
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getAvgUpvotes());
        }
        break;
      default:
        return null;
    }
    return map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(10)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
            LinkedHashMap::new));
  }


  /**
   * @return key: one tag, value: average views over thread
   */
  @GetMapping("/avgViews-tag")
  public Map<String, Double> tagViewsDistribution(String CombinationNum) {
    Map<String, Double> map = new LinkedHashMap<>();

    List<Tag> tags = tagMapper.selectList(null);
    List<CombinationOfTags> list = new ArrayList<>();
    List<Question> questions = questionMapper.selectQuestions(null);
    switch (CombinationNum) {
      case "1":
        for (Tag tag : tags) {
          map.put(tag.getTagName(), (double) tag.getViews());
        }
        break;
      case "2":
        tags = tags.stream().sorted(Comparator.comparing(Tag::getViews).reversed())
            .limit(10).collect(Collectors.toList());
        for (int i = 0; i < tags.size(); i++) {
          for (int j = i + 1; j < tags.size(); j++) {
            CombinationOfTags combinationOfTags = new CombinationOfTags();
            combinationOfTags.addTag(tags.get(i));
            combinationOfTags.addTag(tags.get(j));
            list.add(combinationOfTags);
          }
        }
        for (Question q : questions) {
          if (q.getTags().size() < 2) {
            continue;
          }
          for (CombinationOfTags c : list) {
            if (new HashSet<>(q.getTags()).containsAll(c.getTags())) {
              c.setQuestionNum(c.getQuestionNum() + 1);
              c.setUpvotes(c.getViews() + q.getViewCount());
            }
          }
        }
        for (CombinationOfTags c : list) {
          c.setAvgUpvotes(((double) c.getViews()) / ((double) c.getQuestionNum()));
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getAvgUpvotes());
        }
        break;
      case "3":
        tags = tags.stream().sorted(Comparator.comparing(Tag::getViews).reversed())
            .limit(10).collect(Collectors.toList());
        for (int i = 0; i < tags.size(); i++) {
          for (int j = i + 1; j < tags.size(); j++) {
            for (int k = j + 1; k < tags.size(); k++) {
              CombinationOfTags combinationOfTags = new CombinationOfTags();
              combinationOfTags.addTag(tags.get(i));
              combinationOfTags.addTag(tags.get(j));
              combinationOfTags.addTag(tags.get(k));
              list.add(combinationOfTags);
            }
          }
        }
        for (Question q : questions) {
          if (q.getTags().size() < 3) {
            continue;
          }
          for (CombinationOfTags c : list) {
            if (new HashSet<>(q.getTags()).containsAll(c.getTags())) {
              c.setQuestionNum(c.getQuestionNum() + 1);
              c.setUpvotes(c.getViews() + q.getViewCount());
            }
          }
        }
        for (CombinationOfTags c : list) {
          c.setAvgUpvotes(((double) c.getViews()) / ((double) c.getQuestionNum()));
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getAvgUpvotes());
        }
        break;
      default:
        return null;
    }
    return map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(10)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
            LinkedHashMap::new));
  }

}
