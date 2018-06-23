package com.easy.cloud.core.reptile.dynamicbean.pojo.dto;

import java.util.List;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;

/**
 * 描述：爬虫动态bean配置表数据传输类
 * 
 * @author daiqi
 * @date 2018-06-07 18:09:16
 */
public class EcReptileDynamicBeanDTO extends EcBaseDTO {
	private static final long serialVersionUID = -3702458028118977841L;
	/** 爬虫引擎编号 */
	private Integer reptileEngineNo;
	/** 爬虫动态bean的类型 */
	private Integer beanType;
	/** 模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	private String matchUrls;
	/** 匹配的url类型 */
	private Integer matchUrlType;
	/** 动态bean的编号 */
	private Integer dynamicBeanNo;
	/** 动态bean的父编号 */
	private Integer dynamicBeanParentNo;
	/** 处理数据的管道名称(json数组字符串) */
	private String pipelineNames;
	/** 处理数据的管道名称列表 */
	private List<String> pipelineNameList;
	/** 动态bean所在的包名 */
	private String beanClassPackageName;
	/** bean的类名主体 */
	private String beanClassNameBody;
	/** bean完整类名 */
	private String beanClassFullName;
	/** 匹配的url列表 */
	private List<String> matchUrlList;

	public Integer getReptileEngineNo() {
		return reptileEngineNo;
	}

	public void setReptileEngineNo(Integer reptileEngineNo) {
		this.reptileEngineNo = reptileEngineNo;
	}

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
	public String getMatchUrls() {
		return this.matchUrls;
	}

	/** 设置模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	public void setMatchUrls(String matchUrls) {
		this.matchUrls = matchUrls;
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
	public Integer getDynamicBeanParentNo() {
		return this.dynamicBeanParentNo;
	}

	/** 设置动态bean的父编号 */
	public void setDynamicBeanParentNo(Integer dynamicBeanParentNo) {
		this.dynamicBeanParentNo = dynamicBeanParentNo;
	}

	/** 获取处理数据的管道名称 */
	public String getPipelineNames() {
		return this.pipelineNames;
	}

	/** 设置处理数据的管道名称 */
	public void setPipelineNames(String pipelineNames) {
		this.pipelineNames = pipelineNames;
	}

	public String getBeanClassPackageName() {
		return beanClassPackageName;
	}

	public void setBeanClassPackageName(String beanClassPackageName) {
		this.beanClassPackageName = beanClassPackageName;
	}

	public String getBeanClassNameBody() {
		return beanClassNameBody;
	}

	public void setBeanClassNameBody(String beanClassNameBody) {
		this.beanClassNameBody = beanClassNameBody;
	}

	public List<EcReptileDataFieldDTO> getReptileDataFieldDTOs() {
		return reptileDataFieldDTOs;
	}

	public void setReptileDataFieldDTOs(List<EcReptileDataFieldDTO> reptileDataFieldDTOs) {
		this.reptileDataFieldDTOs = reptileDataFieldDTOs;
	}

	public List<String> getMatchUrlList() {
		if (EcCollectionsUtils.isEmpty(matchUrlList)) {
			matchUrlList = EcJSONUtils.parseArray(matchUrls, String.class);
		}
		return matchUrlList;
	}

	public void setMatchUrlList(List<String> matchUrlList) {
		this.matchUrlList = matchUrlList;
	}

	public List<String> getPipelineNameList() {
		if (EcCollectionsUtils.isEmpty(pipelineNameList)) {
			pipelineNameList = EcJSONUtils.parseArray(pipelineNames, String.class);
		}
		return pipelineNameList;
	}

	public String getBeanClassFullName() {
		if (EcStringUtils.isEmpty(this.beanClassFullName)) {
			this.beanClassFullName = EcStringUtils.generateKey(EcStringConstant.EcSymbol.STOP,beanClassPackageName, beanClassNameBody);
		}
		return this.beanClassFullName;
	}
}
