package com.dq.easy.cloud.model.common.number.bigdecimal.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import com.dq.easy.cloud.model.common.number.utils.DqNumberUtils;

/**
 * 
 * <p>
 * BigDecimal工具类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:47:44
 */
public class DqBigDecimalUtils extends DqNumberUtils {
	/** 数值为0的BigDecimal对象 */
	public static final BigDecimal ZERO = createBigDecimal("0");
	/**
	 * 
	 * <p>
	 * 创建BigDecimal对象
	 * </p>
	 *
	 * @param number : String : BigDecimal对象的值
	 * @return BigDecimal
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午3:59:38
	 */
	public static BigDecimal createBigDecimal(String number){
		return NumberUtils.createBigDecimal(number);
	}
	
	/**
	 * 
	 * <p>
	 * bigDecimal1是否不小于bigDecimal2的值，即大于或等于，不区分精度
	 * </p>
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return true bigDecimal1不小于bigDecimal2，否则返回false
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午4:13:19
	 */
	public static boolean isNotLessThan(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		return !isLessThan(bigDecimal1, bigDecimal2);
	}
	
	/**
	 * 
	 * <p>
	 * bigDecimal1是否大于bigDecimal2的值，不区分精度
	 * </p>
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return true bigDecimal1大于bigDecimal2，否则返回false
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午4:13:19
	 */
	public static boolean isGreaterThan(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		int compareResult = bigDecimal1.compareTo(bigDecimal2);
		if(compareResult > 0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * bigDecimal1是否不大于bigDecimal2的值，即小于或等于，不区分精度
	 * </p>
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return true bigDecimal1不大于bigDecimal2，否则返回false
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午4:13:19
	 */
	public static boolean isNotGreaterThan(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		return !isGreaterThan(bigDecimal1, bigDecimal2);
	}
	/**
	 * 
	 * <p>
	 * bigDecimal1是否等于bigDecimal2的值，不区分精度
	 * </p>
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return true bigDecimal1等于bigDecimal2，否则返回false
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午4:13:19
	 */
	public static boolean isEqual(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		int compareResult = bigDecimal1.compareTo(bigDecimal2);
		if(compareResult == 0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * bigDecimal1是否不等于bigDecimal2的值，不区分精度
	 * </p>
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return true bigDecimal1不等于bigDecimal2，否则返回false
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午4:13:19
	 */
	public static boolean isNotEqual(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		return !isEqual(bigDecimal1, bigDecimal2);
	}
	
	/**
	 * 提供精确加法计算的add方法
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确减法运算的sub方法
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确乘法运算的mul方法
	 * 
	 * @param value1
	 *            被乘数
	 * @param value2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double value1, double value2, int scale) throws IllegalAccessException {
		if (scale < 0) {
			// 如果精确范围小于0，抛出异常信息。
			throw new IllegalArgumentException("精确度不能小于0");
		} else if (value2 == 0) {
			// 如果除数为0，抛出异常信息。
			throw new IllegalArgumentException("除数不能为0");
		}
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确加法计算的add方法，确认精确度
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数求和之后，按精度四舍五入的结果
	 */
	public static double add(double value1, double value2, int scale) {
		return round(add(value1, value2), scale);
	}

	/**
	 * 提供精确减法运算的sub方法，确认精确度
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数的求差之后，按精度四舍五入的结果
	 */
	public static double sub(double value1, double value2, int scale) {
		return round(sub(value1, value2), scale);
	}

	/**
	 * 提供精确乘法运算的mul方法，确认精确度
	 * 
	 * @param value1
	 *            被乘数
	 * @param value2
	 *            乘数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数的乘积之后，按精度四舍五入的结果
	 */
	public static double mul(double value1, double value2, int scale) {
		return round(mul(value1, value2), scale);
	}
	
	/**
	 * 
	 * <p>
	 * bigDecimal1是否小于bigDecimal2的值，不区分精度
	 * </p>
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return true bigDecimal1小于bigDecimal2，否则返回false
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午4:13:19
	 */
	public static boolean isLessThan(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		int compareResult = bigDecimal1.compareTo(bigDecimal2);
		if(compareResult < 0){
			return true;
		}
		return false;
	}
}
