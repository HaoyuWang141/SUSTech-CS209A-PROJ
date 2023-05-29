package sustech.project.javaproject;

import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Comment;
import sustech.project.javaproject.entity.Question;
import sustech.project.javaproject.entity.User;

@SpringBootApplication
@MapperScan("sustech.project.javaproject.mapper")
public class JavaProjectApplication {
  public static void main(String[] args) {
    SpringApplication.run(JavaProjectApplication.class, args);
  }
}
