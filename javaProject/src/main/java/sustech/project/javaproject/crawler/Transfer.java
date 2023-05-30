package sustech.project.javaproject.crawler;

import com.google.gson.Gson;
import sustech.project.javaproject.crawler_model.JsonAnswer;
import sustech.project.javaproject.crawler_model.JsonQuestion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Transfer {

    static JsonQuestion[] transferQuestion() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader in = new BufferedReader(new FileReader("./src/main/resources/data/questions1.json"));
        JsonQuestion[] questions = gson.fromJson(in, JsonQuestion[].class);
        return questions;
    }

    static JsonAnswer[] transferAnswer() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader in = new BufferedReader(new FileReader("./src/main/resources/data/answers1.json"));
        JsonAnswer[] answers = gson.fromJson(in, JsonAnswer[].class);
        return answers;
    }

}
