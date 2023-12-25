package com.sika.code.demo.domain.business.user.repository;

import com.sika.code.demo.domain.common.base.repository.BaseBizRepository;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.infrastructure.db.business.user.mapper.UserMapper;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;

import java.util.List;

/**
 * <p>
 * 用户表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:41
 */
public interface UserRepository extends BaseBizRepository<UserPO, UserMapper> {
    /**
     * 校验ID对应的协作器是否不存在-不存在抛出异常
     *
     * @param id : 主键ID
     */
    void verifyUserUnExistById(Long id);

    List<UserPO> listAsc(UserQuery userQuery);

    /**
     * 自定义批量插入
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int insertBatchReal(List<UserPO> list);

    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int updateBatchReal(List<UserPO> list);

}
