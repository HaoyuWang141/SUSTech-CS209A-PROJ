package sustech.project.javaproject.crawler;

import sustech.project.javaproject.crawler_model.JsonAnswer;
import sustech.project.javaproject.crawler_model.JsonQuestion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class Crawler {

  static void getQuestion() {
    try {
      int page = 2; // 页码
      int pageSize = 100; // 每页的结果数
//            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=votes&tagged=java&page=" +
//                    page + "&pagesize=" + pageSize + "&site=stackoverflow";
      String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=votes&tagged=java&page=2&pagesize=100&site=stackoverflow";

// 0            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=activity&site=stackoverflow";
//            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=votes&tagged=java&site=stackoverflow";
      URL url = new URL(apiUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      int responseCode = conn.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        String charset = conn.getContentEncoding();
        BufferedReader in;
        if (charset != null && charset.equalsIgnoreCase("gzip")) {
          in = new BufferedReader(
              new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
        } else {
          in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        }

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        System.out.println(response);
      } else {
        System.out.println("HTTP GET request failed with response code: " + responseCode);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void getQuestion(int page) {
    try {
//            int page = 2; // 页码
      int pageSize = 100; // 每页的结果数
      String apiUrl =
          "https://api.stackexchange.com/2.3/questions?order=desc&sort=votes&tagged=java&page="
              + page + "&pagesize=" + pageSize + "&site=stackoverflow";

// 0            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=activity&site=stackoverflow";
//            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=votes&tagged=java&site=stackoverflow";
      URL url = new URL(apiUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      int responseCode = conn.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        String charset = conn.getContentEncoding();
        BufferedReader in;
        if (charset != null && charset.equalsIgnoreCase("gzip")) {
          in = new BufferedReader(
              new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
        } else {
          in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        }

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        System.out.println(response);
      } else {
        System.out.println("HTTP GET request failed with response code: " + responseCode);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static StringBuilder getQuestionSortByActivity() {
    String apiUrl = "https://api.stackexchange.com/2.3/questions?" +
        "order=desc&sort=activity&tagged=java&page=2&pagesize=100&site=stackoverflow";
    return executeQuery(apiUrl);
  }


  static StringBuilder executeQuery(String apiUrl) {
    try {

//            System.out.println(apiUrl);  //
      URL url = new URL(apiUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      int responseCode = conn.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        String charset = conn.getContentEncoding();
        BufferedReader in;
        if (charset != null && charset.equalsIgnoreCase("gzip")) {
          in = new BufferedReader(
              new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
        } else {
          in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        }

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        System.out.println(response);
        return response;

      } else {
        System.out.println("HTTP GET request failed with response code: " + responseCode);
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }


  static StringBuilder getAnswer(String questionIDs) {
    String apiUrl = "https://api.stackexchange.com/2.3/questions/" + questionIDs +
        "/answers?pagesize=100&site=stackoverflow";
    return executeQuery(apiUrl);
  }

  static StringBuilder getAnswer(List<JsonQuestion> questions) {
    List<String> questionIDs = new ArrayList<>();
    for (JsonQuestion question : questions) {
      questionIDs.add(String.valueOf(question.getQuestion_id()));
    }
    String questionIDsString = String.join(";", questionIDs);
    return getAnswer(questionIDsString);
  }

  static StringBuilder getAcceptedAnswer(List<JsonQuestion> questions) {
    List<String> answerIDs = new ArrayList<>();
    for (JsonQuestion question : questions) {
      if (question.getAccepted_answer_id() != null) {
        answerIDs.add(String.valueOf(question.getAccepted_answer_id()));
      }
    }
    String answerIDsString = String.join(";", answerIDs);
    String apiUrl = "https://api.stackexchange.com/2.3/answers/" + answerIDsString +
        "?pagesize=100&site=stackoverflow";
    return executeQuery(apiUrl);
  }


  static StringBuilder getQuestionComment(String questionIDs) {
    String apiUrl = "https://api.stackexchange.com/2.3/questions/" + questionIDs +
        "/comments?pagesize=100&site=stackoverflow";
    return executeQuery(apiUrl);
  }

  static StringBuilder getQuestionComment(List<JsonQuestion> questions) {
    List<String> questionIDs = new ArrayList<>();
    for (JsonQuestion question : questions) {
      questionIDs.add(String.valueOf(question.getQuestion_id()));
    }
    String questionIDsString = String.join(";", questionIDs);
    return getQuestionComment(questionIDsString);
  }

  static StringBuilder getAnswerComment(String answerIDs) {
    String apiUrl = "https://api.stackexchange.com/2.3/answers/" + answerIDs +
        "/comments?pagesize=100&site=stackoverflow";
    return executeQuery(apiUrl);
  }

  static StringBuilder getAnswerComment(List<JsonAnswer> answers) {
    List<String> answerIDs = new ArrayList<>();
    for (JsonAnswer answer : answers) {
      answerIDs.add(String.valueOf(answer.getAnswer_id()));
    }
    String answerIDsString = String.join(";", answerIDs);
    return getAnswerComment(answerIDsString);
  }


  static StringBuilder testQuery() {
    String apiUrl = "";
    return executeQuery(apiUrl);
  }

  static StringBuilder testQuery(String apiUrl) {
    return executeQuery(apiUrl);
  }

}
