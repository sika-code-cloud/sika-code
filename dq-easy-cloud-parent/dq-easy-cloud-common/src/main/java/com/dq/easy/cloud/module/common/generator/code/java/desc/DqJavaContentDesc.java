package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * java内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:06:55
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DqJavaContentDesc extends DqJavaContentBaseDesc {

	/** 注释 */
	private String comment;
	/** 注解列表 */
	private List<DqJavaAnnotationDesc> annotations;
	/** 修饰符列表 */
	private List<DqJavaModifierDesc> modifiers;

	public DqJavaContentDesc() {
		super();
	}

	public DqJavaContentDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}

	public void addModifier(DqModifierMappingEnum modifierMappingEnum) {
		addModifier(new DqJavaModifierDesc(modifierMappingEnum));
	}
	
	public void addModifier(DqJavaModifierDesc modifier) {
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(modifier);
		addModifiers(modifiers);
	}
	
	public void addModifiers(List<DqJavaModifierDesc> modifiers) {
		this.modifiers = getFilterDesc(modifiers, this.modifiers);
	}
	

	public void addAnnotation(DqJavaAnnotationDesc annotation) {
		List<DqJavaAnnotationDesc> annotations = new ArrayList<>();
		annotations.add(annotation);
		addAnnotations(annotations);
	}

	public void addAnnotations(List<DqJavaAnnotationDesc> annotations) {
		// 增加过滤后的desc---过滤name重复的desc
		this.annotations = getFilterDesc(annotations, this.annotations);
	}

	/** 获取Modifier列表的字符串 */
	public String getModifiersStr() {
		StringBuilder modifiersBuild = DqStringUtils.newStringBuilderDefault();
		if (DqCollectionsUtils.isNotEmpty(getModifiers())) {
			for (int i = 0; i < getModifiers().size(); ++i) {
				modifiersBuild.append(getModifiers().get(i).getDesc());
				if (i < getModifiers().size() - 1) {
					modifiersBuild.append(DqSymbol.EMPTY);
				}
			}
		}
		return modifiersBuild.toString();
	}

	protected List getFilterDesc(List addDescs, List targetDescs) {
		if (targetDescs == null) {
			targetDescs = new ArrayList<>();
		}
		if (DqCollectionsUtils.isEmpty(addDescs)) {
			return targetDescs;
		}
		Map<String, DqJavaContentBaseDesc> filterDescs = DqMapUtils.newLinkedHashMap();
		for (Object targetDesc : targetDescs) {
			DqJavaContentBaseDesc tempTargetDesc = (DqJavaContentBaseDesc) targetDesc;
			filterDescs.put(tempTargetDesc.getJavaContentSign(), tempTargetDesc);
		}
		for (Object addDesc : addDescs) {
			DqJavaContentBaseDesc tempAddDesc = (DqJavaContentBaseDesc) addDesc;
			filterDescs.put(tempAddDesc.getJavaContentSign(), tempAddDesc);
		}
		List<DqJavaContentBaseDesc> retDesc = new ArrayList<>();
		for (DqJavaContentBaseDesc desc : filterDescs.values()) {
			retDesc.add(desc);
		}
		return retDesc;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<DqJavaAnnotationDesc> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<DqJavaAnnotationDesc> annotations) {
		this.annotations = annotations;
	}

	public List<DqJavaModifierDesc> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<DqJavaModifierDesc> modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public String getJavaContentSign() {
		return getFullClassType();
	}

}
