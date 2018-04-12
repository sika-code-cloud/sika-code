package com.easy.cloud.core.common.generator.code.java.desc;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcMethodTypeEnum;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 方法描述
 * 
 * @author daiqi
 * @date 2018年3月25日 下午11:17:05
 */
@SuppressWarnings("unchecked")
public class EcJavaMethodContentDesc extends EcJavaContentDesc {
	/** 方法类型 */
	private Integer type;
	/** 方法类型描述 */
	private String typeDesc;
	/** 返回类类型简称 */
	private String returnSimpleClassType;
	/** 返回完整类类型 */
	private String returnFullClassType;
	/** 形参列表 */
	private List<EcJavaFieldContentDesc> args;

	public EcJavaMethodContentDesc() {
		super();
	}

	public EcJavaMethodContentDesc(EcMethodTypeEnum ecMethodTypeEnum) {
		this.type = ecMethodTypeEnum.getType();
		this.typeDesc = ecMethodTypeEnum.getTypeDesc();
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

	public List<EcJavaFieldContentDesc> getArgs() {
		return args;
	}

	public void setArgs(List<EcJavaFieldContentDesc> args) {
		this.args = args;
	}

	public void addArg(EcJavaFieldContentDesc arg) {
		List<EcJavaFieldContentDesc> args = new ArrayList<>();
		args.add(arg);
		addArgs(args);
	}

	public void addArgs(List<EcJavaFieldContentDesc> args) {
		this.args = super.getFilterDesc(args, this.args);
	}

	/** 获取形参列表字符串的格式 */
	public String getArgsStr() {
		if (EcCollectionsUtils.isEmpty(args)) {
			return null;
		}
		StringBuilder argsBuild = EcStringUtils.newStringBuilderDefault();
		for (int i = 0; i < args.size(); ++i) {
			EcJavaFieldContentDesc arg = args.get(i);
			if (EcBaseUtils.isNull(arg)) {
				continue;
			}
			if (EcCollectionsUtils.isNotEmpty(arg.getAnnotations())) {
				for (EcJavaAnnotationDesc annotation : arg.getAnnotations()) {
					argsBuild.append(annotation.getName());
					argsBuild.append(EcSymbol.LEFT_PARENTHESES);
					argsBuild.append(annotation.getParamsStr());
					argsBuild.append(EcSymbol.RIGHT_PARENTHESES).append(EcSymbol.SPACE);
				}
			}
			argsBuild.append(EcStringUtils.capitalize(arg.getSimpleClassType())).append(EcSymbol.SPACE);
			argsBuild.append(EcStringUtils.uncapitalize(arg.getName()));

			if (i < args.size() - 1) {
				argsBuild.append(EcSymbol.COMMA).append(EcSymbol.SPACE);
			}
		}
		return argsBuild.toString();
	}

	@Override
	public String getJavaContentSign() {
		StringBuilder sb = EcStringUtils.newStringBuilderDefault();
		sb.append(getTypeDesc()).append(getName());
		if (EcCollectionsUtils.isNotEmpty(args)) {
			for (EcJavaFieldContentDesc arg : getArgs()) {
				sb.append(arg.getFullClassType());
			}

		}
		return sb.toString();
	}
}
