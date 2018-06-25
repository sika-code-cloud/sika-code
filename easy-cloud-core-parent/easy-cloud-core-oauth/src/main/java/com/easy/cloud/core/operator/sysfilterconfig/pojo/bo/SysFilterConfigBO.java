package com.easy.cloud.core.operator.sysfilterconfig.pojo.bo;

import com.easy.cloud.core.basic.pojo.bo.EcBaseBO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;

/**
 * 描述：业务逻辑类
 * 
 * @author THINK
 * @date 2018-06-25 16:36:55
 */
public class SysFilterConfigBO extends EcBaseBO {
	/** 数据传输类 */
	private SysFilterConfigDTO sysFilterConfigDTO;
		
	 SysFilterConfigBO(SysFilterConfigDTO sysFilterConfigDTO) {
		this.sysFilterConfigDTO = sysFilterConfigDTO;
	}
	
	/** 构建数据传输类 */
	public SysFilterConfigBO buildSysFilterConfigDTO(SysFilterConfigDTO sysFilterConfigDTO) {
		this.sysFilterConfigDTO = sysFilterConfigDTO;
		return this;
	}

}
