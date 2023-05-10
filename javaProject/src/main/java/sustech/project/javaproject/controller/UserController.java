package sustech.project.javaproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.User;
import sustech.project.javaproject.mapper.UserMapper;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping
  public List<User> query() {
//    List<User> users = userMapper.find();
    List<User> users = userMapper.selectList(null);
    System.out.println(users);
    return users;
  }

  @PostMapping
  public String save(User user) {
    int i = 0;
    try {
      i = userMapper.insert(user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(user);
    if (i > 0) {
      return "success";
    } else {
      return "failure";
    }
  }

  @GetMapping("/findAll")
  public List<User> getAllUsers() {
    return userMapper.SelectAllUserAndOrders();
  }

  @GetMapping("/find")
  public List<User> getUser(String username) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    return userMapper.selectList(queryWrapper);
  }

  // select * from xx limit 0, 10
  @GetMapping("/findByPage")
  public IPage<User> findByPage(int page_num) {
    Page<User> page = new Page<>(page_num, 2);
    return userMapper.selectPage(page, null);
  }
}
