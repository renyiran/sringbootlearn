package com.anthony.springboot.service;

import com.anthony.springboot.dao.UserDao;
import com.anthony.springboot.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BG343891 on 2018/5/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserModel> queryAllUsers() {
        return userDao.queryList();
    }

    @Override
    public UserModel queryUserById(Long id) {
        if (id == null) {
            return null;
        }
        return userDao.queryOne(id);
    }
}
