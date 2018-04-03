package com.easy.cloud.core.common.generator.code.base.pojo.bo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.easy.cloud.core.basic.constant.EcBaseConstant.EcCharset;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.file.pojo.bo.EcFileBO;
import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.file.pojo.desc.EcFileDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.base.utils.EcFreeMarkerTemplateUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;

import freemarker.template.Template;

/**
 * 基础生成者
 * 
 * @author daiqi
 * @date 2018年3月24日 上午10:57:46
 */
public abstract class EcGenerateBO {
	/** 文件描述 */
	private EcFileDesc fileDesc;
	/** 模版描述 */
	private EcTemplateDesc templateDesc;

	public EcGenerateBO() {
		super();
	}

	public EcGenerateBO(EcFileDesc fileDesc, EcTemplateDesc templateDesc) {
		this.fileDesc = fileDesc;
		this.templateDesc = templateDesc;
	}

	public EcFileDesc getFileDesc() {
		return fileDesc;
	}

	public abstract EcFileContentBaseDesc getFileContentDesc();

	public EcTemplateDesc getTemplateDesc() {
		return templateDesc;
	}

	protected void setFileDesc(EcFileDesc fileDesc) {
		this.fileDesc = fileDesc;
	}

	protected void setTemplateDesc(EcTemplateDesc templateDesc) {
		this.templateDesc = templateDesc;
	}

	/**
	 * 
	 * <p>
	 * 生成代码
	 * </p>
	 *
	 * <pre></pre>
	 *
	 *
	 * author daiqi 创建时间 2018年3月24日 下午4:56:34
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void generateCode() throws Exception {
		FileOutputStream needGenerateeFileStram = null;
		Writer out = null;
		try {
			// 生成的文件
			File generateFile = new EcFileBO(fileDesc).newFile();
//			如果文件不存在直接返回
			if (EcBaseUtils.isNull(generateFile)) {
				return;
			}
			// 需要生成的文件流
			needGenerateeFileStram = new FileOutputStream(generateFile);
			out = new BufferedWriter(new OutputStreamWriter(needGenerateeFileStram, EcCharset.UTF_8), 10240);
			// 执行生成代码
			// 获取模版数据模型
			Map<String, Object> dataModel = EcJSONUtils.parseObject(this.getFileContentDesc(), HashMap.class);
			EcLogUtils.info("模板数据", dataModel, LoggerFactory.getLogger(this.getClass()));
			// 1:初始化配置数据
			EcFreeMarkerTemplateUtils.initConfigurationData(templateDesc.getBasePackagePath());
			// 2:获取模版
			Template template = EcFreeMarkerTemplateUtils.getTemplate(templateDesc.getName());
			// 3:生成模版
			template.process(dataModel, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (EcBaseUtils.isNotNull(needGenerateeFileStram)) {
				needGenerateeFileStram.close();
			}
			if (EcBaseUtils.isNotNull(out)) {
				out.close();
			}
		}
	}

}
