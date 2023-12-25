package com.sika.code.demo.interfaces.business.user.repository;


import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import com.sika.code.core.base.test.BaseTestRepository;
import com.sika.code.demo.domain.business.user.repository.UserRepository;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表持久化操作测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:54
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestOrderRepository extends BaseTestRepository {
    @Resource
    private UserRepository userRepository;

    @Test
    public void testFindByPrimaryKey() {
        Long primaryKey = 1L;
        UserPO userPO = userRepository.findByPrimaryKey(primaryKey);
        Assert.assertNotNull(userPO);
    }

    @Test
    public void testUpdateSelectiveByPrimaryKey() {
        UserPO userPO = buildUserPO();
        userPO.setId(null);
        int count = userRepository.save(userPO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        UserPO userPO = buildUserPO();
        Long primaryKey = userRepository.saveRetId(userPO);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testInsertSelective() {
        UserPO userPO = buildUserPO();
        int count = userRepository.save(userPO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatchSelective() {
        List<UserPO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            UserPO userPO = buildUserPO();
            pos.add(userPO);
        }
        int count = userRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void testUpdateBatchSelectiveByPrimaryKey() {
        UserQuery userQuery = buildUserQuery();
        userQuery.setStartIndex(180090L);
        log.info("查询开始");
        List<UserPO> userDTOS = userRepository.list(userQuery);
        log.info("查询结束");
        List<UserPO> userDTOSForUpdate = Lists.newArrayList();
        log.info("缓存开始");
        log.info("缓存结束");
        Long startTime = System.currentTimeMillis();
        log.info("批量写入开始");
        int count = 0;
        for (int i = 0; i < userDTOS.size(); ++i) {
            UserPO userDTO = buildUserPO();
            userDTO.setId(userDTOS.get(i).getId());
            userDTO.setAddress(IdUtil.simpleUUID());
            userDTO.setUsername(IdUtil.objectId());
            userDTO.setNickname(IdUtil.objectId());
            userDTO.setEmail(IdUtil.fastSimpleUUID());
            userDTOSForUpdate.add(userDTO);
        }
        count += userRepository.updateBatch(userDTOSForUpdate, new UserQuery());
        log.info("批量写入结束-更新的数据量为{}", count);
        Long endTime = System.currentTimeMillis();
        log.info("所用时间为：{}ms", (endTime - startTime));
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testFind() {
        UserQuery userQuery = buildUserQuery();
        UserPO po = userRepository.find(userQuery);
        Assert.assertNotNull(po);
    }

    @Test
    public void testFindId() {
        UserQuery userQuery = buildUserQuery();
        Long primaryKey = userRepository.findId(userQuery);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testList() {
        UserQuery userQuery = buildUserQuery();
        List<UserPO> pos = userRepository.list(userQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testListId() {
        UserQuery userQuery = buildUserQuery();
        List<Long> primarys = userRepository.listId(userQuery);
        Assert.assertTrue(primarys.size() > 0);
    }

    @Test
    public void testPage() {
        UserQuery userQuery = buildUserQuery();
        List<UserPO> pos = userRepository.page(userQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testCount() {
        UserQuery userQuery = buildUserQuery();
        int count = userRepository.count(userQuery);
        Assert.assertTrue(count > 0);
    }


    private UserPO buildUserPO() {
        UserPO userPO = new UserPO();
        userPO.setId(null);
        userPO.setCreateBy(null);
        userPO.setUpdateBy(null);
        userPO.setUsername(null);
        userPO.setPassword(null);
        userPO.setOauthPwd(null);
        userPO.setNickname(null);
        userPO.setSex(null);
        userPO.setPhone(null);
        userPO.setEmail(null);
        userPO.setAvatar(null);
        userPO.setProvinceCode(null);
        userPO.setToken(null);
        userPO.setType(null);
        userPO.setCityCode(null);
        userPO.setCountyCode(null);
        userPO.setAddress(null);
        userPO.setDeleted(null);
        return userPO;
    }

    private UserQuery buildUserQuery() {
        UserQuery userQuery = new UserQuery();
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