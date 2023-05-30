package sustech.project.javaproject.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.project.javaproject.entity.Api;
import sustech.project.javaproject.mapper.ApiMapper;

@RestController
@CrossOrigin
public class ApisController {

  @Autowired
  ApiMapper apiMapper;
  @GetMapping("/api")
  public Map<String,Integer> apiNumDistribution(){
    Map<String, Integer> map = new LinkedHashMap<>();
    List<Api> list = apiMapper.selectList(null);
    for(Api api:list){
      map.put(api.getName(),api.getCount());
    }
    return map;
  }
}
