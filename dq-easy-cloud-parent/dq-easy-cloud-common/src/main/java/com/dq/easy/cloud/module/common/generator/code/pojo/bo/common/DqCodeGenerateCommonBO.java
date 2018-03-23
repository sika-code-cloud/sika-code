package com.dq.easy.cloud.module.common.generator.code.pojo.bo.common;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.pojo.bo.DqCodeGenerateBaseBO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.common.DqCodeGenerateCommonDTO;

/**
 * 
 * <p>
 * 代码生成逻辑对象---普通代码生成逻辑对象
 * </p>
 *
 * <pre>
 *  说明：处理代码生成的相关业务逻辑---不需要根据数据库生成使用此逻辑对象
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月22日 上午11:48:05
 */
public class DqCodeGenerateCommonBO extends DqCodeGenerateBaseBO{

	public DqCodeGenerateCommonBO(DqCodeGenerateCommonDTO dqCodeGenerateCommonDTO) {
		super(dqCodeGenerateCommonDTO);
	}

	@Override
	protected void verifyCodeGenerateSubClassData() {
		
	}

	@Override
	protected void initCodeGenerateSubClassData() {
		
	}

	public DqCodeGenerateCommonDTO getDqCodeGenerateCommonDTO() {
		return (DqCodeGenerateCommonDTO) super.getDqCodeGenerateBaseDTO();
	}

	@Override
	protected List<?> buildFieldDTOs() {
		return new ArrayList<>();
	}
	
}
