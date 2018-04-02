package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqMethodTypeEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 方法描述
 * 
 * @author daiqi
 * @date 2018年3月25日 下午11:17:05
 */
@SuppressWarnings("unchecked")
public class DqJavaMethodContentDesc extends DqJavaContentDesc {
	/** 方法类型 */
	private Integer type;
	/** 方法类型描述 */
	private String typeDesc;
	/** 返回类类型简称 */
	private String returnSimpleClassType;
	/** 返回完整类类型 */
	private String returnFullClassType;
	/** 形参列表 */
	private List<DqJavaFieldContentDesc> args;

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

	public List<DqJavaFieldContentDesc> getArgs() {
		return args;
	}

	public void setArgs(List<DqJavaFieldContentDesc> args) {
		this.args = args;
	}

	public void addArg(DqJavaFieldContentDesc arg) {
		List<DqJavaFieldContentDesc> args = new ArrayList<>();
		args.add(arg);
		addArgs(args);
	}

	public void addArgs(List<DqJavaFieldContentDesc> args) {
		this.args = super.getFilterDesc(args, this.args);
	}

	/** 获取形参列表字符串的格式 */
	public String getArgsStr() {
		if (DqCollectionsUtils.isEmpty(args)) {
			return null;
		}
		StringBuilder argsBuild = DqStringUtils.newStringBuilderDefault();
		for (int i = 0; i < args.size(); ++i) {
			DqJavaFieldContentDesc arg = args.get(i);
			if (DqBaseUtils.isNull(arg)) {
				continue;
			}
			if (DqCollectionsUtils.isNotEmpty(arg.getAnnotations())) {
				for (DqJavaAnnotationDesc annotation : arg.getAnnotations()) {
					argsBuild.append(annotation.getName());
					argsBuild.append(DqSymbol.LEFT_PARENTHESES);
					argsBuild.append(annotation.getParamsStr());
					argsBuild.append(DqSymbol.RIGHT_PARENTHESES).append(DqSymbol.EMPTY);
				}
			}
			argsBuild.append(DqStringUtils.capitalize(arg.getSimpleClassType())).append(DqSymbol.EMPTY);
			argsBuild.append(DqStringUtils.uncapitalize(arg.getName()));

			if (i < args.size() - 1) {
				argsBuild.append(DqSymbol.COMMA).append(DqSymbol.EMPTY);
			}
		}
		return argsBuild.toString();
	}

	@Override
	public String getJavaContentSign() {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		sb.append(getTypeDesc()).append(getName());
		if (DqCollectionsUtils.isNotEmpty(args)) {
			for (DqJavaFieldContentDesc arg : getArgs()) {
				sb.append(arg.getFullClassType());
			}

		}
		return sb.toString();
	}
}
