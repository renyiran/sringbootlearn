package com.anthony.springboot.service;

import com.anthony.springboot.dao.UserDao;
import com.anthony.springboot.dao.UserVOMapper;
import com.anthony.springboot.model.UserModel;
import com.anthony.springboot.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BG343891 on 2018/5/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    //    @Autowired
//    private UserDao userDao;
    @Autowired
    private UserVOMapper userVOMapper;

//    @Override
//    public List<UserModel> queryAllUsers() {
//        return userDao.queryList();
//    }

//    @Override
//    public UserModel queryUserById(Long id) {
//        if (id == null) {
//            return null;
//        }
//        return userDao.queryOne(id);
//    }

    @Override
    public List<UserVO> queryAll() {
        return userVOMapper.selectAll();
    }

    @Override
    public UserVO queryOneUser(Integer id) {
        if (id == null) {
            return null;
        }
        return userVOMapper.selectByPrimaryKey(id);
    }
}
