package com.sample.restServer.controller;

import com.sample.restServer.ignite.IgniteLifeCycle;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SampleController {
    ClientCache<Object, Object> cache = null;
    IgniteLifeCycle igniteLifeCycle = null;

    @GetMapping("/igniteCacheCreate")
    public void igniteCacheCreate(){
        igniteLifeCycle = new IgniteLifeCycle();
        cache = igniteLifeCycle.IgniteConnect();
    }

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

    @RequestMapping(value = "/ignite", method = RequestMethod.POST)
    public String igniteTest(@RequestBody Map<String,String> param){
        String getParam = param.get("method");
        System.out.println("넘어온 파라미터 : " + param);
        System.out.println("넘어온 파라미터 : " + getParam);
        switch(getParam){
            case "getSelectAll":
                igniteLifeCycle.getSelectAll(cache, "emp");
                break;
            case "insertEmp":
                igniteLifeCycle.insertEmp(cache, "emp", "YSH","유승훈",20);
                break;
            case "updateEmp":
                igniteLifeCycle.updateEmp(cache, "emp","YSH","유승훈",30);
                break;
            case "createTable":
                igniteLifeCycle.createTable(cache,"emp");
                break;
        }
        return "igniteTest";
    }
}
