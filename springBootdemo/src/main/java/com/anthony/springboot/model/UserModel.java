package com.anthony.springboot.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by BG343891 on 2018/5/9.
 */
@Data
public class UserModel {
    private Integer id;
    private String account;
    private String name;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String description;
}

