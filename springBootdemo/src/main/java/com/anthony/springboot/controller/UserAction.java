package com.anthony.springboot.controller;

import com.anthony.springboot.model.UserModel;
import com.anthony.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by BG343891 on 2018/5/9.
 */
@RestController
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public List<UserModel> queryAllUsers() {
        return userService.queryAllUsers();
    }

    @RequestMapping("/getuser")
    public UserModel queryUserById() {
        Random ran = new Random();
        return userService.queryUserById(Long.valueOf(ran.nextInt(3) + 1));
    }
}
