package sustech.project.javaproject.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class Crawler {

    public static void main(String[] args) {
//        getQuestion();
//        getAnswer();

    }

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
                    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
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
            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&sort=votes&tagged=java&page=" + page + "&pagesize=" + pageSize + "&site=stackoverflow";

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
                    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
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

    static void getAnswer() {
        try {
//            String questionID;
            String apiUrl = "https://api.stackexchange.com/2.3/questions/24885223/answers?site=stackoverflow";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String charset = conn.getContentEncoding();
                BufferedReader in;
                if (charset != null && charset.equalsIgnoreCase("gzip")) {
                    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
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

    static void getAnswer(int questionID) {
        try {
//            String questionID;
            String apiUrl = "https://api.stackexchange.com/2.3/questions/" + questionID + "/answers?site=stackoverflow";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String charset = conn.getContentEncoding();
                BufferedReader in;
                if (charset != null && charset.equalsIgnoreCase("gzip")) {
                    in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
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

}
