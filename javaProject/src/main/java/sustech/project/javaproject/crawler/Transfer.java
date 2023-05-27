package sustech.project.javaproject.crawler;

import com.google.gson.Gson;
import sustech.project.javaproject.crawler_model.JsonQuestion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Transfer {

    static JsonQuestion[] transferQuestion() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader in = new BufferedReader(new FileReader("./src/data/questions.json"));
        JsonQuestion[] questions = gson.fromJson(in, JsonQuestion[].class);
        return questions;
    }



}
