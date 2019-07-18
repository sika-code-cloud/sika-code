package com.sika.code.common.date.util;

import com.sika.code.basic.util.BaseUtil;

/**
 * 日期工具类
 *
 * @author daiqi 创建日期 2018年1月3日 下午11:43:31
 */
public class DateUtil extends cn.hutool.core.date.DateUtil {
    /**
     * 时间戳进制---1000---TIMESTAMP_HEX
     */
    private static final int TIMESTAMP_HEX = 1000;
    /**
     * 时间戳秒的长度---10
     */
    private static final int TIMESTAMP_SEC_LENGTH = 10;

    /**
     * <p>
     * 获取时间戳的毫秒数
     * </p>
     *
     * @param timestamp
     * @return
     * @author daiqi
     * 创建时间    2018年3月2日 下午12:01:56
     */
    public static Long getTimestampMillis(Long timestamp) {
        if (BaseUtil.isNull(timestamp)) {
            return null;
        }
        if (timestamp.toString().length() > TIMESTAMP_SEC_LENGTH) {
            return timestamp;
        }
        return timestamp * TIMESTAMP_HEX;
    }
}
