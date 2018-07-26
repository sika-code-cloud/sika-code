package com.easy.cloud.core.operator.sysuser.service.impl;

import com.easy.cloud.core.authority.constant.EcAuthorityConstant;
import com.easy.cloud.core.authority.constant.EcAuthorityErrorCodeEnum;
import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.http.utils.EcRequestUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.operator.sysuser.dao.SysUserDAO;
import com.easy.cloud.core.operator.sysuser.pojo.bo.SysUserBO;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.pojo.entity.SysUserEntity;
import com.easy.cloud.core.operator.sysuser.pojo.query.SysUserQuery;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import java.util.List;

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
    public EcBaseServiceResult login(Subject subject, SysUserDTO sysUserDTO) {
        UsernamePasswordToken token = new UsernamePasswordToken(sysUserDTO.getUsername(), sysUserDTO.getPassword());
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            throw new EcBaseBusinessException(EcAuthorityErrorCodeEnum.USERNAME_PASSWORD_WRONG);
        } catch (LockedAccountException e) {
            throw new EcBaseBusinessException(EcAuthorityErrorCodeEnum.USER_LOCKED);
        } catch (AuthenticationException e) {
            throw new EcBaseBusinessException(EcAuthorityErrorCodeEnum.USER_NOT_EXIST);
        }
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @Override
    public EcBaseServiceResult login(ServletRequest request, SysUserDTO sysUserDTO) {
        String username = EcRequestUtils.getTObjFromAttribute(request, EcAuthorityConstant.USERNAME, String.class);
        if (EcStringUtils.isEmpty(username)) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.DATA_ERROR, "登录信息");
        }
        sysUserDTO.setUsername(username);
        Subject subject = SecurityUtils.getSubject();
        return login(subject, sysUserDTO);
    }

    @Override
    public SysUserDTO findByPhone(String phone) {
        EcAssert.verifyStrEmpty(phone, "phone");
        SysUserQuery query = new SysUserQuery();
        query.setPhone(phone);
        List<SysUserEntity> userEntitys = sysUserDAO.listByQuery(query);
        if (EcCollectionsUtils.isEmpty(userEntitys)) {
            return null;
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
        EcAssert.verifyStrEmpty(username, "username");

        SysUserQuery query = new SysUserQuery();
        query.setUsername(username);
        List<SysUserEntity> userEntities = sysUserDAO.listByQuery(query);
        if (EcCollectionsUtils.isEmpty(userEntities)) {
            return null;
        }
        return EcJSONUtils.parseObject(userEntities.get(0), SysUserDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EcBaseServiceResult updateAppointUser(SysUserDTO sysUserDTO) {
        EcAssert.verifyObjNull(sysUserDTO, "sysUserDTO");
        EcAssert.verifyObjNull(sysUserDTO.getId(), "id");
        SysUserEntity sysUserEntity = sysUserDAO.findById(sysUserDTO.getId());
        sysUserEntity = EcBaseUtils.copeFromObjToTargetObj(sysUserDTO, sysUserEntity);
        sysUserDAO.update(sysUserEntity);
        return EcBaseServiceResult.newInstanceOfSucResult(sysUserEntity);
    }
}
