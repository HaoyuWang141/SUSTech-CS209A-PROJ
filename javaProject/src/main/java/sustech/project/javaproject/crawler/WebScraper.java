package sustech.project.javaproject.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    public static void main(String[] args) {
        String url = "https://stackoverflow.com/questions/11227809"; // 替换为你感兴趣的问题的URL
        getCodes(url);
    }

    static void getContentDemo(String url) {
        url = "https://stackoverflow.com/questions/11227809"; // 替换为你感兴趣的问题的URL

        try {
            // 发送HTTP GET请求并获取页面内容
            Document doc = Jsoup.connect(url).get();

            // 提取问题标题
            String title = doc.select("div#question-header h1 a").text();
            System.out.println("问题标题: " + title);

            // 提取问题正文
            Element questionBody = doc.selectFirst("div#question div.js-post-body");
            String questionContent = questionBody.text();
            System.out.println("问题正文: " + questionContent);

            // 提取代码片段
            Elements codeBlocks = doc.select("pre code");
            System.out.println("代码片段:");
            for (Element codeBlock : codeBlocks) {
                System.out.println(codeBlock.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> getCodes(String url) {
        List<String> codes = new ArrayList<>();
        try {

            // 发送HTTP GET请求并获取页面内容
            Document doc = Jsoup.connect(url).get();

            // 提取代码片段
            Elements codeBlocks = doc.select("pre code");
            System.out.println("代码片段:");
            for (Element codeBlock : codeBlocks) {
                System.out.println("#############################");
                System.out.println(codeBlock.text());
                System.out.println("/////////////////////////////");
                codes.add(codeBlock.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return codes;
    }

}

