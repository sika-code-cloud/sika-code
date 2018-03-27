package com.dq.easy.cloud.module.common.file.constant;

import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 文件常量类
 * @author daiqi
 * @date 2018年3月23日 下午11:46:21
 */
public class DqFileConstant {
	/** 文件后缀 */
	public static class DqFileSuffix {
		/** 源代码后缀--- .java */
		public static final String JAVA = "java";	
		/** mybaties的mapper文件后缀--- .xml */
		public static final String XML = "xml";
		
		public static boolean isJava(String fileSuffix) {
			return DqStringUtils.equalsIgnoreCase(fileSuffix, JAVA);
		}
		
		public static boolean isXml(String fileSuffix) {
			return DqStringUtils.equalsIgnoreCase(fileSuffix, XML);
		}
	}
}
