package sustech.project.javaproject.crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Comment;
import sustech.project.javaproject.entity.Question;


public class DataStore {

  // 定义数据库连接信息
   private static final String url = "jdbc:postgresql://114.132.51.227:5432/java2_project";
   private static final String username = "admin_java2_project";
   private static final String password = "@4Em~hD9EoPkEVEL3tgWY@";
//  private static final String url = "jdbc:postgresql://localhost:5432/java2_project";
//  private static final String username = "postgres";
//  private static final String password = "111111";
  private static Connection connection = null;

  public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, username, password);
       questionTransfer(1);
       answerTransfer(1, 1);
       commentTransfer(1);
       commentTransferRelatedToAnswer(1);
       updateQuestionTable_CommentCount();
       updateTagTable();
       updateUserTable();
      apiTransfer();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void questionTransfer(int questionCount) throws Exception {
    System.out.println("questionTransfer begin");
    StringBuilder stringBuilder = new StringBuilder();
    List<String> filePath = new ArrayList<>();
    for (int i = 1; i <= questionCount; i++) {
      filePath.add("src/main/resources/data/questions" + i + ".json");
    }
    for (String path : filePath) {
      byte[] bytes = Files.readAllBytes(Paths.get(path));
      String content = new String(bytes);
      stringBuilder.append(content);
    }
    String content = stringBuilder.toString();
    List<Question> questions = null;
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    questions = objectMapper.readValue(content,
        objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));

    PreparedStatement preparedStatement = null;

    preparedStatement = connection.prepareStatement(
        "INSERT INTO t_user VALUES (?, ?, 0 ,0, 0) ON CONFLICT DO NOTHING ");
    int count = 0;
    for (Question question : questions) {
      count++;
      if (question.getOwner() == null) {
        continue;
      }
      preparedStatement.setInt(1, question.getOwner().getAccountId());
      preparedStatement.setString(2, question.getOwner().getDisplayName());
      preparedStatement.addBatch();
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;

    System.out.println("user insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO tag VALUES (?) ON CONFLICT DO NOTHING ");
    for (Question question : questions) {
      for (String tag : question.getTags()) {
        count++;
        preparedStatement.setString(1, tag);
        preparedStatement.addBatch();
        if (count % 100 == 0) {
          preparedStatement.executeBatch();
          preparedStatement.clearBatch();
          count = 0;
        }
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("tag insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO question VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING");
    for (Question question : questions) {
      preparedStatement.setInt(1, question.getId());
      if (question.getOwner() == null) {
        preparedStatement.setNull(2, java.sql.Types.INTEGER);
      } else {
        preparedStatement.setInt(2, question.getOwner().getAccountId());
      }
      preparedStatement.setBoolean(3, question.getIsAnswered());
      preparedStatement.setBoolean(4, question.getHasAcceptedAnswer());
      preparedStatement.setInt(5, question.getViewCount());
      preparedStatement.setInt(6, question.getUpvotes());
      preparedStatement.setTimestamp(7, question.getCreationDate());
      preparedStatement.setInt(8, question.getAnswerCount());
      preparedStatement.setInt(9, 0);
      count++;
      preparedStatement.addBatch();
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("question insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO question_tag VALUES (?, ?) ON CONFLICT DO NOTHING");
    for (Question question : questions) {
      for (String tag : question.getTags()) {
        preparedStatement.setInt(1, question.getId());
        preparedStatement.setString(2, tag);
        preparedStatement.addBatch();
        count++;
        if (count % 100 == 0) {
          preparedStatement.executeBatch();
          preparedStatement.clearBatch();
          count = 0;
        }
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    System.out.println("question_tag insert finished");
    System.out.println();
  }

  private static void answerTransfer(int ansCount, int acceptedAnsCount)
      throws IOException, SQLException {
    System.out.println("answerTransfer begin");
    StringBuilder stringBuilder = new StringBuilder();
    List<String> filePath = new ArrayList<>();
    for (int i = 1; i <= ansCount; i++) {
      filePath.add("src/main/resources/data/answers" + i + ".json");
    }
    for (int i = 1; i <= acceptedAnsCount; i++) {
      filePath.add("src/main/resources/data/accepted_answers" + i + ".json");
    }
    for (String path : filePath) {
      byte[] bytes = Files.readAllBytes(Paths.get(path));
      String content = new String(bytes);
      stringBuilder.append(content);
    }
    String content = stringBuilder.toString();
    List<Answer> answers = null;
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    answers = objectMapper.readValue(content,
        objectMapper.getTypeFactory().constructCollectionType(List.class, Answer.class));

    PreparedStatement preparedStatement = null;

    preparedStatement = connection.prepareStatement(
        "INSERT INTO t_user VALUES (?, ?,0,0,0) ON CONFLICT DO NOTHING ");
    int count = 0;
    for (Answer answer : answers) {
      if (answer.getOwner() == null) {
        continue;
      }
      preparedStatement.setInt(1, answer.getOwner().getAccountId());
      preparedStatement.setString(2, answer.getOwner().getDisplayName());
      count++;
      preparedStatement.addBatch();
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("user insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO answer VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING");
    for (Answer answer : answers) {
      preparedStatement.setInt(1, answer.getId());
      preparedStatement.setInt(2, answer.getQuestionId());
      if (answer.getOwner() == null) {
        preparedStatement.setNull(3, java.sql.Types.INTEGER);
      } else {
        preparedStatement.setInt(3, answer.getOwner().getAccountId());
      }
      preparedStatement.setTimestamp(4, answer.getCreationDate());
      preparedStatement.setInt(5, answer.getUpvotes());
      preparedStatement.setBoolean(6, answer.getIsAccepted());
      count++;
      preparedStatement.addBatch();
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("answer insert finished");
    System.out.println();
  }

  private static void commentTransfer(int commentCount) throws IOException, SQLException {
    System.out.println("comment transfer start");
    StringBuilder stringBuilder = new StringBuilder();
    List<String> filePath = new ArrayList<>();
    for (int i = 1; i <= commentCount; i++) {
      filePath.add("src/main/resources/data/comments" + i + ".json");
    }
    for (String path : filePath) {
      byte[] bytes = Files.readAllBytes(Paths.get(path));
      String content = new String(bytes);
      stringBuilder.append(content);
    }
    String content = stringBuilder.toString();
    List<List<Comment>> list;
    List<Comment> comments = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    list = objectMapper.readValue(content,
        objectMapper.getTypeFactory().constructCollectionType(List.class,
            objectMapper.getTypeFactory().constructCollectionType(List.class, Comment.class)));
    for (List<Comment> l : list) {
      comments.addAll(l);
    }
    // System.out.println(comments.toString());
    PreparedStatement preparedStatement = null;

    preparedStatement = connection.prepareStatement(
        "INSERT INTO t_user VALUES (?, ?,0,0,0) ON CONFLICT DO NOTHING ");
    int count = 0;
    for (Comment comment : comments) {
      if (comment.getOwner() == null) {
        continue;
      }
      preparedStatement.setInt(1, comment.getOwner().getAccountId());
      preparedStatement.setString(2, comment.getOwner().getDisplayName());
      preparedStatement.addBatch();
      count++;
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("user insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO comment VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING ");
    for (Comment comment : comments) {
      preparedStatement.setInt(1, comment.getId());
      preparedStatement.setInt(2, comment.getQuestionId());
      if (comment.getOwner() == null) {
        preparedStatement.setNull(3, java.sql.Types.INTEGER);
      } else {
        preparedStatement.setInt(3, comment.getOwner().getAccountId());
      }
      preparedStatement.setTimestamp(4, comment.getCreationDate());
      count++;
      preparedStatement.addBatch();
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("comment insert finished");
    System.out.println();
  }

  private static void commentTransferRelatedToAnswer(int commentCount)
      throws IOException, SQLException {
    System.out.println("comment transfer realted to answer start");
    StringBuilder stringBuilder = new StringBuilder();
    List<String> filePath = new ArrayList<>();
    for (int i = 1; i <= commentCount; i++) {
      filePath.add("src/main/resources/data/commentsRelatedToAnswer" + i + ".json");
    }
    for (String path : filePath) {
      byte[] bytes = Files.readAllBytes(Paths.get(path));
      String content = new String(bytes);
      stringBuilder.append(content);
    }
    String content = stringBuilder.toString();
    List<List<Comment>> list;
    List<Comment> comments = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    list = objectMapper.readValue(content,
        objectMapper.getTypeFactory().constructCollectionType(List.class,
            objectMapper.getTypeFactory().constructCollectionType(List.class, Comment.class)));
    for (List<Comment> l : list) {
      comments.addAll(l);
    }
    // System.out.println(comments.toString());
    PreparedStatement preparedStatement = null;

    preparedStatement = connection.prepareStatement(
        "INSERT INTO t_user VALUES (?, ?,0,0,0) ON CONFLICT DO NOTHING ");
    int count = 0;
    for (Comment comment : comments) {
      if (comment.getOwner() == null) {
        continue;
      }
      preparedStatement.setInt(1, comment.getOwner().getAccountId());
      preparedStatement.setString(2, comment.getOwner().getDisplayName());
      preparedStatement.addBatch();
      count++;
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("user insert finished");

    Map<Integer, Integer> answerMapToQuestion = new HashMap<>();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT id,question_id FROM answer");
    while (resultSet.next()) {
      answerMapToQuestion.put(resultSet.getInt(1), resultSet.getInt(2));
    }

    preparedStatement = connection.prepareStatement(
        "INSERT INTO comment VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING ");
    for (Comment comment : comments) {
      preparedStatement.setInt(1, comment.getId());
      preparedStatement.setInt(2, answerMapToQuestion.get(comment.getQuestionId()));
      if (comment.getOwner() == null) {
        preparedStatement.setNull(3, java.sql.Types.INTEGER);
      } else {
        preparedStatement.setInt(3, comment.getOwner().getAccountId());
      }
      preparedStatement.setTimestamp(4, comment.getCreationDate());
      count++;
      preparedStatement.addBatch();
      if (count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();
    count = 0;
    System.out.println("comment insert finished");
    System.out.println();
  }

  private static void updateQuestionTable_CommentCount() throws SQLException {
    System.out.println("update comment count start");
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("""
        select question_id, count(*)
        from question
                 join comment on question.id = comment.question_id
        group by question_id;""");
    PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE question SET comment_count = ? WHERE id = ?");
    while (resultSet.next()) {
      preparedStatement.setInt(1, resultSet.getInt("count"));
      preparedStatement.setInt(2, resultSet.getInt("question_id"));
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    preparedStatement.close();
    System.out.println();
  }

  private static void updateTagTable() throws SQLException {
    System.out.println("update tag start");
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("""
        select tag_name,
               count(*)        as question_number,
               avg(upvotes)    as avg_upvotes,
               avg(view_count) as avg_views
        from question
                 join question_tag qt on question.id = qt.question_id
        group by tag_name;
        """);
    PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE tag SET question_number = ?, avg_views = ?, avg_upvotes = ? WHERE tag_name = ?");
    while (resultSet.next()) {
      preparedStatement.setInt(1, resultSet.getInt("question_number"));
      preparedStatement.setDouble(2, resultSet.getDouble("avg_views"));
      preparedStatement.setDouble(3, resultSet.getDouble("avg_upvotes"));
      preparedStatement.setString(4, resultSet.getString("tag_name"));
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    preparedStatement.close();
    System.out.println();
  }

  private static void updateUserTable() throws SQLException {
    System.out.println("update user start");
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("""
        select owner_id, count(*) from question group by owner_id;
        """);
    PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE t_user SET question_number = ? WHERE account_id = ?");
    while (resultSet.next()) {
      preparedStatement.setInt(1, resultSet.getInt("count"));
      preparedStatement.setInt(2, resultSet.getInt("owner_id"));
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();

    resultSet = statement.executeQuery("""
        select owner_id, count(*) from answer group by owner_id;
        """);
    preparedStatement = connection.prepareStatement(
        "UPDATE t_user SET answer_number = ? WHERE account_id = ?");
    while (resultSet.next()) {
      preparedStatement.setInt(1, resultSet.getInt("count"));
      preparedStatement.setInt(2, resultSet.getInt("owner_id"));
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    preparedStatement.clearBatch();

    resultSet = statement.executeQuery("""
        select owner_id, count(*) from comment group by owner_id;
        """);
    preparedStatement = connection.prepareStatement(
        "UPDATE t_user SET comment_number = ? WHERE account_id = ?");
    while (resultSet.next()) {
      preparedStatement.setInt(1, resultSet.getInt("count"));
      preparedStatement.setInt(2, resultSet.getInt("owner_id"));
      preparedStatement.addBatch();
    }
    preparedStatement.executeBatch();
    preparedStatement.close();
    System.out.println();
  }

  private static void apiTransfer() throws IOException, SQLException {
    System.out.println("comment transfer realted to answer start");
    List<String> filePath = new ArrayList<>();
    String path = "src/main/resources/data/apis1.txt";
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    String content = new String(bytes);
    String[] splitContent = content.split(",");
    Map<String, Integer> apiMap = new HashMap<>();
    for (String s : splitContent) {
      if (s.contains("java.")) {
        String patternString = "\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
          String api = matcher.group(1);
          System.out.println(api);
          if (apiMap.containsKey(api)) {
            apiMap.put(api, apiMap.get(api) + 1);
          } else {
            apiMap.put(api, 1);
          }
        }
      }
    }
    PreparedStatement preparedStatement = connection.prepareStatement(
        "INSERT INTO api VALUES (?, ?) ON CONFLICT DO NOTHING ");
    int count = 0;
    for (Map.Entry<String, Integer> entry : apiMap.entrySet()) {
      preparedStatement.setString(1, entry.getKey());
      preparedStatement.setInt(2, entry.getValue());
      preparedStatement.addBatch();
      count++;
      if(count % 100 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        count = 0;
      }
    }
    preparedStatement.executeBatch();
    preparedStatement.close();
    System.out.println("api transfer finished");
    System.out.println();
  }
}