package sustech.project.javaproject.crawler_model;

public class JsonUser {
    private Integer user_id;
    private Integer account_id;
    private String display_name;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", display_name='" + display_name + '\'' +
                '}';
    }
}
