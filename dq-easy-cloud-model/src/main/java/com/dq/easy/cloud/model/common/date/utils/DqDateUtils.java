package com.dq.easy.cloud.model.common.date.utils;

import java.util.Date;

/**
 * 日期工具类
 * @author daiqi
 * 创建日期 2018年1月3日 下午11:43:31
 */
public class DqDateUtils {
	
	/**
	 * <p>获取当前时间的日期</p>
	 *
	 * @return Date
	 *
	 * author daiqi
	 * 创建时间  2018年1月3日 下午11:44:25
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	
	/**
	 * 
	 * <p>
	 * 获取当前时间戳的毫秒数
	 * </p>
	 *
	 * @return long
	 * @author daiqi
	 * 创建时间    2018年2月9日 上午10:26:30
	 */
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
}
