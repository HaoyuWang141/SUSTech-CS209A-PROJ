package sustech.project.javaproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {

  @RequestMapping("/hello/**")
  public String test() {
    System.out.println("hello");
    return "hello";
  }
}
