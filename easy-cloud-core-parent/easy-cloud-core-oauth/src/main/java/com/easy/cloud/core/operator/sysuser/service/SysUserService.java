package com.easy.cloud.core.operator.sysuser.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.pojo.query.SysUserQuery;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;

/**
 * 描述：服务接口
 *
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
public interface SysUserService {
    /**
     * <p>
     * 用户注册
     * </p>
     *
     * @param sysUserDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 11:18
     */
    EcBaseServiceResult register(SysUserDTO sysUserDTO);

    /**
     * <p>
     * 用户登录
     * </p>
     *
     * @param request
     * @param sysUserDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/27 20:07
     */
    EcBaseServiceResult login(ServletRequest request, SysUserDTO sysUserDTO);

    EcBaseServiceResult login(Subject subject, SysUserDTO sysUserDTO);

    /**
     * <p>
     * 根据手机号获取用户信息
     * </p>
     *
     * @param phone : String : 手机号
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 11:18
     */
    SysUserDTO findByPhone(String phone);

    /**
     * <p>
     * 根据用户名获取用户信息
     * </p>
     *
     * @param sysUserQuery
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 11:33
     */
    EcBaseServiceResult detailByUsername(SysUserQuery sysUserQuery);

    /**
     * <p>
     * 根据用户名获取用户信息
     * </p>
     *
     * @param username
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 11:18
     */
    SysUserDTO findByUsername(String username);

    /**
     * <p>
     * 更新指定的用户信息
     * </p>
     *
     * @param sysUserDTO : SysUserDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 11:18
     */
    EcBaseServiceResult updateAppointUser(SysUserDTO sysUserDTO);

}
