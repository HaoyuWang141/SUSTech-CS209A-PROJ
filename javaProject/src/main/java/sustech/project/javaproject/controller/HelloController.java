package sustech.project.javaproject.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.User1;

@RestController
public class HelloController {

  // http://localhost:8080/hello?name=a
  @RequestMapping(value = "/getTest2", method = RequestMethod.GET)
  // @GetMapping(value="/hello")
  public String getTest1(String name) {
    return "hello " + name;
  }

  @RequestMapping(value = "/getTest3", method = RequestMethod.GET)
  // @GetMapping(value="/hello")
  public String getTest2(@RequestParam(value = "name", required = false) String nnn) {
    return "hello " + nnn;
  }

  @RequestMapping(value = "/postTest1", method = RequestMethod.POST)
  public String postTest1(@RequestParam(value = "name", required = false) String nnn) {
    return "postTest1";
  }

  @RequestMapping(value = "/postTest2", method = RequestMethod.POST)
  public String postTest2(@RequestParam(value = "name", required = false) String nnn) {
    return "postTest2: " + nnn;
  }

  @RequestMapping(value = "/postTest3", method = RequestMethod.POST)
  public String postTest3(User1 user1) {
    System.out.println(user1);
    return "postTest3: " + user1;
  }

  @RequestMapping(value = "/postTest4", method = RequestMethod.POST)
  public String postTest4(@RequestBody User1 user1) { // json
    System.out.println(user1);
    return "postTest4: " + user1;
  }

  @RequestMapping("/hello/**")
  public String test() {
    return "hello";
  }
}
