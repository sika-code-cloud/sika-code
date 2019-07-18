package com.sika.code.standard.auth.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.sika.code.standard.auth.constant.AuthConstant;
import com.sika.code.standard.token.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 认证鉴权工具类
 *
 * @author daiqi
 * @create 2018-12-14 22:06
 */
@Component
@Slf4j
public class AuthUtil {
    /**
     * uuid的有效期 --- 1800秒
     */
    private static long TOKEN_ID_EXPIRES_IN = AuthConstant.AuthExpiresIn.TOKEN_ID;
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String CLAIM_KEY_USER_ID = "userId";

    private static RedisTemplate<String, Object> redisTemplate;
    private static HttpSession httpSession;
    private static HttpServletRequest httpServletRequest;

    @Autowired
    public void setHttpSession(HttpSession httpSession) {
        AuthUtil.httpSession = httpSession;
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        AuthUtil.httpServletRequest = httpServletRequest;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        AuthUtil.redisTemplate = redisTemplate;
    }

    /**  
     * <p>
     * 从请求中获取username
     * </p>
     * @author daiqi
     * @date 2019/5/15 14:25
     * @param secret : jwt加密的密钥
     * @return java.lang.String  
     */  
    public static String getUsernameFromRequest(String secret) {
        String token = getAuthToken(httpServletRequest);
        return getUsername(token, secret);
    }

    /**
     * <p>
     * 从请求中获取userId
     * </p>
     * @author daiqi
     * @date 2019/5/15 14:25
     * @param secret : jwt加密的密钥
     * @return java.lang.String
     */
    public static String getUserIdFromRequest(String secret) {
        String token = getAuthToken(httpServletRequest);
        return getUserId(token, secret);
    }

    /**
     * 获取认证的token
     */
    public static String getAuthToken(HttpServletRequest request) {
        String tokenHeaderName = AuthConstant.AUTHORIZATION_KEY;
        return getTokenFromRequest(request, tokenHeaderName);
    }

    /**
     * <p>
     * 获取oauth2.0协议的token
     * </p>
     *
     * @param request
     * @return java.lang.String
     * @author daiqi
     * @date 2019/1/11 14:03
     */
    public static String getOauth2Token(HttpServletRequest request) {
        String oauth2TokenName = AuthConstant.OAUTH2_TOKEN_KEY;
        return getTokenFromRequest(request, oauth2TokenName);
    }

    /**
     * 从请求中获取token
     */
    private static String getTokenFromRequest(HttpServletRequest request, String tokenName) {
        // 从请求缓存attribute中获取信息
        String token = (String) request.getAttribute(tokenName);
        if (StrUtil.isNotBlank(token)) {
            return token;
        }
        //先从url中取token
        token = request.getParameter(tokenName);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        String tokenHeadStart = AuthConstant.TOKEN_HEAD_START;
        String tokenHeader = request.getHeader(tokenName);
        if (StrUtil.isNotBlank(tokenHeader) && tokenHeader.startsWith(tokenHeadStart)) {
            //如果header中存在token，则覆盖掉url中的token
            // "Bearer "之后的内容
            token = tokenHeader.substring(tokenHeadStart.length()).trim();
            // 设置到请求attrbute中
            request.setAttribute(tokenName, token);
        }
        return token;
    }

    /**
     * 获取用户id
     */
    public static String getUserId(String token, String secret) {
        Claims claims = JwtTokenUtil.getClaimsFromToken(token, secret);
        return getUserId(claims);
    }

    /**
     * 获取用户名
     */
    public static String getUsername(String token, String secret) {
        Claims claims = JwtTokenUtil.getClaimsFromToken(token, secret);
        return getUsername(claims);
    }

    /**
     * 从claims获取用户名
     */
    private static String getUsername(Claims claims) {
        return claims.getSubject();
    }

    /**
     * 从Claims中获取userId
     */
    private static String getUserId(Claims claims) {
        return MapUtil.getStr(claims, CLAIM_KEY_USER_ID);
    }

}