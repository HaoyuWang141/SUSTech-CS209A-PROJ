package sustech.project.javaproject.crawler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.util.List;
import sustech.project.javaproject.entity.Answer;
import sustech.project.javaproject.entity.Comment;
import sustech.project.javaproject.entity.Question;

public class DataStoreTest {

  public static void main(String[] args) {
    try {
      transferQuestion();
      // transferAnswer();
      // transferComment();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  static void transferQuestion() throws JsonProcessingException {
    ObjectMapper objectMapper1 = new ObjectMapper();
    Question obj = new Question();
    String jsonString1 = objectMapper1.writeValueAsString(obj);
    // System.out.println(jsonString1);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    String jsonString = """
        {
          "tags": [
            "android",
            "android-recyclerview"
          ],
          "owner": {
            "account_id": 2821295,
            "reputation": 17410,
            "user_id": 2424999,
            "user_type": "registered",
            "accept_rate": 85,
            "profile_image": "https://i.stack.imgur.com/oNCDG.jpg?s=256&g=1",
            "display_name": "T_V",
            "link": "https://stackoverflow.com/users/2424999/t-v"
          },
          "is_answered": true,
          "view_count": 439055,
          "accepted_answer_id": 24933117,
          "answer_count": 31,
          "score": 1043,
          "last_activity_date": 1660302871,
          "creation_date": 1406025819,
          "last_edit_date": 1576075674,
          "question_id": 24885223,
          "content_license": "CC BY-SA 4.0",
          "link": "https://stackoverflow.com/questions/24885223/why-doesnt-recyclerview-have-onitemclicklistener",
          "title": "Why doesn&#39;t RecyclerView have onItemClickListener()?"
        }
        """;
    Question q = objectMapper.readValue(jsonString, Question.class);
    System.out.println(q.toString());
  }

  static void transferAnswer() throws JsonProcessingException {
    ObjectMapper objectMapper1 = new ObjectMapper();
    Answer obj = new Answer();
    String jsonString1 = objectMapper1.writeValueAsString(obj);
    System.out.println(jsonString1);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    String jsonString = """
         [
              {
                "owner": {
                  "account_id": 16322448,
                  "reputation": 584,
                  "user_id": 11787903,
                  "user_type": "registered",
                  "profile_image": "https://www.gravatar.com/avatar/d6c141bb9ba804af3b9ad651b20acf87?s=256&d=identicon&r=PG&f=y&so-version=2",
                  "display_name": "Alopwer",
                  "link": "https://stackoverflow.com/users/11787903/alopwer"
                },
                "is_accepted": false,
                "score": 0,
                "last_activity_date": 1685113162,
                "creation_date": 1685113162,
                "answer_id": 76341994,
                "question_id": 76341555,
                "content_license": "CC BY-SA 4.0"
              },
              {
                "owner": {
                  "account_id": 24963694,
                  "reputation": 1,
                  "user_id": 18827435,
                  "user_type": "registered",
                  "profile_image": "https://www.gravatar.com/avatar/190aeee2771c558088f63f3bebd250f7?s=256&d=identicon&r=PG",
                  "display_name": "r_tech44",
                  "link": "https://stackoverflow.com/users/18827435/r-tech44"
                },
                "is_accepted": false,
                "score": 0,
                "last_activity_date": 1685113149,
                "creation_date": 1685113149,
                "answer_id": 76341993,
                "question_id": 72088414,
                "content_license": "CC BY-SA 4.0"
              },
         {
          "owner": {
            "account_id": 16322448,
            "reputation": 584,
            "user_id": 11787903,
            "user_type": "registered",
            "profile_image": "https://www.gravatar.com/avatar/d6c141bb9ba804af3b9ad651b20acf87?s=256&d=identicon&r=PG&f=y&so-version=2",
            "display_name": "Alopwer",
            "link": "https://stackoverflow.com/users/11787903/alopwer"
          },
          "is_accepted": false,
          "score": 0,
          "last_activity_date": 1685113162,
          "creation_date": 1685113162,
          "answer_id": 76341994,
          "question_id": 76341555,
          "content_license": "CC BY-SA 4.0"
        }]
         """;
    List<Answer> a =objectMapper.readValue(jsonString, new TypeReference<List<Answer>>() {});
    System.out.println(a);
  }

  static void transferComment() throws JsonProcessingException {
    ObjectMapper objectMapper1 = new ObjectMapper();
    Comment obj = new Comment();
    String jsonString1 = objectMapper1.writeValueAsString(obj);
    System.out.println(jsonString1);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    String jsonString = """
        {
          "owner": {
            "account_id": 4273426,
            "reputation": 953,
            "user_id": 3494714,
            "user_type": "registered",
            "profile_image": "https://i.stack.imgur.com/zfS58.jpg?s=256&g=1",
            "display_name": "BigFinger",
            "link": "https://stackoverflow.com/users/3494714/bigfinger"
          },
          "edited": false,
          "score": 0,
          "creation_date": 1685113255,
          "post_id": 76341853,
          "comment_id": 134620767,
          "content_license": "CC BY-SA 4.0"
        }
        """;
    Comment c = objectMapper.readValue(jsonString, Comment.class);
    System.out.println(c.toString());
  }
}
