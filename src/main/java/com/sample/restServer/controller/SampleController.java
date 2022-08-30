package com.sample.restServer.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SampleController {

    @RequestMapping(value = "/restTest", method = RequestMethod.POST)
    public String restTest(@RequestBody Map<String,String> param){
        String getParam = param.get("emp_name");
        System.out.println("넘어온 파라미터 : " + param);
        System.out.println("넘어온 파라미터 : " + getParam);
        switch(getParam){
            case "윤병욱":
                return "대리";
            case "임진호":
                return "과장";
            case "이예지":
                return "주임";
        }
        return "SAMPLE CALL FAILED";
    }
}
