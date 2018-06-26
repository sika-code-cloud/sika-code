package com.easy.cloud.core.operator.sysuser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.operator.sysuser.pojo.bo.SysUserBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.operator.sysuser.dao.SysUserDAO;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.pojo.entity.SysUserEntity;
import com.easy.cloud.core.operator.sysuser.pojo.query.SysUserQuery;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

/**
 * 描述：服务实现类
 *
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
@Service(value = "sysUserService")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class SysUserServiceImpl extends EcBaseService implements SysUserService {
    /**
     * 数据处理接口
     */
    @Autowired
    private SysUserDAO sysUserDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EcBaseServiceResult register(SysUserDTO sysUserDTO) {
        SysUserBO sysUserBO = new SysUserBO(sysUserDTO);
        sysUserBO.verifyRegisterData();
        sysUserBO.buildRegisterData();
        SysUserEntity sysUserEntity = EcJSONUtils.parseObject(sysUserBO.getSysUserDTO(), SysUserEntity.class);
        sysUserDAO.save(sysUserEntity);
        return EcBaseServiceResult.newInstanceOfSucResult(sysUserEntity);
    }

    @Override
    public EcBaseServiceResult login(SysUserDTO sysUserDTO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUserDTO.getUsername(), sysUserDTO.getPassword());
        Map<String, Object> retMap = EcMapUtils.newHashMap();
        try {
            subject.login(token);
            retMap.put("token", subject.getSession().getId());
            retMap.put("user", subject.getPrincipal());
            retMap.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            throw new EcBaseBusinessException("A_111111111", "帐号/密码错误");
        } catch (LockedAccountException e) {
            retMap.put("msg", "登录失败，该用户已被冻结");
            throw new EcBaseBusinessException("A_111111111", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            throw new EcBaseBusinessException("A_111111111", "该用户不存在");
        }
        return EcBaseServiceResult.newInstanceOfSucResult(retMap);
    }

    @Override
    public SysUserDTO findByUsernameAndPassword(String username, String password) {
        SysUserDTO sysUserDTO = findByUsername(username);
        if (EcBaseUtils.notEquals(sysUserDTO.getPassword(), password)) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.SYS_ERROR, "username or password wrong");
        }
        return sysUserDTO;
    }

    @Override
    public SysUserDTO findByPhoneAndPassword(String phone, String password) {
        SysUserDTO sysUserDTO = findByPhone(phone);
        if (EcBaseUtils.notEquals(sysUserDTO.getPassword(), password)) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.SYS_ERROR, "phone or password wrong");
        }
        return sysUserDTO;
    }

    @Override
    public SysUserDTO findByPhone(String phone) {
        if (EcStringUtils.isEmpty(phone)) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
        }

        SysUserQuery query = new SysUserQuery();
        query.setPhone(phone);
        if (EcStringUtils.isEmpty(query.getPhone())) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
        }
        List<SysUserEntity> userEntitys = sysUserDAO.listByQuery(query);
        if (EcCollectionsUtils.isEmpty(userEntitys)) {
            throw new RuntimeException("用户不存在");
        }
        return EcJSONUtils.parseObject(userEntitys.get(0), SysUserDTO.class);
    }

    @Override
    public EcBaseServiceResult detailByUsername(SysUserQuery sysUserQuery) {
        EcAssert.verifyObjNull(sysUserQuery, "sysUserQuery");
        EcAssert.verifyObjNull(sysUserQuery.getUsername(), "sysUserQuery.username");
        SysUserDTO sysUserDTO = findByUsername(sysUserQuery.getUsername());
        EcAssert.verifyDataNotExistent(sysUserDTO, "sysUserDTO");
        return EcBaseServiceResult.newInstanceOfSucResult(sysUserDTO);
    }

    @Override
    public SysUserDTO findByUsername(String username) {
        if (EcStringUtils.isEmpty(username)) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
        }

        SysUserQuery query = new SysUserQuery();
        query.setUsername(username);
        List<SysUserEntity> userEntities = sysUserDAO.listByQuery(query);
        if (EcCollectionsUtils.isEmpty(userEntities)) {
            throw new RuntimeException("用户不存在");
        }
        return EcJSONUtils.parseObject(userEntities.get(0), SysUserDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EcBaseServiceResult updateAppointUser(SysUserDTO sysUserDTO) {
        EcAssert.verifyObjNull(sysUserDTO, "sysUserDTO");
        EcAssert.verifyObjNull(sysUserDTO.getId(), "sysUserDTO.id");
        SysUserEntity sysUserEntity = sysUserDAO.findById(sysUserDTO.getId());
        sysUserEntity = EcBaseUtils.copeFromObjToTargetObj(sysUserDTO, sysUserEntity);
        sysUserDAO.update(sysUserEntity);
        return EcBaseServiceResult.newInstanceOfSucResult(sysUserEntity);
    }
}
