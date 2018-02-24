package com.dq.easy.cloud.pay.model.base.utils;

import java.util.Date;

import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;

/**
 * 
 * <p>
 * 支付工具类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午5:18:01
 */
public class DqPayUtils {

	/**
	 * 
	 * <p>
	 * 订单号生产者工具类
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月24日 下午5:19:47
	 */
	public static class DqOrderNoGenerator {
		/** 产生支付订单 */
		public static String generatePayOrderNO() {
			return generateOrderNOCore("PON");
		}
		
		/**
		 * 
		 * <p>
		 * 核心产生订单号的方法
		 * </p>
		 *
		 * @param prefix : String : 订单前缀
		 * @return String
		 * @author daiqi
		 * 创建时间    2018年2月24日 下午5:23:03
		 */
		private static String generateOrderNOCore(String prefix){
			return prefix + DqDateFormatUtils.format(DqDateUtils.getCurrentDate(), DqDateFormatUtils.FORMAT_LONG_MILLIS);
		}
	}
}
