package com.dq.easy.cloud.module.common.generator.code.pojo.bo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqCharset;
import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeEnum;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.DqCodeGenerateBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.utils.DqFreeMarkerTemplateUtils;
import com.dq.easy.cloud.module.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

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
	/** 模板的数据model */
	private Map<String, Object> dataModel;
	/** true关闭根据属性导入class---默认false */
	private boolean closeImportFieldClazzs;

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
			throw DqBaseBusinessException.newInstance(DqBaseErrorCodeEnum.DTO_OBJ_CANT_NULL);
		}
		// 初始化FreeMarkerTemplate的CONFIGURATION数据
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath())) {
			this.dqCodeGenerateBaseDTO.initCodeTemplateBasePackagePathDefault();
		}

		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getAuthor())) {
			this.dqCodeGenerateBaseDTO.initAuthorDefault();
		}
		
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath())) {
			this.dqCodeGenerateBaseDTO.initCodeTemplateBasePackagePathDefault();
		}

		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getPackageNameFull())) {
			this.dqCodeGenerateBaseDTO.initPackageNameFullDefault();
		}
		
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getClassNameBody())) {
			this.dqCodeGenerateBaseDTO.initClassNameBody();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getClassNameFull())) {
			this.dqCodeGenerateBaseDTO.initClassNameFullDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getProjectRootPath())) {
			this.dqCodeGenerateBaseDTO.initProjectRootPathDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getSourceFilePathFull())) {
			this.dqCodeGenerateBaseDTO.initSourceFilePathFullDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getPackgePathFull())) {
			this.dqCodeGenerateBaseDTO.initPackgePathFullDefault();
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getFilePathFull())) {
			this.dqCodeGenerateBaseDTO.initFilePathFullDefault();
		}
		if (DqCollectionsUtils.isEmpty(dqCodeGenerateBaseDTO.getImportClazzs())) {
			this.dqCodeGenerateBaseDTO.initImportClazzsDefault();
		}
		// 初始化模板数据
		DqFreeMarkerTemplateUtils.initConfigurationData(dqCodeGenerateBaseDTO);
		// 初始化子类数据
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
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.AUTHOR_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.CODE_TEMPLATE_BASE_PACKAGE_PATH_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getPackgePathFull())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.AUTHOR_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(dqCodeGenerateBaseDTO.getFilePathFull())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.AUTHOR_CANT_EMPTY);
		}

		// 校验子类数据
		verifyCodeGenerateSubClassData();
	}

	/** 校验代码生成子类数据--子类重写此方法进行自己的数据校验 */
	protected abstract void verifyCodeGenerateSubClassData();

	/** 初始化代码生成子类数据--子类重写此方法进行自己的数据初始化 */
	protected abstract void initCodeGenerateSubClassData();

	@SuppressWarnings("unchecked")
	public void generateFileByTemplate() throws Exception {
		Template template = DqFreeMarkerTemplateUtils.getTemplate(dqCodeGenerateBaseDTO.getTemplateName());
		File packageFile = new File(dqCodeGenerateBaseDTO.getPackgePathFull());
		if (!packageFile.exists()) {
			packageFile.mkdirs();
		}
		File needGenerateeFile = new File(dqCodeGenerateBaseDTO.getFilePathFull());
		FileOutputStream needGenerateeFileStram = new FileOutputStream(needGenerateeFile);
		Writer out = new BufferedWriter(new OutputStreamWriter(needGenerateeFileStram, DqCharset.UTF_8), 10240);
		if (DqCollectionsUtils.isEmpty(dqCodeGenerateBaseDTO.getFieldDTOs())) {
			dqCodeGenerateBaseDTO.setFieldDTOs(buildFieldDTOs());
		}
		
		if (!closeImportFieldClazzs) {
			for (Object fieldBaseDTO : dqCodeGenerateBaseDTO.getFieldDTOs()) {
				if (fieldBaseDTO instanceof DqJavaContentDesc) {
					DqJavaContentDesc fieldBase = (DqJavaContentDesc) fieldBaseDTO;
					if (DqStringUtils.isNotEmpty(DqCodeGenerateUtils.getCompleteClassName(fieldBase.getSimpleClassType()))) {
						dqCodeGenerateBaseDTO.getImportClazzs().add(DqCodeGenerateUtils.getCompleteClassName(fieldBase.getSimpleClassType()));
					}
				}
			}
		}
		dataModel = DqJSONUtils.parseObject(this.getDqCodeGenerateBaseDTO(), HashMap.class);
		DqLogUtils.info("模板数据", dataModel, LoggerFactory.getLogger(this.getDqCodeGenerateBaseDTO().getClass()));
		template.process(dataModel, out);
	}

	protected abstract List<?> buildFieldDTOs();

	public boolean isCloseImportFieldClazzs() {
		return closeImportFieldClazzs;
	}

	public void setCloseImportFieldClazzs(boolean closeImportFieldClazzs) {
		this.closeImportFieldClazzs = closeImportFieldClazzs;
	}
}
