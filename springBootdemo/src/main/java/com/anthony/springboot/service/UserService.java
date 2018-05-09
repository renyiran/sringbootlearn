package com.anthony.springboot.service;

import com.anthony.springboot.model.UserModel;

import java.util.List;

/**
 * Created by BG343891 on 2018/5/9.
 */
public interface UserService {

    public List<UserModel> queryAllUsers();

    public UserModel queryUserById(Long id);
}
