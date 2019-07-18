package com.sika.code.standard.token.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.util.Assert;
import com.sika.code.exception.BusinessException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 生成jwt的token格式工具类
 *
 * @author daiqi
 * @create 2018-12-14 10:31
 */
@Slf4j
public class JwtTokenUtil {
    /**
     * jwt协议的sub字段
     */
    public static final String CLAIM_KEY_SUB = "sub";
    /**
     * 创建时间key
     */
    public static final String CLAIM_KEY_CREATED = "created";

    /**
     * jwt默认的有效期Key --- expired
     */
    public static final String EXPIRED_KEY = "expired";

    /**
     * jwt默认的有效期 --- 7 * 24 * 60 * 60 （秒）--- 7天
     */
    private static final long EXPIRED_DEFAULT = 7 * 24 * 60 * 60;

    /**
     * 从token中获取创建时间
     */
    public static Date getCreatedDate(String token, Object secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return getCreatedDate(claims);
    }

    /**
     * 从token中获取token有效结束时间
     */
    public static Date getExpirationDate(String token, Object secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return getExpirationDate(claims);
    }

    /**
     * 从token中获取tokenId
     */
    public static String getTokenId(String token, Object secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return getTokenId(claims);
    }


    /**
     * 从token中获取token有效结束时间
     */
    public static Date getExpirationDateFromClaims(Claims claims) {
        return claims.getExpiration();
    }


    /**
     * <p>
     * 从claims中获取token有效结束时间
     * </p>
     */
    public static Date getExpirationDate(Claims claims) {
        return claims.getExpiration();
    }

    /**
     * 从claims中获取创建时间
     */
    public static Date getCreatedDate(Claims claims) {
        return new Date(MapUtil.getLong(claims, CLAIM_KEY_CREATED));
    }


    /**
     * 从Claims中获取tokenId
     */
    private static String getTokenId(Claims claims) {
        return claims.getId();
    }

    /**
     * 从token中获取Cliaim
     */
    public static Claims getClaimsFromToken(String token, Object secretKey) {
        Assert.verifyObjNull(secretKey, "加密的秘钥secret");
        return getClaimsFromTokenCore(token, secretKey);
    }

    /**
     * 从token中获取Cliaim
     */
    public static Claims getClaimsFromTokenCore(String token, Object secretKey) {
        Assert.verifyObjNull(secretKey, "加密的秘钥secret");
        Claims claims;
        try {
            JwtParser jwtParser = Jwts.parser();
            if (secretKey instanceof String) {
                jwtParser.setSigningKey((String) secretKey);
            } else if (secretKey instanceof Key) {
                jwtParser.setSigningKey((Key) secretKey);
            }
            claims = jwtParser.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR, "token");
        }
        return claims;
    }

    /**
     * <p>
     * 生成有效时间
     * </p>
     *
     * @param expirationSecond : Long : 有效的时间单位秒
     * @return java.manager.Date
     * @author daiqi
     * @date 2018/12/14 10:39
     */
    private static Date generateExpirationDate(Long expirationSecond) {
        return new Date(DateUtil.current(false) + expirationSecond * 1000);
    }

    /**
     * <p>
     * token到期 true为到期 false没有到期
     * </p>
     *
     * @return java.lang.Boolean
     * @author daiqi
     * @date 2018/12/14 13:55
     */
    public static Boolean isTokenExpired(Claims claims) {
        final Date expiration = getExpirationDate(claims);
        // 未设置表示永远不过期
        return expiration.before(new Date());
    }

    /**
     * <p>
     * 生成jwtToken
     * </p>
     *
     * @param claims
     * @param secretKey
     * @return java.lang.String
     * @author daiqi
     * @date 2019/1/10 16:17
     */
    public static String generateToken(Map<String, Object> claims, String secretKey) {
        Assert.verifyStrEmpty(secretKey, "加密的秘钥secret");
        return generateTokenCore(claims, SignatureAlgorithm.HS512, secretKey);
    }

    private static String generateTokenCore(Map<String, Object> claims, SignatureAlgorithm algorithm, Object secretKey) {
        Assert.verifyObjNull(secretKey, "加密的秘钥secret");

        Long expirationForSecond = MapUtil.getLong(claims, EXPIRED_KEY);
        if (expirationForSecond == null) {
            expirationForSecond = EXPIRED_DEFAULT;
        }
        Date expiration = generateExpirationDate(expirationForSecond);

        String id = MapUtil.getStr(claims, Claims.ID);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setExpiration(expiration);
        if (secretKey instanceof String) {
            jwtBuilder.signWith(algorithm, (String) secretKey);
        } else if (secretKey instanceof Key) {
            jwtBuilder.signWith(algorithm, (Key) secretKey);
        }
        return jwtBuilder.compact();
    }

}
