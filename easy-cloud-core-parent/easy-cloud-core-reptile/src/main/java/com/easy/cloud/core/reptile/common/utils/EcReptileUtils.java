package com.easy.cloud.core.reptile.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldTypeEnum;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicField;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.dynamic.JavassistDynamicBean;

/**
 * 
 * <p>
 * 爬虫工具类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月7日 下午6:44:00
 */
public class EcReptileUtils {
	
	/**
	 * 
	 * <p>
	 * 将spiderBeanClass注册到爬虫引擎
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param geccoEngine
	 * @param dynamicBeanDTO
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午8:37:16
	 */
	public static void registerToReptileEngine(GeccoEngine geccoEngine, List<Class<?>> spiderBeanClazzs) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Class<?> spiderBeanClass : spiderBeanClazzs) {
			geccoEngine.register(spiderBeanClass);
		}
	}
	
	/**
	 * 
	 * <p>
	 * 构建爬虫Beanclass
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param reptileDynamicBeanDAO
	 * @param reptileDataFieldDTO
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午6:45:26
	 */
	public static Class<?> buildSpiderBeanClass(EcReptileDynamicBeanDTO dynamicBeanDTO) {
		// 构建JavassistDynamicBean
		JavassistDynamicBean dynamicBean = buildJavassistDynamicBean(dynamicBeanDTO);
		// 构建规则属性列表
		buildDynamicField(dynamicBean, dynamicBeanDTO);
		// 加载class
		return dynamicBean.loadClass();
	}

	/**
	 * 
	 * <p>
	 * 构建动态bean的动态数据属性
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param dynamicBean
	 * @param dynamicBeanDTO
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午8:13:04
	 */
	private static void buildDynamicField(JavassistDynamicBean dynamicBean, EcReptileDynamicBeanDTO dynamicBeanDTO) {
		for (EcReptileDataFieldDTO dataFieldDTO : dynamicBeanDTO.getReptileDataFieldDTOs()) {
			DynamicField dynamicField = getDynamicFieldDTO(dynamicBean, dataFieldDTO);
			dynamicField.csspath(dataFieldDTO.getPath());
			dynamicField.text(dataFieldDTO.isValueTrue());
			dynamicField.build();
		}
	}

	/**
	 * 
	 * <p>
	 * 根据数据传输对象获取动态属性对象
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dataFieldDTO.fieldType : int : 属性类型 ：是
	 *     dataFieldDTO.fieldName : userName : 属性名称 ：是
	 * </pre>
	 *
	 * @param dynamicBean
	 *            : 动态bean
	 * @param dataFieldDTO
	 *            : 数据属性数据传输对象
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午8:14:25
	 */
	private static DynamicField getDynamicFieldDTO(JavassistDynamicBean dynamicBean,
			EcReptileDataFieldDTO dataFieldDTO) {
		String fieldName = dataFieldDTO.getFieldName();
		String fieldType = dataFieldDTO.getFieldType();
		DynamicField dynamicField = null;
		if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.STRING.getType())) {
			dynamicField = dynamicBean.stringField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.INTEGER.getType())) {
			dynamicField = dynamicBean.intField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.LONG.getType())) {
			dynamicField = dynamicBean.longField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.FLOAT.getType())) {
			dynamicField = dynamicBean.floatField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.DOUBLE.getType())) {
			dynamicField = dynamicBean.doubleField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.LIST.getType())) {
			dynamicField = dynamicBean.listField(fieldName, JSONObject.class);
		} else {
			dynamicField = dynamicBean.field(fieldName, JSONObject.class);
		}
		return dynamicField;
	}

	/**
	 * 
	 * <p>
	 * 构建动态JavassistDynamicBean对象
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param dynamicBeanDTO
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午7:14:00
	 */
	private static JavassistDynamicBean buildJavassistDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO) {
		if (EcStringUtils.isEmpty(dynamicBeanDTO.getMatchUrl())
				|| EcCollectionsUtils.isEmpty(dynamicBeanDTO.getMatchUrls())) {
			// TODO 抛出异常
			throw new EcBaseBusinessException("", "");
		}
		if (EcStringUtils.isEmpty(dynamicBeanDTO.getPipelineName())) {
			// TODO 抛出异常
			throw new EcBaseBusinessException("", "");
		}
		JavassistDynamicBean dynamicBean = null;
		if (EcStringUtils.isNotEmpty(dynamicBeanDTO.getBeanNameBody())
				&& EcStringUtils.isNotEmpty(dynamicBeanDTO.getBeanNameFull())) {
			dynamicBean = DynamicGecco.html(dynamicBeanDTO.getBeanNameFull());
		} else {
			dynamicBean = DynamicGecco.html();
		}
		List<String> matchUrls = dynamicBeanDTO.getMatchUrls();
		// 获取matchUrl
		String[] matchUrlArrays = new String[matchUrls.size()];
		dynamicBean.gecco(matchUrls.toArray(matchUrlArrays), dynamicBeanDTO.getPipelineName());
		return dynamicBean;
	}
}
