package com.dq.easy.cloud.model.common.generator.code.pojo.bo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant.DqCharset;
import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeEnum;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.generator.code.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.DqCodeGenerateBaseDTO;
import com.dq.easy.cloud.model.common.generator.code.utils.DqFreeMarkerTemplateUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessExceptionEnum;

import freemarker.template.Template;

/**
 * 
 * <p>
 * 代码生成基础逻辑对象
 * </p>
 *
 * <pre>
 *  说明：处理代码生成的相关业务逻辑
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月22日 上午11:48:05
 */
public abstract class DqCodeGenerateBaseBO {

	private DqCodeGenerateBaseDTO dqCodeGenerateBaseDTO;
	
	private Map<String, Object> dataModel;

	public DqCodeGenerateBaseBO(DqCodeGenerateBaseDTO dqCodeGenerateBaseDTO) {
		this.dqCodeGenerateBaseDTO = dqCodeGenerateBaseDTO;
	}
	

	public final DqCodeGenerateBaseDTO getDqCodeGenerateBaseDTO() {
		return dqCodeGenerateBaseDTO;
	}


	/**
	 * 
	 * <p>
	 * 初始化代码生成数据
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月22日 下午3:48:27
	 */
	public final void initCodeGenerateData() {
		if (DqBaseUtils.isNull(dqCodeGenerateBaseDTO)) {
			throw DqBaseBusinessExceptionEnum.newInstance(DqBaseErrorCodeEnum.DTO_OBJ_CANT_NULL);
		}
//		初始化FreeMarkerTemplate的CONFIGURATION数据 
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.CODE_TEMPLATE_BASE_PACKAGE_PATH_CANT_EMPTY);
		}
		
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getAuthor())) {
			this.dqCodeGenerateBaseDTO.initAuthorDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath())) {
			this.dqCodeGenerateBaseDTO.initCodeTemplateBasePackagePathDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getPackgePathFull())) {
			this.dqCodeGenerateBaseDTO.initPackgePathFullDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getFilePathFull())) {
			this.dqCodeGenerateBaseDTO.initFilePathFullDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getClassNameFull())) {
			this.dqCodeGenerateBaseDTO.initClassNameFullDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getPackageNameFull())) {
			this.dqCodeGenerateBaseDTO.initClassNameFullDefault();
		}
		DqFreeMarkerTemplateUtils.initConfigurationData(dqCodeGenerateBaseDTO);
//		初始化子类数据
		initCodeGenerateSubClassData();
	}
	
	/**
	 * 
	 * <p>
	 * 校验代码生成数据
	 * </p>
	 *
	 * @return this对象
	 * @author daiqi
	 * @Date 2018年3月22日 下午3:48:43
	 */
	public final void verifyCodeGenerateData() {
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getAuthor())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.AUTHOR_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath())) {
			throw new DqBaseBusinessExceptionEnum(
					DqCodeGenerateErrorCodeEnum.CODE_TEMPLATE_BASE_PACKAGE_PATH_CANT_EMPTY);
		}
//		校验子类数据
		verifyCodeGenerateSubClassData();
	}
	
	/** 校验代码生成子类数据--子类重写此方法进行自己的数据校验 */
	protected abstract void verifyCodeGenerateSubClassData();
	/** 初始化代码生成子类数据--子类重写此方法进行自己的数据初始化 */
	protected abstract void initCodeGenerateSubClassData();
	
	@SuppressWarnings("unchecked")
	public void generateFileByTemplate()
			throws Exception {
		Template template = DqFreeMarkerTemplateUtils.getTemplate(dqCodeGenerateBaseDTO.getTemplateName());
		File packageFile = new File(dqCodeGenerateBaseDTO.getPackgePathFull());
		if (!packageFile.exists()) {
			packageFile.mkdirs();
		}
		
		File needGenerateeFile = new File(dqCodeGenerateBaseDTO.getFilePathFull()); 
		
		FileOutputStream needGenerateeFileStram = new FileOutputStream(needGenerateeFile);
//		dataMap.put("author", dqCodeGenerateBaseDTO.getAuthor());
//		dataMap.put("packageNameFull", dqCodeGenerateBaseDTO.getPackageNameFull());
//		dataMap.put("table_name_small", tableName);
//		dataMap.put("table_name", changeTableName);
//		dataMap.put("author", AUTHOR);
//		dataMap.put("date", CURRENT_DATE);
//		dataMap.put("package_name", packageName);
//		dataMap.put("table_annotation", tableAnnotation);
		Writer out = new BufferedWriter(new OutputStreamWriter(needGenerateeFileStram, DqCharset.UTF_8), 10240);
		dataModel = DqJSONUtils.parseObject(this, HashMap.class);
		template.process(dataModel, out);
	}
	
}
