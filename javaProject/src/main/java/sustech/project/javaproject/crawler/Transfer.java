package sustech.project.javaproject.crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Comment;
import sustech.project.javaproject.entity.Question;


public class Transfer {

  // 定义数据库连接信息
  private static final String url = "jdbc:postgresql://114.132.51.227:5432/java2_project";
  private static final String username = "admin_java2_project";
  private static final String password = "@4Em~hD9EoPkEVEL3tgWY@";
  private static Connection connection = null;

  public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, username, password);
      questionTransfer(1);
      answerTransfer(1, 1);
      commentransfer(1);
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
        "INSERT INTO t_user VALUES (?, ?) ON CONFLICT DO NOTHING ");
    for (Question question : questions) {
      if (question.getOwner() == null) {
        continue;
      }
      // System.out.println(
      //     question.getOwner().getAccountId() + " " + question.getOwner().getDisplayName());
      preparedStatement.setInt(1, question.getOwner().getAccountId());
      preparedStatement.setString(2, question.getOwner().getDisplayName());
      preparedStatement.executeUpdate();
    }

    System.out.println("user insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO tag VALUES (?) ON CONFLICT DO NOTHING ");
    for (Question question : questions) {
      for (String tag : question.getTags()) {
        preparedStatement.setString(1, tag);
        preparedStatement.executeUpdate();
      }
    }

    System.out.println("tag insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO question VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING");
    for (Question question : questions) {
      preparedStatement.setInt(1, question.getId());
      if (question.getOwner() == null) {
        preparedStatement.setNull(2, java.sql.Types.INTEGER);
      } else {
        preparedStatement.setInt(2, question.getOwner().getAccountId());
      }
      preparedStatement.setBoolean(3, question.getIsAnswered());
      preparedStatement.setInt(4, question.getViewCount());
      preparedStatement.setInt(5, question.getUpvotes());
      preparedStatement.setTimestamp(6, question.getCreationDate());
      preparedStatement.executeUpdate();
    }

    System.out.println("question insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO question_tag VALUES (?, ?) ON CONFLICT DO NOTHING");
    for (Question question : questions) {
      for (String tag : question.getTags()) {
        preparedStatement.setInt(1, question.getId());
        preparedStatement.setString(2, tag);
        preparedStatement.executeUpdate();
      }
    }

    System.out.println("question_tag insert finished");
  }

  private static void answerTransfer(int ansCount, int acceptedAnsCount)
      throws IOException, SQLException {
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
        "INSERT INTO t_user VALUES (?, ?) ON CONFLICT DO NOTHING ");
    for (Answer answer : answers) {
      if (answer.getOwner() == null) {
        continue;
      }
      preparedStatement.setInt(1, answer.getOwner().getAccountId());
      preparedStatement.setString(2, answer.getOwner().getDisplayName());
      preparedStatement.executeUpdate();
    }

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
      preparedStatement.executeUpdate();
    }

    System.out.println("answer insert finished");
  }

  private static void commentransfer(int commentCount) throws IOException, SQLException {
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
    List<Comment> comments = null;
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    comments = objectMapper.readValue(content,
        objectMapper.getTypeFactory().constructCollectionType(List.class, Comment.class));

    PreparedStatement preparedStatement = null;

    preparedStatement = connection.prepareStatement(
        "INSERT INTO t_user VALUES (?, ?) ON CONFLICT DO NOTHING ");
    for (Comment comment : comments) {
      if (comment.getOwner() == null) {
        continue;
      }
      preparedStatement.setInt(1, comment.getOwner().getAccountId());
      preparedStatement.setString(2, comment.getOwner().getDisplayName());
      preparedStatement.executeUpdate();
    }

    System.out.println("user insert finished");

    preparedStatement = connection.prepareStatement(
        "INSERT INTO comment VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING");
    for (Comment comment : comments) {
      preparedStatement.setInt(1, comment.getId());
      preparedStatement.setInt(2, comment.getQuestionId());
      if (comment.getOwner() == null) {
        preparedStatement.setNull(3, java.sql.Types.INTEGER);
      } else {
        preparedStatement.setInt(3, comment.getOwner().getAccountId());
      }
      preparedStatement.setTimestamp(4, comment.getCreationDate());
      preparedStatement.executeUpdate();
    }

    System.out.println("comment insert finished");
  }

}
