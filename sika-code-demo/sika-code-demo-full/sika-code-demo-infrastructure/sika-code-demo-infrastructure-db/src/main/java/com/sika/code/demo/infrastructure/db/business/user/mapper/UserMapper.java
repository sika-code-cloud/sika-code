package com.sika.code.demo.infrastructure.db.business.user.mapper;

import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.infrastructure.db.common.mapper.BaseBizMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:38
 */
public interface UserMapper extends BaseBizMapper<UserPO> {
    List<UserPO> listAsc(@Param(value = "query") UserQuery query);

    /**
     * 自定义批量插入
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int insertBatchReal(@Param("list") List<UserPO> list);

    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int batchUpdateCorporation(@Param("list") List<UserPO> list);
}
