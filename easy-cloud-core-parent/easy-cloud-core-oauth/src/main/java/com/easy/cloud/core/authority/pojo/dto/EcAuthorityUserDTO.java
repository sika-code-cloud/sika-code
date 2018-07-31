package com.easy.cloud.core.authority.pojo.dto;

import org.crazycake.shiro.AuthCachePrincipal;

/**
 * 授权用户数据传输对象接口
 * @author daiqi
 * @create 2018-07-25 13:44
 */
public interface EcAuthorityUserDTO<T> extends AuthCachePrincipal {
    void setPassword(String password);
    void setUsername(String username);
    T getAuthorityUserId();
    String getPassword();
    String getUsername();
    Integer getLocked();
    String getSalt();
}
