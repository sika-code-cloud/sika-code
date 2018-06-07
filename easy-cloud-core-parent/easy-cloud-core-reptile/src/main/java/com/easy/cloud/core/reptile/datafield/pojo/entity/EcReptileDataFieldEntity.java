package com.easy.cloud.core.reptile.datafield.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：爬虫数据属性配置表持久化类
 * 
 * @author THINK
 * @date 2018-06-07 18:09:33
 */
public class EcReptileDataFieldEntity extends EcBaseEntity {
	/** 爬虫动态beanid */
	private Long reptileDynamicBeanId;
	/** 属性对应的路径若valueSource为ajax则为请求的url，否则为cssPath */
	private String path;
	/** 值的来源 1:text 2:html 3:href 4:image 5:attr 6:ajax 7: ... */
	private Integer valueSource;
	/** 值来源类型 1:cssPath 2:jsonPath */
	private Integer valueSourceType;
	/** 若该属性为图片可以设置下载路径将会自动将图片下载到指定路径中 */
	private String downloadPath;
	/** 对应html元素的attr的值等等 */
	private String fieldValue;
	/** 对应相关boolean类型的标志 */
	private Integer valueFlag;
	/** 生成bean的属性名称 */
	private String fieldName;
	/** 生成bean的属性类型 支持string、int、long、float、double、list、ref类型 */
	private String fieldType;
		
	/** 获取爬虫动态beanid */
	public Long getReptileDynamicBeanId() {
		return this.reptileDynamicBeanId;
	}

	/** 设置爬虫动态beanid */
	public void setReptileDynamicBeanId(Long reptileDynamicBeanId) {
		this.reptileDynamicBeanId = reptileDynamicBeanId;
	}

	/** 获取属性对应的路径若valueSource为ajax则为请求的url，否则为cssPath */
	public String getPath() {
		return this.path;
	}

	/** 设置属性对应的路径若valueSource为ajax则为请求的url，否则为cssPath */
	public void setPath(String path) {
		this.path = path;
	}

	/** 获取值的来源 1:text 2:html 3:href 4:image 5:attr 6:ajax 7: ... */
	public Integer getValueSource() {
		return this.valueSource;
	}

	/** 设置值的来源 1:text 2:html 3:href 4:image 5:attr 6:ajax 7: ... */
	public void setValueSource(Integer valueSource) {
		this.valueSource = valueSource;
	}

	/** 获取值来源类型 1:cssPath 2:jsonPath */
	public Integer getValueSourceType() {
		return this.valueSourceType;
	}

	/** 设置值来源类型 1:cssPath 2:jsonPath */
	public void setValueSourceType(Integer valueSourceType) {
		this.valueSourceType = valueSourceType;
	}

	/** 获取若该属性为图片可以设置下载路径将会自动将图片下载到指定路径中 */
	public String getDownloadPath() {
		return this.downloadPath;
	}

	/** 设置若该属性为图片可以设置下载路径将会自动将图片下载到指定路径中 */
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	/** 获取对应html元素的attr的值等等 */
	public String getFieldValue() {
		return this.fieldValue;
	}

	/** 设置对应html元素的attr的值等等 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	/** 获取对应相关boolean类型的标志 */
	public Integer getValueFlag() {
		return this.valueFlag;
	}

	/** 设置对应相关boolean类型的标志 */
	public void setValueFlag(Integer valueFlag) {
		this.valueFlag = valueFlag;
	}

	/** 获取生成bean的属性名称 */
	public String getFieldName() {
		return this.fieldName;
	}

	/** 设置生成bean的属性名称 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/** 获取生成bean的属性类型 支持string、int、long、float、double、list、ref类型 */
	public String getFieldType() {
		return this.fieldType;
	}

	/** 设置生成bean的属性类型 支持string、int、long、float、double、list、ref类型 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

}
