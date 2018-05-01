package com.easy.cloud.core.common.file.constant;

import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 文件常量类
 * @author daiqi
 * @date 2018年3月23日 下午11:46:21
 */
public class EcFileConstant {
	
	public static final String USER_DIR = System.getProperty("user.dir");
	/** 文件后缀 */
	public static class EcFileSuffix {
		/** 源代码后缀--- .java */
		public static final String JAVA = "java";	
		/** mybaties的mapper文件后缀--- .xml */
		public static final String XML = "xml";
		
		public static boolean isJava(String fileSuffix) {
			return EcStringUtils.equalsIgnoreCase(fileSuffix, JAVA);
		}
		
		public static boolean isXml(String fileSuffix) {
			return EcStringUtils.equalsIgnoreCase(fileSuffix, XML);
		}
	}
}
