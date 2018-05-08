package com.anthony.springboot.controller;

import com.anthony.springboot.configbean.GirlConfig;
import com.anthony.springboot.configbean.UserConfig;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BG343891 on 2018/5/7.
 */

@RestController
@RequestMapping("/demo")
public class DemoAction {

    @Value("${com.demo.name}")
    private String name;
    @Value("${com.demo.account}")
    private String account;
    @Value("${com.demo.who}")
    private String who;

    @Autowired
    private UserConfig userConfig;
    @Autowired
    private GirlConfig girlConfig;

    @Value("${com.demo.random.value}")
    private String randomValue;
    @Value("${com.demo.random.int}")
    private Integer randomInt;
    @Value("${com.demo.random.long}")
    private Long randomLong;
    @Value("${com.demo.random.uuid}")
    private String randomUUID;
    @Value("${com.demo.random.int.range}")
    private Integer randomIntRange;

    @RequestMapping("/hello")
    public String index() {
        return "Hello Anthony!";
    }

    @RequestMapping("/man")
    public String helloman() {
        return account + " is " + name;
    }

    @RequestMapping("/user")
    public String user() {
        return userConfig.getName() + "---" + userConfig.getPsw();
    }

    @RequestMapping("/who")
    public String who() {
        return who;
    }

    @RequestMapping("/love")
    public String love() {
        return girlConfig.getLove() + " " + girlConfig.getName();
    }

    @RequestMapping("/random")
    public String random() {
        List<Object> randomList = Lists.newArrayList();
        randomList.add(randomValue);
        randomList.add(randomInt);
        randomList.add(randomLong);
        randomList.add(randomUUID);
        randomList.add(randomIntRange);

        StringBuffer sb = new StringBuffer();
        randomList.stream().forEach(o -> {
            sb.append(String.valueOf(o));
            sb.append(" -- ");
        });
        return sb.toString();
    }
}
