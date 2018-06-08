package com.easy.cloud.core.reptile.common.pojo.dto;

import java.util.List;

/**
 * 
 * <p>
 * 需要爬的数据的数据传输类
 * </p>
 *
 * <pre>
 *  说明：封装业务系统的爬取的url及其匹配的url的参数配置
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年6月8日 下午2:40:43
 */
public class EcReptileDataDTO {
	private Integer dynamicBeanNo;
	private List<EcReptileKeyValueDTO> urlKeyValueDTOs;

	public Integer getDynamicBeanNo() {
		return dynamicBeanNo;
	}

	public void setDynamicBeanNo(Integer dynamicBeanNo) {
		this.dynamicBeanNo = dynamicBeanNo;
	}

	public List<EcReptileKeyValueDTO> getUrlKeyValueDTOs() {
		return urlKeyValueDTOs;
	}

	public void setUrlKeyValueDTOs(List<EcReptileKeyValueDTO> urlKeyValueDTOs) {
		this.urlKeyValueDTOs = urlKeyValueDTOs;
	}
}
