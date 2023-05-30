package sustech.project.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.mapper.CommentMapper;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
  @Autowired
  private CommentMapper commentMapper;

  @GetMapping("/getNum")
  public long getNum(String status){
    long result = 0;
    switch (status){
      case "all":
        result = commentMapper.selectCount(null);
        break;
    }
    System.out.println(status + " comment number: " + result);
    return result;
  }
}
