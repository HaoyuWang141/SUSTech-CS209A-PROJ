package sustech.project.javaproject;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Comment;
import sustech.project.javaproject.entity.Question;
import sustech.project.javaproject.entity.User;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("sustech.project.javaproject.mapper")
public class JavaProjectApplication {
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(JavaProjectApplication.class, args);
    }

    @PostConstruct
    public void testDatabaseConnection() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "SELECT 1";
            jdbcTemplate.queryForObject(sql, Integer.class);
            System.out.println("Database connection is successful.");
        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}
