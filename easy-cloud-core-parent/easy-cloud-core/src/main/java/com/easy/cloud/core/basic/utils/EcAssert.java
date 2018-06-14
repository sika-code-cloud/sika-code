package com.easy.cloud.core.basic.utils;

import java.util.Collection;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 断言工具类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月9日 下午3:38:09
 */
public class EcAssert {
	/**
	 * 
	 * <p>
	 * 校验对象是否存在
	 * </p>
	 * 
	 * <pre>
	 * 对象不为空即是存在：抛出异常
	 * </pre>
	 * 
	 * @param obj : List : 待校验obj实例
	 * @param formatValues : Object... : 格式化错误信息的的值
	 * @author daiqi
	 * @创建时间 2018年6月9日 下午3:34:46
	 */
	public static void verifyDataExistent(Object obj, Object... formatValues) {
		if (EcBaseUtils.isNull(obj)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.DATA_NON_EXISTENT, formatValues);
		}
	}

	/**
	 * 
	 * <p>
	 * 校验对象是否不存在
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
		if (EcBaseUtils.isNull(obj)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.DATA_NON_EXISTENT, formatValues);
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
		if (EcBaseUtils.isNull(obj)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL, formatValues);
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
		if (EcBaseUtils.isNull(obj)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.LIST_CANT_NULL, formatValues);
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
		if (EcCollectionsUtils.isEmpty(obj)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.LIST_CANT_EMPTY, formatValues);
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
		if (EcStringUtils.isEmpty(str)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.STR_CANT_EMPTY, formatValues);
		}
	}
}
