package com.anthony.springboot.service;

import com.anthony.springboot.model.UserModel;
import com.anthony.springboot.model.UserVO;

import java.util.List;

/**
 * Created by BG343891 on 2018/5/9.
 */
public interface UserService {

//    public List<UserModel> queryAllUsers();
//
//    public UserModel queryUserById(Long id);

    //使用通用mapper生成方法
    public List<UserVO> queryAll();

    public UserVO queryOneUser(Integer id);
}
