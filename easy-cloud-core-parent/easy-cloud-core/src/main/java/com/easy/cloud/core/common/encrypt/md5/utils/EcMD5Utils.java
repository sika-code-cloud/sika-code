package com.easy.cloud.core.common.encrypt.md5.utils;
import org.apache.commons.codec.digest.DigestUtils;

import com.easy.cloud.core.common.encrypt.common.utils.EcEncryptUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * MD5签名工具
 */
public class EcMD5Utils {

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param key           密钥
     * @param inputCharset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String inputCharset) {
        //拼接key
        text = text + key;
        return DigestUtils.md5Hex(EcEncryptUtils.getContentBytes(text, inputCharset));
    }

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param sign          签名结果
     * @param key           密钥
     * @param inputCharset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String inputCharset) {
        //判断是否一样
        return EcStringUtils.equals(sign(text, key, inputCharset).toUpperCase(), sign.toUpperCase());
    }




}