package com.easy.cloud.core.reptile.dynamicbean.pojo.dto;

import java.util.List;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;

/**
 * 描述：爬虫动态bean配置表数据传输类
 * 
 * @author daiqi
 * @date 2018-06-07 18:09:16
 */
public class EcReptileDynamicBeanDTO extends EcBaseDTO {
	private static final long serialVersionUID = -3702458028118977841L;
	/** 爬虫动态bean的类型 */
	private Integer beanType;
	/** 模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	private String matchUrl;
	/** 匹配的url类型 */
	private Integer matchUrlType;
	/** 动态bean的编号 */
	private Integer dynamicBeanNo;
	/** 动态bean的父编号 */
	private String dynamicBeanParentNo;
	/** 处理数据的管道名称 */
	private String pipelineName;
	/** 动态bean名称主体 */
	private String beanNameBody;
	/** beanName后缀 */
	private String beanNameSuffix;
	/** 匹配的url列表 */
	private List<String> matchUrls;
	/** 获取爬虫动态bean的类型 */
	public Integer getBeanType() {
		return this.beanType;
	}

	/** 设置爬虫动态bean的类型 */
	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

	/** 爬虫数据属性数据传输对象列表 */
	private List<EcReptileDataFieldDTO> reptileDataFieldDTOs;

	/** 获取模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	public String getMatchUrl() {
		return this.matchUrl;
	}

	/** 设置模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	/** 获取匹配的url类型 */
	public Integer getMatchUrlType() {
		return this.matchUrlType;
	}

	/** 设置匹配的url类型 */
	public void setMatchUrlType(Integer matchUrlType) {
		this.matchUrlType = matchUrlType;
	}

	/** 获取动态bean的编号 */
	public Integer getDynamicBeanNo() {
		return this.dynamicBeanNo;
	}

	/** 设置动态bean的编号 */
	public void setDynamicBeanNo(Integer dynamicBeanNo) {
		this.dynamicBeanNo = dynamicBeanNo;
	}

	/** 获取动态bean的父编号 */
	public String getDynamicBeanParentNo() {
		return this.dynamicBeanParentNo;
	}

	/** 设置动态bean的父编号 */
	public void setDynamicBeanParentNo(String dynamicBeanParentNo) {
		this.dynamicBeanParentNo = dynamicBeanParentNo;
	}

	/** 获取处理数据的管道名称 */
	public String getPipelineName() {
		return this.pipelineName;
	}

	/** 设置处理数据的管道名称 */
	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	/** 获取动态bean名称主体 */
	public String getBeanNameBody() {
		return this.beanNameBody;
	}

	/** 设置动态bean名称主体 */
	public void setBeanNameBody(String beanNameBody) {
		this.beanNameBody = beanNameBody;
	}

	/** 获取beanName后缀 */
	public String getBeanNameSuffix() {
		return this.beanNameSuffix;
	}

	/** 设置beanName后缀 */
	public void setBeanNameSuffix(String beanNameSuffix) {
		this.beanNameSuffix = beanNameSuffix;
	}

	public List<EcReptileDataFieldDTO> getReptileDataFieldDTOs() {
		return reptileDataFieldDTOs;
	}

	public void setReptileDataFieldDTOs(List<EcReptileDataFieldDTO> reptileDataFieldDTOs) {
		this.reptileDataFieldDTOs = reptileDataFieldDTOs;
	}

	public void setMatchUrls(List<String> matchUrls) {
		this.matchUrls = matchUrls;
	}

	public List<String> getMatchUrls() {
		if (EcCollectionsUtils.isEmpty(matchUrls)) {
			matchUrls = EcJSONUtils.parseArray(matchUrl, String.class);
		}
		return matchUrls;
	}

	public String getBeanNameFull() {
		return beanNameBody + beanNameSuffix;
	}
}
