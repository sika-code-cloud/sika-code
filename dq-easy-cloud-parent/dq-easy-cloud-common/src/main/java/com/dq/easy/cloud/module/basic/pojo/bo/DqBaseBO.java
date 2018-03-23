package com.dq.easy.cloud.module.basic.pojo.bo;

import com.dq.easy.cloud.module.basic.pojo.dto.DqBaseDTO;

/**
 * 业务逻辑对象基础类--所有的业务逻辑对象需要继承此类
 * @author daiqi
 * 创建日期 2018年1月6日 下午3:44:34
 */
public class DqBaseBO extends DqBaseDTO{

	@Override
	public String toString() {
		return "DqBaseBO [getId()=" + getId() + ", getCreateDate()=" + getCreateDate() + ", getUpdateDate()="
				+ getUpdateDate() + ", getVersion()=" + getVersion() + ", getCreateBy()=" + getCreateBy()
				+ ", getUpdateBy()=" + getUpdateBy() + ", getIsDeleted()=" + getIsDeleted() + ", getRemark()="
				+ getRemark() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
}
