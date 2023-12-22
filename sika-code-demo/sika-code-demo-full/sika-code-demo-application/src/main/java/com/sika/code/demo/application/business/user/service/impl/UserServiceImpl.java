package com.sika.code.demo.application.business.user.service.impl;

import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.domain.business.user.repository.UserRepository;
import com.sika.code.demo.application.business.user.service.UserService;
import com.sika.code.core.base.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:52
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<Long, UserPO, UserDTO, UserRepository> implements UserService {

    static List<UserDTO> userDTOS = Lists.newArrayList();

    static {
        for (int i = 0; i < 1000; ++i) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId((long) i);
            userDTO.setUsername("userName" + i);
            userDTOS.add(userDTO);
        }
    }

    @Override
    public List<UserDTO> readData(UserQuery query) {
        log.info("执行查询：{}", JSONUtil.toJSONString(query));
        List<UserDTO> userDTOSRet = Lists.newArrayList();
        Long start = query.getStartIndex() + 1;
        int count = 0;
        for (int i = start.intValue(); i < userDTOS.size(); ++i) {
            if (count >= query.getPageSize()) {
                break;
            }
            userDTOSRet.add(userDTOS.get(i));
            count ++;
        }
        return userDTOSRet;
    }

    @Override
    public List<UserDTO> readDataRealData(UserQuery query) {
        return convertToDtos(getRepository().listAsc(query));
    }

    @Override
    public void writeData(List<UserDTO> dtos) {
        log.info("执行写入测试---{}", JSONUtil.toJSONString(dtos));
    }
}
