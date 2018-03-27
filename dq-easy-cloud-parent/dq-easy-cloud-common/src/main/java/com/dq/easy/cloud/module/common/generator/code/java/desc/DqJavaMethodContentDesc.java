package com.dq.easy.cloud.module.common.generator.code.java.desc;

import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqMethodTypeEnum;

/**
 * 方法描述
 * 
 * @author daiqi
 * @date 2018年3月25日 下午11:17:05
 */
public class DqJavaMethodContentDesc extends DqJavaContentDesc {
	/** 方法类型 */
	private Integer type;
	/** 方法类型描述 */
	private String typeDesc;
	/** 返回类类型简称 */
	private String returnSimpleClassType;
	/** 返回完整类类型 */
	private String returnFullClassType;

	public DqJavaMethodContentDesc() {
		super();
	}

	public DqJavaMethodContentDesc(DqMethodTypeEnum dqMethodTypeEnum) {
		this.type = dqMethodTypeEnum.getType();
		this.typeDesc = dqMethodTypeEnum.getTypeDesc();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getReturnSimpleClassType() {
		return returnSimpleClassType;
	}

	public void setReturnSimpleClassType(String returnSimpleClassType) {
		this.returnSimpleClassType = returnSimpleClassType;
	}

	public String getReturnFullClassType() {
		return returnFullClassType;
	}

	public void setReturnFullClassType(String returnFullClassType) {
		this.returnFullClassType = returnFullClassType;
	}
}
