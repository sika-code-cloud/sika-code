package com.sika.code.demo.interfaces.business.user.service;


import cn.hutool.core.util.IdUtil;
import com.sika.code.core.base.test.BaseTestService;
import com.sika.code.core.base.pojo.query.Page;
import com.google.common.collect.Lists;
import com.sika.code.demo.application.business.user.service.UserService;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:55
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestUserService extends BaseTestService {
    @Resource
    private UserService userService;

    @Test
    public void testFindByPrimaryKey() {
        Long key = 1L;
        UserDTO userDTO = userService.findByPrimaryKey(key);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void testSaveAndRet() {
        UserDTO userDTO = buildUserDTO();
        UserDTO userDTORet = userService.saveAndRet(userDTO);
        Assert.assertNotNull(userDTORet);
    }

    @Test
    public void testSave() {
        UserDTO userDTO = buildUserDTO();
        boolean result = userService.save(userDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveBatchAndRet() {
        List<UserDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserDTO userDTO = buildUserDTO();
            pos.add(userDTO);
        }
        List<UserDTO> posRet = userService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveBatch() {
        UserQuery userQuery = buildUserQuery();
        userQuery.setStartIndex(210000L);
        log.info("查询开始");
        List<UserDTO> userDTOS = userService.list(userQuery);
        log.info("查询结束");
        List<UserDTO> userDTOSForUpdate = Lists.newArrayList();
        log.info("缓存开始");
        for (int i = 0; i < userDTOS.size(); ++i) {
            UserDTO userDTO = buildUserDTO();
            userDTO.setId(userDTOS.get(i).getId());
            if (i % 2 == 0) {
                userDTO.setAddress(IdUtil.objectId());
            } else {
                userDTO.setUsername(IdUtil.simpleUUID());
            }
            userDTOSForUpdate.add(userDTO);
        }
        log.info("缓存结束");
        Long startTime = System.currentTimeMillis();
        log.info("批量写入开始");
        boolean result = userService.saveBatch(userDTOSForUpdate);
        log.info("批量写入结束");
        Long endTime = System.currentTimeMillis();
        log.info("所用时间为：{}ms", (endTime - startTime));
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateAndRet() {
        UserDTO userDTO = buildUserDTO();
        userDTO.setId(null);
        UserDTO userDTORet = userService.saveAndRet(userDTO);
        Assert.assertNotNull(userDTORet);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        UserDTO userDTO = buildUserDTO();
        userDTO.setId(null);
        boolean result = userService.save(userDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateBatchAndRet() {
        List<UserDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserDTO userDTO = buildUserDTO();
            pos.add(userDTO);
        }
        List<UserDTO> posRet = userService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testUpdateBatch() {
        List<UserDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserDTO userDTO = buildUserDTO();
            pos.add(userDTO);
        }
        boolean result = userService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateAndRet() {
        UserDTO userDTO = buildUserDTO();
        UserDTO userDTORet = userService.saveAndRet(userDTO);
        Assert.assertNotNull(userDTORet);
    }

    @Test
    public void testSaveOrUpdate() {
        UserDTO userDTO = buildUserDTO();
        boolean result = userService.save(userDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateBatchAndRet() {
        List<UserDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserDTO userDTO = buildUserDTO();
            pos.add(userDTO);
        }
        List<UserDTO> posRet = userService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveOrUpdateBatch() {
        List<UserDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserDTO userDTO = buildUserDTO();
            pos.add(userDTO);
        }
        boolean result = userService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testFind() {
        UserQuery userQuery = buildUserQuery();
        UserDTO userDTORet = userService.find(userQuery);
        Assert.assertNotNull(userDTORet);
    }

    @Test
    public void testFindId() {
        UserQuery userQuery = buildUserQuery();
        Long key = userService.findId(userQuery);
        Assert.assertNotNull(key);
    }

    @Test
    public void testList() {
        UserQuery userQuery = buildUserQuery();
        List<UserDTO> userDTOS = userService.list(userQuery);
        Assert.assertTrue(userDTOS.size() > 0);
    }

    @Test
    public void testListId() {
        UserQuery userQuery = buildUserQuery();
        List<Long> keys = userService.listId(userQuery);
        Assert.assertTrue(keys.size() > 0);
    }

    @Test
    public void testPage() {
        UserQuery userQuery = buildUserQuery();
        Page<UserDTO> page = userService.page(userQuery);
        Assert.assertTrue(page.getTotal() > 0);
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