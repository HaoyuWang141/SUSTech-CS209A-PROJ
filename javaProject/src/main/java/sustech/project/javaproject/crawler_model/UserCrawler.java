package sustech.project.javaproject.crawler_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCrawler {

  private Integer account_id;
  private Integer reputation;
  private Integer user_id;
  private Double Accept_rate;
  private String display_name;

  public Integer getAccount_id() {
    return account_id;
  }

  public void setAccount_id(Integer account_id) {
    this.account_id = account_id;
  }

  public Integer getReputation() {
    return reputation;
  }

  public void setReputation(Integer reputation) {
    this.reputation = reputation;
  }

  public Double getAccept_rate() {
    return Accept_rate;
  }

  public void setAccept_rate(Double accept_rate) {
    Accept_rate = accept_rate;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  @Override
  public String toString() {
    return "UserCrawler{" +
        "account_id=" + account_id +
        ", reputation=" + reputation +
        ", user_id=" + user_id +
        ", Accept_rate=" + Accept_rate +
        ", display_name='" + display_name + '\'' +
        '}';
  }
}
