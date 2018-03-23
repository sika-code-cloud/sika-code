package com.dq.easy.cloud.module.common.generator.code.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqCharset;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.DqCodeGenerateBaseDTO;

/**
 * Created by Ay on 2016/7/27.
 */
public class DqFreeMarkerTemplateUtils {
	
	/** 初始化CONFIGURATION对象数据的标志 */
	private static boolean INIT_CONFIGURATION_FLAG; 

	private DqFreeMarkerTemplateUtils() {
		
	}

	private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);
	
	/**
	 * 
	 * <p>
	 * 初始化FreeMarkerTemplate的CONFIGURATION数据 ,使用之前必须先调用该方法
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param dqCodeGenerateBaseDTO
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午5:45:14
	 */
	public static void initConfigurationData(DqCodeGenerateBaseDTO dqCodeGenerateBaseDTO) {
		if (INIT_CONFIGURATION_FLAG) {
			return;
		}
		// 这里比较重要，用来指定加载模板所在的路径
		CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(DqFreeMarkerTemplateUtils.class, dqCodeGenerateBaseDTO.getCodeTemplateBasePackagePath()));
		CONFIGURATION.setDefaultEncoding(DqCharset.UTF_8);
		CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
		INIT_CONFIGURATION_FLAG = true;
	}

	public static Template getTemplate(String templateName) throws IOException {
		try {
			return CONFIGURATION.getTemplate(templateName);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void clearCache() {
		CONFIGURATION.clearTemplateCache();
	}
}
