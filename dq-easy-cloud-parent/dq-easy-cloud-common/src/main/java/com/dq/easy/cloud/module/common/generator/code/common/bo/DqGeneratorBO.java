package com.dq.easy.cloud.module.common.generator.code.common.bo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqCharset;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.file.pojo.bo.DqFileBO;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.generator.code.common.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.utils.DqFreeMarkerTemplateUtils;
import com.dq.easy.cloud.module.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;
import freemarker.template.Template;

/**
 * 基础生成者
 * 
 * @author daiqi
 * @date 2018年3月24日 上午10:57:46
 */
public class DqGeneratorBO {
	/** 文件描述 */
	private DqFileDesc fileDesc;
	/** 文件内容描述 */
	private DqFileContentBaseDesc fileContentDesc;
	/** 模版描述 */
	private DqTemplateDesc templateDesc;
	
	public DqGeneratorBO(DqFileDesc fileDesc, DqFileContentBaseDesc fileContentDesc, DqTemplateDesc templateDesc) {
		this.fileDesc = fileDesc;
		this.fileContentDesc = fileContentDesc;
		this.templateDesc = templateDesc;
	}

	public DqFileDesc getFileDesc() {
		return fileDesc;
	}

	public DqFileContentBaseDesc getFileContentDesc() {
		return fileContentDesc;
	}

	public DqTemplateDesc getTemplateDesc() {
		return templateDesc;
	}

	/**
	 * 
	 * <p>生成代码</p>
	 *
	 * <pre></pre>
	 *
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 下午4:56:34
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void generateCode() throws Exception {
		FileOutputStream needGenerateeFileStram = null;
		Writer out = null;
		try {
//			生成的文件
			File generateFile = new DqFileBO(fileDesc).newFile();
			generateFile = new File(fileDesc.getFileFullPath());
//			需要生成的文件流
			needGenerateeFileStram = new FileOutputStream(generateFile);
			out = new BufferedWriter(new OutputStreamWriter(needGenerateeFileStram, DqCharset.UTF_8), 10240);
//			执行生成代码
//			获取模版数据模型
			Map<String, Object> dataModel = DqJSONUtils.parseObject(this.getFileContentDesc(), HashMap.class);
			DqLogUtils.info("模板数据", dataModel, LoggerFactory.getLogger(this.getClass()));
//			1:初始化配置数据
			DqFreeMarkerTemplateUtils.initConfigurationData(templateDesc.getBasePackagePath());
//			2:获取模版
			Template template = DqFreeMarkerTemplateUtils.getTemplate(templateDesc.getName());
//			3:生成模版
			template.process(dataModel, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (DqBaseUtils.isNotNull(needGenerateeFileStram)) {
				needGenerateeFileStram.close();
			}
			if (DqBaseUtils.isNotNull(out)) {
				out.close();
			}
		}
	}
	
}
