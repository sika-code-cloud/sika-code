package com.sika.code.demo.interfaces.business.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sika.code.core.base.test.BaseTestController;
import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 * 用户表控制器测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:56
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestUserController extends BaseTestController {

    @Test
    public void testSave() {
        UserDTO userDTO = buildUserDTO();
        Result result = postJson("/user/save", userDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testSaveBatch() {
    List<UserDTO> DTOs = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserDTO userDTO = buildUserDTO();
            DTOs.add(userDTO);
        }
        Result result = postJson("/user/saveBatch", DTOs);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testUpdateById() {
        UserDTO userDTO = buildUserDTO();
        Result result = postJson("/user/save", userDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testPage() {
        UserQuery query = buildUserQuery();
        Result result = postJson("/user/page", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testFind() {
        UserQuery query = buildUserQuery();
        Result result = postJson("/user/find", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testList() {
        UserQuery query = buildUserQuery();
        Result result = postJson("/user/list", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    private UserDTO buildUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(null);
        userDTO.setCreateBy(null);
        userDTO.setUpdateBy(null);
        userDTO.setUsername(null);
        userDTO.setPassword(null);
        userDTO.setOauthPwd(null);
        userDTO.setNickname(null);
        userDTO.setSex(null);
        userDTO.setPhone(null);
        userDTO.setEmail(null);
        userDTO.setAvatar(null);
        userDTO.setProvinceCode(null);
        userDTO.setToken(null);
        userDTO.setType(null);
        userDTO.setCityCode(null);
        userDTO.setCountyCode(null);
        userDTO.setAddress(null);
        userDTO.setDeleted(null);
        return userDTO;
    }

    private UserQuery buildUserQuery() {
        UserQuery userQuery = new UserQuery();
        userQuery.setId(null);
        userQuery.setCreateBy(null);
        userQuery.setUpdateBy(null);
        userQuery.setUsername(null);
        userQuery.setPassword(null);
        userQuery.setOauthPwd(null);
        userQuery.setNickname(null);
        userQuery.setSex(null);
        userQuery.setPhone(null);
        userQuery.setEmail(null);
        userQuery.setAvatar(null);
        userQuery.setProvinceCode(null);
        userQuery.setToken(null);
        userQuery.setType(null);
        userQuery.setCityCode(null);
        userQuery.setCountyCode(null);
        userQuery.setAddress(null);
        userQuery.setDeleted(null);
        return userQuery;
    }
}