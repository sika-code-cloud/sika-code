package com.easy.cloud.core.common.generator.code.java.desc.anno;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaContentBaseDesc;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 类的注解描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class EcJavaAnnotationDesc extends EcJavaContentBaseDesc {
	/** 注解的参数列表 */
	private List<EcJavaAnnotationParamDesc> params;

	public List<EcJavaAnnotationParamDesc> getParams() {
		return params;
	}

	public void setParams(List<EcJavaAnnotationParamDesc> params) {
		this.params = params;
	}

	public void addParam(String name, Object value) {
		if (EcCollectionsUtils.isEmpty(getParams())) {
			this.params = new ArrayList<>();
		}
		getParams().add(new EcJavaAnnotationParamDesc(name, value));
	}
	public void addParam(EcJavaAnnotationParamDesc param) {
		if (EcCollectionsUtils.isEmpty(getParams())) {
			this.params = new ArrayList<>();
		}
		getParams().add(param);
	}

	public void addParams(List<EcJavaAnnotationParamDesc> params) {
		if (EcCollectionsUtils.isEmpty(getParams())) {
			this.params = new ArrayList<>();
		}
		getParams().addAll(params);
	}

	@Override
	public String getName() {
		if (EcStringUtils.isNotEmpty(super.getName())) {
			return EcSymbol.AT + super.getName();
		}
		return super.getName();
	}

	public String getParamsStr() {
		if (EcCollectionsUtils.isNotEmpty(params)) {
			StringBuilder paramsBuild = EcStringUtils.newStringBuilderDefault();
			for (int i = 0; i < params.size(); ++i) {
				EcJavaAnnotationParamDesc param = params.get(i);
				Object value = param.getValue();
				paramsBuild.append(param.getName());
				paramsBuild.append(EcSymbol.EMPTY).append(EcSymbol.EQUAL).append(EcSymbol.EMPTY);
				if (param.getValue() instanceof String) {
					paramsBuild.append(EcSymbol.DOUBLE_QUOTES).append(value).append(EcSymbol.DOUBLE_QUOTES);
				} else {
					paramsBuild.append(value);
				}
				if (i < params.size() - 1) {
					paramsBuild.append(EcSymbol.COMMA);
					paramsBuild.append(EcSymbol.EMPTY);
				}
			}
			return paramsBuild.toString();
		}
		return null;
	}

	@Override
	public String getJavaContentSign() {
		return getFullClassType();
	}
}
