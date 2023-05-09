package sustech.project.javaproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.User;
import sustech.project.javaproject.mapper.UserMapper;

@RestController
public class UserController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/user")
  public List<User> query() {
//    List<User> users = userMapper.find();
    List<User> users = userMapper.selectList(null);
    System.out.println(users);
    return users;
  }

  @PostMapping("/user")
  public String save(User user) {
    int i = userMapper.insert(user);
    System.out.println(user);
    if (i > 0) {
      return "success";
    } else {
      return "failure";
    }
  }
}
