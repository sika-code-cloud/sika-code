package com.sika.code.demo.domain.business.user.repository.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.infrastructure.db.business.user.mapper.UserMapper;
import com.sika.code.demo.domain.business.user.repository.UserRepository;
import com.sika.code.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:42
 */
@Repository
public class UserRepositoryImpl extends BaseRepositoryMyBatisPlusImpl<UserPO, Long, UserMapper> implements UserRepository {

    @Override
    public void verifyUserUnExistById(Long id) {
        Assert.notNull(id, "用户表主键ID不能为空");
        UserPO po = findByPrimaryKey(id);
        Assert.notNull(po, "主键【{}】对应的用户表数据不存在，请核实", id);
    }

    @Override
    public List<UserPO> listAsc(UserQuery userQuery) {
        return getMapper().listAsc(userQuery);
    }

    @Override
    public int insertBatchReal(List<UserPO> list) {
        return getMapper().insertBatchReal(list);
    }

    @Override
    public int updateBatchReal(List<UserPO> list) {
        List<Long> ids = list.stream().map(UserPO::getId).collect(Collectors.toList());
        UpdateWrapper<UserQuery> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 211091L);
        updateWrapper.in("id", ids);
        return getMapper().updateBatchCaseWhen(list, updateWrapper);
    }

}

