package com.sika.code.basic.util;


import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.util.CollectionUtil;
import com.sika.code.exception.BusinessException;

import java.util.Collection;

/**
 * 
 * <p>
 * 断言工具类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月9日 下午3:38:09
 */
public class Assert {
	/**
	 * 
	 * <p>
	 * 校验对象是否存在
	 * </p>
	 * 
	 * <pre>
	 * 对象不为空即是存在：抛出异常
	 * 若对象为boolean类型则 为true是表示存在 抛出异常，否则表示不存在
	 * </pre>
	 * 
	 * @param obj : List : 待校验obj实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:34:46
	 */
	public static void verifyDataExistent(Object obj, Object... formatValues) {
		if (BaseUtil.isNull(obj)) {
			return;
		}
		boolean isBoolean = (obj instanceof Boolean || boolean.class.equals(obj.getClass()));
		if (isBoolean && (Boolean) obj) {
			throw new BusinessException(BaseErrorCodeEnum.ALREADY_EXISTENT).buildFormatValues(formatValues);
		}
	}

	/**
	 * 
	 * <p>
	 * 校验对象是否不存在
	 * 若对象为boolean类型则 为true是表示不存在 抛出异常，否则表示存在
	 * </p>
	 * 
	 * <pre>
	 * 数据为空即是不存在:抛出异常
	 * </pre>
	 * 
	 * @param obj : List : 待校验obj实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:34:46
	 */
	public static void verifyDataNotExistent(Object obj, Object... formatValues) {
		BusinessException exception = new BusinessException(BaseErrorCodeEnum.NON_EXISTENT).buildFormatValues(formatValues);
		if (BaseUtil.isNull(obj)) {
			throw exception;
		}
		if (obj instanceof Boolean || boolean.class.equals(obj.getClass())) {
			if ((Boolean) obj) {
				throw exception;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 校验对象是否为空
	 * </p>
	 *
	 * <pre>
	 * 对象为空抛出异常
	 * </pre>
	 *
	 * @param obj : List : 待校验obj实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:34:46
	 */
	public static void verifyObjNull(Object obj, Object... formatValues) {
		if (BaseUtil.isNull(obj)) {
			throw new BusinessException(BaseErrorCodeEnum.OBJECT_NULL).buildFormatValues(formatValues);
		}
	}

	/**
	 * 
	 * <p>
	 * 校验列表为空
	 * </p>
	 *
	 * <pre>
	 * 列表为空抛出异常
	 * </pre>
	 *
	 * @param obj : List : 待校验的List实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:32:41
	 */
	public static void verifyListNull(Collection<?> obj, Object... formatValues) {
		if (BaseUtil.isNull(obj)) {
			throw new BusinessException(BaseErrorCodeEnum.LIST_NULL).buildFormatValues(formatValues);
		}
	}

	/**
	 * 
	 * <p>
	 * 校验列表为空列表: 抛出异常
	 * </p>
	 * 
	 * <pre>
	 * 列表为空抛出异常
	 * </pre>
	 * 
	 * @param obj : List : 待校验的List实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:32:41
	 */
	public static void verifyListEmpty(Collection<?> obj, Object... formatValues) {
		verifyListNull(obj, formatValues);
		if (CollectionUtil.isEmpty(obj)) {
			throw new BusinessException(BaseErrorCodeEnum.LIST_EMPTY).buildFormatValues(formatValues);
		}
	}

	/**
	 * 
	 * <p>
	 * 校验字符串是否为空或者空串
	 * </p>
	 * 
	 * <pre>
	 * 字符串为空串抛出异常
	 * </pre>
	 * 
	 * @param str : List : 待校验的String实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:37:30
	 */
	public static void verifyStrEmpty(String str, Object... formatValues) {
		if (StringUtil.isEmpty(str)) {
			throw new BusinessException(BaseErrorCodeEnum.STR_EMPTY).buildFormatValues(formatValues);
		}
	}
}
