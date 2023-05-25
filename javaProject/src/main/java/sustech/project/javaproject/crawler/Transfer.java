package sustech.project.javaproject.crawler;

import com.google.gson.Gson;

public class Transfer {
    public static void main(String[] args) {
        transferQuestion();
    }

    static void transferQuestion() {
        Gson gson = new Gson();
        String json = """
                {
                  "tags": [
                    "java",
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
        Question obj = gson.fromJson(json, Question.class);
        System.out.println(obj);
    }
}
