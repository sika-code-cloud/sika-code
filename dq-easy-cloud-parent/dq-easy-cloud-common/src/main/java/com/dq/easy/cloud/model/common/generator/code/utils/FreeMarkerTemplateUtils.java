package com.dq.easy.cloud.model.common.generator.code.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

/**
 * Created by Ay on 2016/7/27.
 */
public class FreeMarkerTemplateUtils {

	private FreeMarkerTemplateUtils() {
	}

	private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

	static {
		// 这里比较重要，用来指定加载模板所在的路径
		CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/codetemplates"));
		CONFIGURATION.setDefaultEncoding("UTF-8");
		CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
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
