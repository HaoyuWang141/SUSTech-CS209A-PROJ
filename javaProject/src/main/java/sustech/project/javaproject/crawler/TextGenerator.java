package sustech.project.javaproject.crawler;

import sustech.project.javaproject.crawler_model.JsonQuestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextGenerator {
    public static void main(String[] args) throws IOException {
        writeComment();
    }


    static void writeQuestion() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/questions.txt"));
        StringBuilder sb = Crawler.getQuestionSortByActivity();
        if (sb != null) {
            out.write(extractItem(sb));
        }
        else
            System.out.println("response is null!");
        out.close();
    }


    static void writeAnswer() throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/raw_answers_q500_q599.txt"));
        JsonQuestion[] questions = Transfer.transferQuestion();

//        for (int t = 0; t < 100; t++) {
//            List<Question> questionBatch = new ArrayList<>();
//            questionBatch.add(questions[100 + 3 * t]);
//            questionBatch.add(questions[100 + 3 * t + 1]);
//            questionBatch.add(questions[100 + 3 * t + 2]);
//            StringBuilder sb = Crawler.getAnswer(questionBatch);
//            if (sb != null) {
//                out.write(extractItem(sb) + ",\n");
//            }
//            else {
//                System.out.println("response is null!");
//                break;
//            }
//        }

        for (int t = 0; t < 34; t++) {
            List<JsonQuestion> questionBatch = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                if (500 + 3 * t + i < 600)
                    questionBatch.add(questions[500 + 3 * t + i]);
            }
            StringBuilder sb = Crawler.getAnswer(questionBatch);
            if (sb != null) {
                out.write(extractItem(sb) + ",\n");
            }
            else {
                System.out.println("response is null!");
                break;
            }
        }

        out.close();
    }

    static void writeAcceptedAnswer() throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/accepted_answers.txt"));
        JsonQuestion[] questions = Transfer.transferQuestion();

        for (int t = 0; t < 6; t++) {
            List<JsonQuestion> questionBatch = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                questionBatch.add(questions[100 * t + i]);
            }
            StringBuilder sb = Crawler.getAcceptedAnswer(questionBatch);
            if (sb != null) {
                out.write(extractItem(sb) + ",\n");
            }
            else
                System.out.println("response is null!");
        }

        out.close();
    }


    static void writeComment() throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/raw_comments_c500_c549.txt"));
        JsonQuestion[] questions = Transfer.transferQuestion();

        for (int t = 500; t < 550; t++) {
            String questionIDs = String.valueOf(questions[t].getQuestion_id());
            StringBuilder sb = Crawler.getComment(questionIDs);
            if (sb != null) {
                out.write("[");
                out.write(extractItem(sb));
                out.write("],\n");
            }
            else {
                System.out.println("response is null!");
                break;
            }
        }

//        for (int t = 0; t < 200; t++) {
//            List<Question> questionBatch = new ArrayList<>();
//            questionBatch.add(questions[100 + 2 * t]);
//            questionBatch.add(questions[100 + 2 * t + 1]);
//            StringBuilder sb = Crawler.getComment(questionBatch);
//        }

        out.close();
    }


    static String extractItem(StringBuilder sb) {
        String s = sb.toString();
        int beginIdx = 0;
        int endIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i-9 >= 0 &&
                    s.charAt(i-1) == '[' && s.charAt(i-2) == ':' && s.charAt(i-3) == '"' && s.charAt(i-4) == 's' &&
                    s.charAt(i-5) == 'm' && s.charAt(i-6) == 'e' && s.charAt(i-7) == 't' && s.charAt(i-8) == 'i' &&
                    s.charAt(i-9) == '"')
                beginIdx = i;  // "items":[
            else if (i+12 < s.length() &&
                    s.charAt(i) == ']' && s.charAt(i+1) == ',' && s.charAt(i+2) == '"' && s.charAt(i+3) == 'h' &&
                    s.charAt(i+4) == 'a' && s.charAt(i+5) == 's' && s.charAt(i+6) == '_' && s.charAt(i+7) == 'm' &&
                    s.charAt(i+8) == 'o' && s.charAt(i+9) == 'r' && s.charAt(i+10) == 'e' && s.charAt(i+11) == '"')
                endIdx = i;  // ],"has_more":
        }
        if (beginIdx < endIdx)
            return s.substring(beginIdx, endIdx);
        else
            return "";
    }

}
