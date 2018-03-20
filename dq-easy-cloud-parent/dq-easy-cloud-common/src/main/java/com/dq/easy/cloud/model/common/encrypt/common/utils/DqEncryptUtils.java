package com.dq.easy.cloud.model.common.encrypt.common.utils;

import java.io.UnsupportedEncodingException;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 加密工具类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午2:31:38
 */
public class DqEncryptUtils {
	/**
     * @param content 需要加密串
     * @param charset 字符集
     * @return 加密后的字节数组
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (DqStringUtils.isEmpty(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("转码过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
}
