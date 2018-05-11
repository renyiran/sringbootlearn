package com.anthony.springboot.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by BG343891 on 2018/5/10.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
