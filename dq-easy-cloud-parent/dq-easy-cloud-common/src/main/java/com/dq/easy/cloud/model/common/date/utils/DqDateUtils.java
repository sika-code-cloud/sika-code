package com.dq.easy.cloud.model.common.date.utils;

import java.util.Date;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;

/**
 * 日期工具类
 * 
 * @author daiqi 创建日期 2018年1月3日 下午11:43:31
 */
public class DqDateUtils {
	/** 时间戳进制---1000---TIMESTAMP_HEX */
	private static final int TIMESTAMP_HEX = 1000;

	/**
	 * <p>
	 * 获取当前时间的日期
	 * </p>
	 *
	 * @return Date
	 *
	 *         author daiqi 创建时间 2018年1月3日 下午11:44:25
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 
	 * <p>
	 * 获取当前时间戳的毫秒数
	 * </p>
	 *
	 * @return long
	 * @author daiqi 创建时间 2018年2月9日 上午10:26:30
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * <p>
	 * 获取当前时间戳的秒数
	 * </p>
	 *
	 * @return long
	 * @author daiqi 创建时间 2018年2月25日 下午8:34:35
	 */
	public static long getCurrentTimeSec() {
		return getCurrentTimeMillis() / TIMESTAMP_HEX;
	}

	/**
	 * 
	 * <p>
	 * 获取当前时间戳的秒数---字符串类型
	 * </p>
	 *
	 * @return long
	 * @author daiqi 创建时间 2018年2月25日 下午8:34:35
	 */
	public static String getCurrentTimeSecStr() {
		return String.valueOf(getCurrentTimeSec());
	}
	
	/**
	 * 
	 * <p>
	 * 根据时间戳获取时间对象
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param timestamp
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月2日 下午12:00:56
	 */
	public static Date getDateByTimestamp(Long timestamp) {
		Long timestampMillis = getTimestampMillis(timestamp);
		return new Date(timestampMillis);
	}
	
	/**
	 * 
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
		if (DqBaseUtils.isNull(timestamp)) {
			return null;
		}
		if (timestamp.toString().length() > 10) {
			return timestamp;
		}
		return timestamp * TIMESTAMP_HEX;
	}
}
