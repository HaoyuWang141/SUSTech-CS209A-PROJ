package sustech.project.javaproject.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import sustech.project.javaproject.entity.Tag;

public class CombinationOfTags {

  private Set<Tag> tags;
  private int questionNum;
  private int views;
  private double avgViews;
  private int upvotes;
  private double avgUpvotes;

  public CombinationOfTags() {
    tags = new HashSet<>();
  }

  public void addTag(Tag tag) {
    tags.add(tag);
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public int getQuestionNum() {
    return questionNum;
  }

  public void setQuestionNum(int questionNum) {
    this.questionNum = questionNum;
  }

  public int getViews() {
    return views;
  }

  public void setViews(int views) {
    this.views = views;
  }

  public double getAvgViews() {
    return avgViews;
  }

  public void setAvgViews(double avgViews) {
    this.avgViews = avgViews;
  }

  public int getUpvotes() {
    return upvotes;
  }

  public void setUpvotes(int upvotes) {
    this.upvotes = upvotes;
  }

  public double getAvgUpvotes() {
    return avgUpvotes;
  }

  public void setAvgUpvotes(double avgUpvotes) {
    this.avgUpvotes = avgUpvotes;
  }
}
