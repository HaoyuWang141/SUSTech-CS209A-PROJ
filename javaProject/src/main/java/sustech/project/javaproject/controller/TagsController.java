package sustech.project.javaproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
  private QuestionMapper questonMapper;

  // public static List<Question> QUESTIONS_WITH_TAGS;

  /**
   * @return key: tag, value: threadNum
   */
  @GetMapping("/QuestionNum-tag")
  public Map<String, Integer> tagFrequencyDistribution() {
    Map<String, Integer> map = new LinkedHashMap<>();
    QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("question_number");
    queryWrapper.last("limit 100");
    List<Tag> tags = tagMapper.selectList(queryWrapper);
    for (Tag tag : tags) {
      if (tag.getTagName().equals("java")) {
        continue;
      }
      map.put(tag.getTagName(), tag.getQuestionNumber());
    }
    System.out.println("QuestionNum-tag");
    // System.out.println(map);
    System.out.println();
    return map;
  }

  /**
   * @param CombinationNum 1, 2, 3
   * @return key: one tag, value: average upvotes over thread
   */
  @GetMapping("/avgUpvotes-tag")
  public Map<String, Integer> tagUpvotesDistribution(String CombinationNum) {
    long startTime = System.currentTimeMillis();
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Tag> tags = tagMapper.selectList(null);
    List<CombinationOfTags> list = new ArrayList<>();
    // List<Question> questions = new ArrayList<>(QUESTIONS_WITH_TAGS);
    List<Question> questions = questonMapper.selectQuestionsWithTags(null);
    switch (CombinationNum) {

      case "1":
        for (Tag tag : tags) {
          map.put(tag.getTagName(), tag.getAvgUpvotes());
        }
        break;
      case "2":
        tags = tags.stream()
            .filter(t -> t.getAvgUpvotes() < 10000)
            .filter(t -> !t.getTagName().equals("java"))
            .sorted(Comparator.comparing(Tag::getAvgUpvotes).reversed())
            .limit(50).collect(Collectors.toList());
        for (int i = 0; i < tags.size(); i++) {
          for (int j = i + 1; j < tags.size(); j++) {
            CombinationOfTags combinationOfTags = new CombinationOfTags();
            combinationOfTags.addTag(tags.get(i));
            combinationOfTags.addTag(tags.get(j));
            list.add(combinationOfTags);
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
          if (c.getQuestionNum() == 0) {
            continue;
          }
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join("+", tagName), c.getUpvotes() / c.getQuestionNum());
        }
        map = map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(13)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                LinkedHashMap::new));
        break;
      case "3":
        tags = tags.stream()
            .filter(t -> t.getAvgUpvotes() < 10000)
            .filter(t -> !t.getTagName().equals("java"))
            .sorted(Comparator.comparing(Tag::getAvgUpvotes).reversed())
            .limit(50).collect(Collectors.toList());
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
          if (c.getQuestionNum() == 0) {
            continue;
          }
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getUpvotes() / c.getQuestionNum());
        }
        break;
      default:
        return null;
    }
    map = map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(13)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
            LinkedHashMap::new));
    System.out.println(CombinationNum + "tags: avgUpvotes finished, time: "
        + (System.currentTimeMillis() - startTime) / 1000 + "s");
    return map;
  }


  /**
   * @return key: one tag, value: average views over thread
   */
  @GetMapping("/avgViews-tag")
  public Map<String, Integer> tagViewsDistribution(String CombinationNum) {
    long startTime = System.currentTimeMillis();
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Tag> tags = tagMapper.selectList(null);
    List<CombinationOfTags> list = new ArrayList<>();
    // List<Question> questions = new ArrayList<>(QUESTIONS_WITH_TAGS);
    List<Question> questions = questonMapper.selectQuestionsWithTags(null);
    switch (CombinationNum) {
      case "1":
        for (Tag tag : tags) {
          map.put(tag.getTagName(), tag.getAvgViews());
        }
        break;
      case "2":
        tags = tags.stream()
            .filter(t -> !t.getTagName().equals("java"))
            .sorted(Comparator.comparing(Tag::getAvgViews).reversed())
            .limit(50).collect(Collectors.toList());
        for (int i = 0; i < tags.size(); i++) {
          for (int j = i + 1; j < tags.size(); j++) {
            CombinationOfTags combinationOfTags = new CombinationOfTags();
            combinationOfTags.addTag(tags.get(i));
            combinationOfTags.addTag(tags.get(j));
            list.add(combinationOfTags);
          }
        }
        for (Question q : questions) {
          if (q.getTags().size() < 3) {
            continue;
          }
          for (CombinationOfTags c : list) {
            if (new HashSet<>(q.getTags()).containsAll(c.getTags())) {
              c.setQuestionNum(c.getQuestionNum() + 1);
              c.setViews(c.getViews() + q.getViewCount());
            }
          }
        }
        for (CombinationOfTags c : list) {
          if (c.getQuestionNum() == 0) {
            continue;
          }
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getViews() / c.getQuestionNum());
        }
        break;
      case "3":
        tags = tags.stream()
            .filter(t -> !t.getTagName().equals("java"))
            .sorted(Comparator.comparing(Tag::getAvgViews).reversed())
            .limit(50).collect(Collectors.toList());
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
              c.setViews(c.getViews() + q.getViewCount());
            }
          }
        }
        for (CombinationOfTags c : list) {
          if (c.getQuestionNum() == 0) {
            continue;
          }
          ArrayList<String> tagName = new ArrayList<>();
          for (Tag tag : c.getTags()) {
            tagName.add(tag.getTagName());
          }
          map.put(String.join(" + ", tagName), c.getViews() / c.getQuestionNum());
        }
        break;
      default:
        return null;
    }
    map = map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(10)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
            LinkedHashMap::new));
    System.out.println(CombinationNum + " tags: avgViews-tag finished, time: "
        + (System.currentTimeMillis() - startTime) / 1000 + "s");
    return map;
  }

}
