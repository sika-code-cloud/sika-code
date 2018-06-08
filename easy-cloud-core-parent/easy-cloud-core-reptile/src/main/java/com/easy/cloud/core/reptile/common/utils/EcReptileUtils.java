package com.easy.cloud.core.reptile.common.utils;

import java.util.List;

import com.easy.cloud.core.basic.constant.EcBaseConstant.EcAvailableEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldTypeEnum;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldValueSourceEnum;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldValueSourceTypeEnum;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileDataDTO;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileKeyValueDTO;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicField;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.dynamic.JavassistDynamicBean;
import com.geccocrawler.gecco.request.HttpGetRequest;

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
	 * 将要爬取数据的url放到Scheduler中
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param geccoEngine : GeccoEngine : 爬虫引擎
	 * @param dynamicBeanDTO : EcReptileDynamicBeanDTO : 爬虫动态bean数据传输对象
	 * @param dataDTO : EcReptileDataDTO : 爬虫数据的数据传输对象由业务系统传入
	 * @author daiqi
	 * @创建时间 2018年6月8日 下午3:07:08
	 */
	public static void intoScheduler(GeccoEngine geccoEngine, EcReptileDynamicBeanDTO dynamicBeanDTO, EcReptileDataDTO  dataDTO) {
		List<String> matchUrls = dynamicBeanDTO.getMatchUrls();
		if (EcCollectionsUtils.isEmpty(matchUrls)) {
			throw new EcBaseBusinessException("设置异常", "待设置异常");
		}
		for (String matchUrl : matchUrls) {
			String intoUrl = matchUrl;
			if (EcCollectionsUtils.isEmpty(dataDTO.getUrlKeyValueDTOs())) {
				geccoEngine.getScheduler().into(new HttpGetRequest(intoUrl));
				continue;
			}
			for (EcReptileKeyValueDTO keyValueDTO : dataDTO.getUrlKeyValueDTOs()) {
				intoUrl = intoUrl.replace(keyValueDTO.getKey(), keyValueDTO.getValue());
			}
			intoUrl = intoUrl.replace("{", "").replace("}", "");
			geccoEngine.getScheduler().into(new HttpGetRequest(intoUrl));
		}
	}
	
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
			geccoEngine.beginUpdateRule();
			for (Class<?> spiderBeanClass : spiderBeanClazzs) {
				geccoEngine.register(spiderBeanClass);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			geccoEngine.endUpdateRule();
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
			if (EcBaseUtils.equals(dataFieldDTO.getAvailable(), EcAvailableEnum.YES.type())) {
				DynamicField dynamicField = buildDynamicFieldName(dynamicBean, dataFieldDTO);
				dynamicField = buildDynamicValueSourceType(dynamicField, dynamicBean, dataFieldDTO);
				dynamicField = buildDynamicValueSource(dynamicField, dynamicBean, dataFieldDTO);
				dynamicField.build();
			} else {
				dynamicBean.removeField(dataFieldDTO.getFieldName());
			}
		}
	}
	
	private static DynamicField buildDynamicValueSourceType(DynamicField dynamicField, JavassistDynamicBean dynamicBean,
			EcReptileDataFieldDTO dataFieldDTO) {
		if (EcBaseUtils.equals(dataFieldDTO.getValueSourceType(), EcDataFieldValueSourceTypeEnum.CSS_PATH.type())) {
			dynamicField.csspath(dataFieldDTO.getPath());
		} else {
			dynamicField.ajax(dataFieldDTO.getPath());
		}
		return dynamicField;
	}
	private static DynamicField buildDynamicValueSource(DynamicField dynamicField, JavassistDynamicBean dynamicBean,
			EcReptileDataFieldDTO dataFieldDTO) {
		int valueSourceType = dataFieldDTO.getValueSource();
		if (EcBaseUtils.equals(valueSourceType, EcDataFieldValueSourceEnum.TEXT)) {
			dynamicField.text(dataFieldDTO.isValueTrue());
		} else if (EcBaseUtils.equals(valueSourceType, EcDataFieldValueSourceEnum.HTML)) {
			dynamicField.html(dataFieldDTO.isValueTrue());
		} else if (EcBaseUtils.equals(valueSourceType, EcDataFieldValueSourceEnum.HREF)) {
			dynamicField.href(dataFieldDTO.isValueTrue());
		} else if (EcBaseUtils.equals(valueSourceType, EcDataFieldValueSourceEnum.IMAGE)) {
			if (EcStringUtils.isNotEmpty(dataFieldDTO.getDownloadPath())) {
				dynamicField.image(dataFieldDTO.getDownloadPath(), dataFieldDTO.getFieldValue());
			} else {
				dynamicField.image();
			}
		} else if (EcBaseUtils.equals(valueSourceType, EcDataFieldValueSourceEnum.ATTR)) {
			dynamicField.attr(dataFieldDTO.getFieldValue());
		} else if (EcBaseUtils.equals(valueSourceType, EcDataFieldValueSourceEnum.AJAX)) {
			dynamicField.ajax(dataFieldDTO.getFieldValue());
		}
		return dynamicField;
	}
	/**
	 * 
	 * <p>
	 * 根据动态属性的名称
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
	private static DynamicField buildDynamicFieldName(JavassistDynamicBean dynamicBean,
			EcReptileDataFieldDTO dataFieldDTO) {
		String fieldName = dataFieldDTO.getFieldName();
		String fieldType = dataFieldDTO.getFieldType();
		DynamicField dynamicField = null;
		if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.STRING.type())) {
			dynamicField = dynamicBean.stringField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.INTEGER.type())) {
			dynamicField = dynamicBean.intField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.LONG.type())) {
			dynamicField = dynamicBean.longField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.FLOAT.type())) {
			dynamicField = dynamicBean.floatField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.DOUBLE.type())) {
			dynamicField = dynamicBean.doubleField(fieldName);
		} else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.LIST.type())) {
			dynamicField = dynamicBean.listField(fieldName, Object.class);
		} else {
			dynamicField = dynamicBean.field(fieldName, Object.class);
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
