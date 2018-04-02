package com.easy.cloud.core.common.generator.code.java.desc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * java内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:06:55
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EcJavaContentDesc extends EcJavaContentBaseDesc {

	/** 注释 */
	private String comment;
	/** 注解列表 */
	private List<EcJavaAnnotationDesc> annotations;
	/** 修饰符列表 */
	private List<EcJavaModifierDesc> modifiers;

	public EcJavaContentDesc() {
		super();
	}

	public EcJavaContentDesc(EcGenerateRule generateRule) {
		super(generateRule);
	}

	public void addModifier(EcModifierMappingEnum modifierMappingEnum) {
		addModifier(new EcJavaModifierDesc(modifierMappingEnum));
	}
	
	public void addModifier(EcJavaModifierDesc modifier) {
		List<EcJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(modifier);
		addModifiers(modifiers);
	}
	
	public void addModifiers(List<EcJavaModifierDesc> modifiers) {
		this.modifiers = getFilterDesc(modifiers, this.modifiers);
	}
	

	public void addAnnotation(EcJavaAnnotationDesc annotation) {
		List<EcJavaAnnotationDesc> annotations = new ArrayList<>();
		annotations.add(annotation);
		addAnnotations(annotations);
	}

	public void addAnnotations(List<EcJavaAnnotationDesc> annotations) {
		// 增加过滤后的desc---过滤name重复的desc
		this.annotations = getFilterDesc(annotations, this.annotations);
	}

	/** 获取Modifier列表的字符串 */
	public String getModifiersStr() {
		StringBuilder modifiersBuild = EcStringUtils.newStringBuilderDefault();
		if (EcCollectionsUtils.isNotEmpty(getModifiers())) {
			for (int i = 0; i < getModifiers().size(); ++i) {
				modifiersBuild.append(getModifiers().get(i).getDesc());
				if (i < getModifiers().size() - 1) {
					modifiersBuild.append(EcSymbol.EMPTY);
				}
			}
		}
		return modifiersBuild.toString();
	}

	protected List getFilterDesc(List addDescs, List targetDescs) {
		if (targetDescs == null) {
			targetDescs = new ArrayList<>();
		}
		if (EcCollectionsUtils.isEmpty(addDescs)) {
			return targetDescs;
		}
		Map<String, EcJavaContentBaseDesc> filterDescs = EcMapUtils.newLinkedHashMap();
		for (Object targetDesc : targetDescs) {
			EcJavaContentBaseDesc tempTargetDesc = (EcJavaContentBaseDesc) targetDesc;
			filterDescs.put(tempTargetDesc.getJavaContentSign(), tempTargetDesc);
		}
		for (Object addDesc : addDescs) {
			EcJavaContentBaseDesc tempAddDesc = (EcJavaContentBaseDesc) addDesc;
			filterDescs.put(tempAddDesc.getJavaContentSign(), tempAddDesc);
		}
		List<EcJavaContentBaseDesc> retDesc = new ArrayList<>();
		for (EcJavaContentBaseDesc desc : filterDescs.values()) {
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

	public List<EcJavaAnnotationDesc> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<EcJavaAnnotationDesc> annotations) {
		this.annotations = annotations;
	}

	public List<EcJavaModifierDesc> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<EcJavaModifierDesc> modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public String getJavaContentSign() {
		return getFullClassType();
	}

}
