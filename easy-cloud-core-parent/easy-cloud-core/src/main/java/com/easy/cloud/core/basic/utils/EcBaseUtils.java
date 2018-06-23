package com.easy.cloud.core.basic.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;

/**
 * 
 * @ClassName : DqBaseUtils
 * @Description : 基础工具类
 * @author daiqi
 * @date 2017年12月6日 下午1:46:43
 *
 */
public class EcBaseUtils {
	/**
	 * 
	 * <p>
	 * 判断obj是否为空，为空返回true
	 * </p>
	 *
	 * @param obj
	 * @return true 对象为空
	 * @author daiqi
	 * @date 2017年12月6日 下午1:47:50
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 判断obj是否不为空
	 * </p>
	 *
	 * @param obj
	 * @return
	 * @author daiqi
	 * @date 2017年12月6日 下午1:48:38
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 
	 * <p>
	 * 将fromObj中对象中的非空数据拷贝到targetObj对象中
	 * </p>
	 *
	 * <pre>
	 *     只拷贝fromObj对象中的属性值非空的属性
	 * </pre>
	 *
	 * @param fromObj : 被拷贝数据的对象
	 * @param targetObj : 拷贝的目标对象
	 * @return 拷贝完成的对象
	 * @author daiqi
	 * @date 2017年12月6日 下午1:54:39
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copeFromObjToTargetObj(Object fromObj, T targetObj) {
		if (isNull(fromObj) || isNull(targetObj)) {
			return targetObj;
		}
		// 获取目标对象的class
		Class<T> clazz = (Class<T>) targetObj.getClass();
		// 将fromObje转换为map
		Map<String, Object> fromObjMap = EcJSONUtils.parseObject(fromObj, HashMap.class);
		// 将目标对象转换为map
		Map<String, Object> targetObjMap = EcJSONUtils.parseObject(targetObj, HashMap.class);
		// 循环fromObjMap并将其值put到targetObjMap中
		for (String newsKey : fromObjMap.keySet()) {
			if (fromObjMap.get(newsKey) != null) {
				targetObjMap.put(newsKey, fromObjMap.get(newsKey));
			}
		}
		// 将targetObjMap转换为目标类的对象
		return EcJSONUtils.parseObject(targetObjMap, clazz);
	}


	/**
	 * <p>
	 * 比较两个对象是否相等、若为原始类型与包装类型比较则将原始类型放第二个参数
	 * </p>
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 * @author daiqi 创建时间 2018年2月7日 下午7:52:56
	 */
	public static boolean equals(Object obj1, Object obj2) {
		if (isNull(obj1) || isNull(obj2)) {
			return false;
		}
		if (obj1.equals(obj2)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 比较两个对象是否不相等、若为原始类型与包装类型比较则将原始类型放第二个参数
	 * </p>
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 * @author daiqi 创建时间 2018年2月7日 下午7:52:56
	 */
	public static boolean notEquals(Object obj1, Object obj2) {
		return !equals(obj1, obj2);
	}

	/**
	 * <p>
	 * 判断某常量值是否存在常量类中
	 * </p>
	 * 
	 * @author daiqi
	 * @date 2018/1/26 11:58
	 * @param [constantValue]
	 * @return java.lang.Boolean
	 */
	public static boolean isExistConstantValue(Class<?> constantClazz, Object constantValue) {
		if (isNull(constantClazz) || isNull(constantValue)) {
			return false;
		}
		Map<String, Object> nameAndValues = null;
		if (constantClazz.isEnum()) {
			nameAndValues = getEnumsByEnumClass(constantClazz);
		} else {
			nameAndValues = getFieldNameAndValues(constantClazz);
		}
		if (EcMapUtils.isEmpty(nameAndValues)) {
			return false;
		}
		for (Object value : nameAndValues.values()) {
			if (EcBaseUtils.equals(value, constantValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 获取指定枚举class中所有的枚举类放入map中
	 * </p>
	 *
	 * @param targetClass
	 * @return
	 * @author daiqi 创建时间 2018年4月13日 上午10:09:21
	 */
	public static Map<String, Object> getEnumsByEnumClass(Class<?> targetClass) {
		Map<String, Object> eunmKeyAndValue = EcMapUtils.newHashMap();
		// 不是枚举类型直接返回
		if (!targetClass.isEnum()) {
			return eunmKeyAndValue;
		}
		try {
			// 这里是获得枚举的遍历方法 即:枚举对象.values();一样
			Method method = targetClass.getMethod("values");
			// 这里获取所有的枚举对象 method.invoke(null)也可写成method.invoke(null,null)
			Enum<?> inter[] = (Enum[]) method.invoke(null);
			for (Enum<?> enumMessage : inter) {
				eunmKeyAndValue.put(enumMessage.toString(), enumMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eunmKeyAndValue;
	}

	/**
	 * 
	 * <p>
	 * 获取目标类的属性的名称和值信息放入Map中,以属性名称做为key,属性值做为value
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param targetClasss
	 * @return
	 * @author daiqi 创建时间 2018年3月21日 下午7:55:52
	 */
	public static Map<String, Object> getFieldNameAndValues(Class<?> targetClasss) {
		Field[] fields = targetClasss.getFields();
		Map<String, Object> fieldNameValues = EcMapUtils.newHashMap();
		try {
			for (Field field : fields) {
				Object fieldValue = field.get(field.getName());
				if (isNull(fieldValue)) {
					continue;
				}
				fieldNameValues.put(field.getName(), fieldValue);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return fieldNameValues;
	}
}
