package sustech.project.javaproject.lisener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sustech.project.javaproject.controller.TagsController;
import sustech.project.javaproject.controller.UsersController;
import sustech.project.javaproject.mapper.QuestionMapper;
import sustech.project.javaproject.mapper.UserMapper;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  QuestionMapper questionMapper;

  @Autowired
  UserMapper userMapper;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    System.out.println("容器初始化完毕");

    long startTime = System.currentTimeMillis();
    System.out.println("开始初始化带标签的问题列表");
    TagsController.QUESTIONS_WITH_TAGS = questionMapper.selectQuestionsWithTags(null);
    System.out.println(
        "问题列表初始化完毕, 耗时：" + (System.currentTimeMillis() - startTime) / 1000 + " s");

    startTime = System.currentTimeMillis();
    System.out.println("开始初始化用户列表");
    UsersController.USERS = userMapper.selectList(null);
    System.out.println(
        "用户列表初始化完毕, 耗时：" + (System.currentTimeMillis() - startTime) / 1000 + " s");
  }
}
