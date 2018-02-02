package com.dq.easy.cloud.model.common.date.utils;

import java.util.Date;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.date.constant.DqDateConstant;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

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
	
	public static String formatDate(Date date, String format){
		if(DqBaseUtils.isNull(date)){
			return null;
		}
		if(DqStringUtils.isEmpty(format)){
			format = DqDateConstant.FORMAT_NORMAL;
		}
		return null;
	}
}
