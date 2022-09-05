package com.sample.restServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class RedisController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/RedisStart")
    public void hello(HttpSession httpSession) {
        httpSession.setAttribute("testSession", "testRedisSession");
    }

    @GetMapping("/CreateData")
    public void createData(){
        String key = "number extend";
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, "1"); // redis set 명령어
        String result_1 = stringStringValueOperations.get(key); // redis get 명령어
        System.out.println("key : "+ key + " / value :  " + result_1);

        stringStringValueOperations.increment(key); // redis incr 명령어
        String result_2 = stringStringValueOperations.get(key);
        System.out.println("key : "+ key + " / value :  " + result_2);

        String keyName = "윤병욱";
        stringStringValueOperations.set(keyName, "대리"); // redis set 명령어
        String jobGrade = stringStringValueOperations.get(keyName); // redis get 명령어
        System.out.println("keyName : "+ keyName + " / value :  " + jobGrade);

        keyName = "임진호";
        stringStringValueOperations.set(keyName, "대리"); // redis set 명령어
        jobGrade = stringStringValueOperations.get(keyName); // redis get 명령어
        System.out.println("keyName : "+ keyName + " / value :  " + jobGrade);

        keyName = "hyuna";
        stringStringValueOperations.set(keyName, "release_master"); // redis set 명령어
        jobGrade = stringStringValueOperations.get(keyName); // redis get 명령어
        System.out.println("keyName : "+ keyName + " / value :  " + jobGrade);

    }

    //Redis로 데이터 Post로 던져서 값 세팅
    @RequestMapping(value = "/RedisPost", method = RequestMethod.POST)
    public String RedisPost(@RequestBody Map<String,String> param){
        String getParam = param.get("name");
        System.out.println("넘어온 파라미터 : " + param);
        System.out.println("넘어온 파라미터 : " + getParam);

        String keyName = "name";
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(keyName, getParam);

        return "RedisPost";
    }
}
