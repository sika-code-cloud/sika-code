package com.easy.cloud.core.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.authority.dao.SysUserDAO;
import com.easy.cloud.core.authority.pojo.SysUser;
import com.easy.cloud.core.authority.service.SysUserService;


/**
 * Created by Administrator on 2018/1/12.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void myTest() throws Exception {
//        DbContextHolder.setDbType(DBTypeEnum.shiro);
        SysUser user = new SysUser();
//        user.setId(UUID.randomUUID().toString());
        user.setUsername("alice");
        user.setPassword("123456");
        sysUserDAO.save(user);
//        user.insert();
//        Aatest aatest = new Aatest();
//        aatest.setProid("123");
//        aatest.setContent("ksjdjf");
//        aatest.insert();
//        aatest.insert();
//        int i = 1/0;
//        System.err.println("查询插入结果：" + user.selectById().getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public List<SysUser> myTest2() throws Exception {
//        DbContextHolder.setDbType(DBTypeEnum.shiro);
        SysUser user = new SysUser();
//        System.err.println("删除所有：" + user.delete(null));
        user.setUsername("alice");
        user.setPassword("123");
//        user.setId(UUID.randomUUID().toString());
        sysUserDAO.save(user);
        System.err.println("查询插入结果：" + user.getUsername());
        user.setUsername("mybatis-plus-ar");
        System.err.println("更新：" + sysUserDAO.update(user));
        return sysUserDAO.listPage(null);
    }
}
