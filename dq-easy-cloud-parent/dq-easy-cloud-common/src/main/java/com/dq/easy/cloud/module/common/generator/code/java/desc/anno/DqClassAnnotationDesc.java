package com.dq.easy.cloud.module.common.generator.code.java.desc.anno;

import java.util.List;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 类的注解描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class DqClassAnnotationDesc extends DqJavaContentBaseDesc {
	/** 注解的参数列表 */
	private List<DqClassAnnotationParamDesc> params;

	public List<DqClassAnnotationParamDesc> getParams() {
		return params;
	}

	public void setParams(List<DqClassAnnotationParamDesc> params) {
		this.params = params;
	}

	public String getParamsStr() {
		if (DqCollectionsUtils.isNotEmpty(params)) {
			StringBuilder paramsBuild = DqStringUtils.newStringBuilderDefault();
			for (int i = 0 ; i < params.size(); ++i) {
				DqClassAnnotationParamDesc param = params.get(i);
				paramsBuild.append(param.getName());
				paramsBuild.append(DqSymbol.EMPTY).append(DqSymbol.EQUAL).append(DqSymbol.EMPTY);
				if (param.getValue() instanceof String) {
					paramsBuild.append(DqSymbol.DOUBLE_QUOTES).append(param.getValue()).append(DqSymbol.DOUBLE_QUOTES);
				} else {
					paramsBuild.append(param.getValue());
				}
				if (i < params.size() - 1) {
					paramsBuild.append(DqSymbol.COMMA);
					paramsBuild.append(DqSymbol.EMPTY);
				}
			}
			return paramsBuild.toString();
		}
		return null;
	}
}
