package com.dq.easy.cloud.module.common.generator.code.base.constant;

import com.dq.easy.cloud.module.common.file.constant.DqFileConstant;

/**
 * 
 * <p>
 * 代码生成常量类
 * </p>
 *
 * <pre>
 *  说明：代码生成组件所需要的常量
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月21日 下午4:46:23
 */
public class DqCodeGenerateConstant {

	/**
	 * 
	 * <p>
	 * 代码项目常量类
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年3月22日 下午5:28:11
	 */
	public static class DqCodeProject {
		/** 项目根路径---基础路径---System.getProperty("user.dir") */
		public static final String PROJECT_ROOT_BASE_PATH_DEFAULT = DqFileConstant.USER_DIR;
	}
	
	public static class DqTemplateName {
		/** java文件的ftl */
		public static final String JAVA = "JAVA.ftl";
		/** mybatis.xml文件的ftl */
		public static final String MYBATIS_XML = "MYBATIS.ftl";
	}

	/**
	 * 
	 * <p>
	 * 类名简称
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年3月23日 下午1:55:37
	 */
	public static class DqClassNameSimple {
		/** 类名简称---原始类型---byte */
		public static final String BYTE_ORIGINAL = "byte";
		/** 类名简称---原始类型---short */
		public static final String SHORT_ORIGINAL = "short";
		/** 类名简称---原始类型---int */
		public static final String INT_ORIGINAL = "int";
		/** 类名简称---原始类型---long */
		public static final String LONG_ORIGINAL = "long";
		/** 类名简称---原始类型---float */
		public static final String FLOAT_ORIGINAL = "float";
		/** 类名简称---原始类型---double */
		public static final String DOUBLE_ORIGINAL = "double";
		/** 类名简称---原始类型---boolean */
		public static final String BOOLEAN_ORIGINAL = "boolean";
		/** 类名简称---原始类型---char */
		public static final String CHAR_ORIGINAL = "char";
		/** 类名简称---包装类型---Byte */
		public static final String BYTE_PACK = "Byte";
		/** 类名简称---包装类型---Short */
		public static final String SHORT_PACK = "Short";
		/** 类名简称---包装类型---Integer */
		public static final String INT_PACK = "Integer";
		/** 类名简称---包装类型---Long */
		public static final String LONG_PACK = "Long";
		/** 类名简称---包装类型---Float */
		public static final String FLOAT_PACK = "Float";
		/** 类名简称---包装类型---Double */
		public static final String DOUBLE_PACK = "Double";
		/** 类名简称---包装类型---Boolean */
		public static final String BOOLEAN_PACK = "Boolean";
		/** 类名简称---包装类型---Character */
		public static final String CHAR_PACK = "Character";

		/** 类名简称---引用类型---String */
		public static final String STRING = "String";
		/** 类名简称---引用类型---BigDecimal */
		public static final String BIGDECIMAL = "BigDecimal";
		/** 类名简称---引用类型---Date */
		public static final String DATE = "Date";
		/** 类名简称---引用类型---byte [] */
		public static final String BYTE_ARRAY = "byte []";
	}

	/**
	 * 
	 * <p>
	 * 源代码路径常量类
	 * </p>
	 * 
	 * @author daiqi 创建时间 2018年3月23日 上午9:49:23
	 */
	public static class DqSourceCodeRelativePath {
		/** 源代码路径---java---src\\main\\java */
		public static final String JAVA = "src\\main\\java";
		/** 源代码路径---资源路径---src\\main\\resources */
		public static final String RESOURCES = "src\\main\\resources";
	}

	/**
	 * 
	 * <p>
	 * 字段名标签常量
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年3月22日 下午5:28:11
	 */
	public static class DqColumnLabel {
		/** 数据库表的字段名标签---字段名---COLUMN_NAME */
		public static final String COLUMN_NAME = "COLUMN_NAME";
		/** 数据库表的字段名标签---字段的类型名称---TYPE_NAME */
		public static final String TYPE_NAME = "TYPE_NAME";
		/** 数据库表的字段名标签---字段的备注---COLUMN_NAME */
		public static final String REMARKS = "REMARKS";
		/** 列的key---PRI:主键 --- UNI:唯一索引 */
		public static final String COLUMN_KEY = "COLUMN_KEY";
	}
}
