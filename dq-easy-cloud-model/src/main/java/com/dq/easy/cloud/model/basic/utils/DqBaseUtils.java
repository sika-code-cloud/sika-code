package com.dq.easy.cloud.model.basic.utils;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;
import com.dq.easy.cloud.model.basic.pojo.entity.DqBaseEntity;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;


/**
 * 
 * @ClassName : DqBaseUtils 
 * @Description : 基础工具类 
 * @author daiqi
 * @date 2017年12月6日 下午1:46:43 
 *
 */
public class DqBaseUtils {
	/**
	 * 
	 * <p>判断obj是否为空，为空返回true</p>
	 *
	 * @param obj
	 * @return true 对象为空
	 * @author daiqi
	 * @date 2017年12月6日 下午1:47:50
	 */
	public static boolean isNull(Object obj){
		if(obj == null){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>判断obj是否不为空</p>
	 *
	 * @param obj
	 * @return
	 * @author daiqi
	 * @date 2017年12月6日 下午1:48:38
	 */
	public static boolean isNotNull(Object obj){
		return !isNull(obj);
	}
	/**
	 * 
	 * <p>判断DqBaseEntity类是否为空  </p>
	 *
	 * @param baseEntity
	 * @return <code>true</code> 若baseEntity为null 或者 baseEntity.id为null
	 * @author daiqi
	 * @date 2017年12月6日 下午1:51:09
	 */
	public static boolean isNullEntity(DqBaseEntity baseEntity){
		if(isNull(baseEntity) || isNull(baseEntity.getId())){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>将fromObj中对象中的非空数据拷贝到targetObj对象中</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @param fromObj : 被拷贝数据的对象
	 * @param targetObj : 拷贝的目标对象
	 * @return 拷贝完成的对象
	 * @author daiqi
	 * @date 2017年12月6日 下午1:54:39
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copeFromObjToTargetObj(T fromObj,T targetObj){
		if(isNull(fromObj) || isNull(targetObj)){
			return targetObj;
		}
//		获取目标对象的class
		Class<T> clazz = (Class<T>) targetObj.getClass();
//		将fromObje转换为map
		Map<String,Object> fromObjMap = DqJSONUtils.parseObject(JSONObject.toJSONString(fromObj),HashMap.class);
//		将目标对象转换为map
		Map<String,Object> targetObjMap = DqJSONUtils.parseObject(JSONObject.toJSONString(targetObj),HashMap.class);
//		循环fromObjMap并将其值put到targetObjMap中
		for(String newsKey : fromObjMap.keySet()){
			if(fromObjMap.get(newsKey) != null){
				targetObjMap.put(newsKey, fromObjMap.get(newsKey));
			}
		}
//		将targetObjMap转换为目标类的对象
		return DqJSONUtils.parseObject(targetObjMap, clazz);
	}
	/**
	 * 
	 * <p>根据实体类的Class获取其对应的tableName</p>
	 *
	 * <pre></pre>
	 *
	 * @param clazz
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月8日 下午7:47:40
	 */
	public static String getTableNameByEntityClass(final Class<?> entityClazz){
		if(isNull(entityClazz)){
			throw new RuntimeException("entityClazz is null");
		}
//		获取javax.persistence.Table注解
		Table tableAnnotation = entityClazz.getAnnotation(Table.class);
		if(isNull(tableAnnotation)){
			throw new RuntimeException("实体类没有javax.persistence.Table注解");
		}
		String tableName = tableAnnotation.name();
		if(DqStringUtils.isEmpty(tableName)){
			throw new RuntimeException("javax.persistence.Table注解name属性为空");
		}
		return tableName;
	}
}
