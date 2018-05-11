package com.anthony.springboot.controller;

import com.anthony.springboot.util.JacksonUtil;
import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by BG343891 on 2018/5/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserActionTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final String URL_QUERYALL = "/user/all";
    private static final String URL_QUERYONE = "/user/getuser";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        logger.info("-----------before-----------");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("-----------after-----------");
    }
//
//    @Test
//    public void queryAllUsers() throws Exception {
//    }

    @Test
    public void queryUserById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(URL_QUERYONE).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8).param("id",String.valueOf(1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.account").value("13277992701"))
                .andExpect(jsonPath("$.name").value("anthony1"))
                .andExpect(jsonPath("$.password").value("123456"))
                .andExpect(jsonPath("$.description").value("帅哥"));
    }

}