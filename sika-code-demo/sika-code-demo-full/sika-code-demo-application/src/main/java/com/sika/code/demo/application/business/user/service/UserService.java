package com.sika.code.demo.application.business.user.service;

import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.core.base.service.BaseService;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:50
 */
public interface UserService extends BaseService<Long, UserDTO> {
    List<UserDTO>  readData(UserQuery query);
    List<UserDTO>  readDataRealData(UserQuery query);
    void writeData(List<UserDTO> dtos);
}
