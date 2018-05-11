package com.anthony.springboot.controller;

import com.anthony.springboot.model.UserModel;
import com.anthony.springboot.model.UserVO;
import com.anthony.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by BG343891 on 2018/5/9.
 */
@RestController
@RequestMapping("/user")
public class UserAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public List<UserVO> queryAllUsers() {
//        return userService.queryAllUsers();
        return userService.queryAll();
    }

    @RequestMapping("/getuser")
    public UserVO queryUserById(@RequestParam(required = false) Integer id) {
        logger.info("---id:{}", id);
        if (id == null) {
            Random ran = new Random();
            id = ran.nextInt(3) + 1;
        }
        return userService.queryOneUser(id);
    }
}
