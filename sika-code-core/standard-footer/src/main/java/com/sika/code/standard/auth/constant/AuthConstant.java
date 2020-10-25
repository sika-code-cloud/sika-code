package com.sika.code.standard.auth.constant;

/**
 * @author daiqi
 * @create 2018-12-14 15:04
 */
public class AuthConstant {
    /**
     * AuthorizationKey --- Authorization
     */
    public static final String AUTHORIZATION_KEY = "Authorization";
    /**
     * authorizationToken的Key --- authorizationToken
     */
    public static final String AUTHORIZATION_TOKEN_KEY = "authorizationToken";
    /**
     * tokenStart --- Bearer
     */
    public static final String TOKEN_HEAD_START = "Bearer ";
    /**
     * sessionIdKey --- sessionId
     */
    public static final String SESSION_ID_KEY = "sessionId";
    /**
     * oauth2.0协议的tokenkey --- oauth2Token
     */
    public static final String OAUTH2_TOKEN_KEY = "oauth2Token";
    /**
     * 登录成功后重定向的url的参数名称 --- loginSuccessRedirectUrl
     */
    public static final String LOGIN_SUCCESS_REDIRECT_URL_KEY = "loginSuccessRedirectUrl";
    /**
     * 认证的用户key
     */
    public static final String AUTH_USER_KEY = "auth_user";
    /**
     * 认证的用户详情key
     */
    public static final String AUTH_USER_DETAIL_KEY = "auth_user_detail";

    public static class AuthRedisKey {
        /**
         * 认证登录用户数据key---username为key
         */
        public static final String AUTH_LOGIN_USER_BY_USERNAME = "oauth:login:user:username";
        /**
         * 认证token的uuid数据key---id
         */
        public static final String AUTH_LOGIN_TOKEN_ID = "oauth:login:token:id";
    }

    /**
     * 认证的有效期常量类
     */
    public static class AuthExpiresIn {
        /**
         * 有效期 --- 认证用户 --- 60 * 60 * 24 * 7（秒） --- 7天
         */
        public static final long AUTH_USER = 60 * 60 * 24 * 7;
        /**
         * 有效期 --- TOKEN的唯一标识 --- 60 * 30（秒） --- 30分钟
         */
        public static final long TOKEN_ID = 60 * 30;
    }
}
