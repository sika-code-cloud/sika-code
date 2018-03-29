package com.dq.easy.cloud.module.common.generator.code.xml.constant;

import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 代码生成器xml常量类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月28日 下午2:49:17
 */
public class DqCodeGenerateXmlConstant {
	/** xml申明类 */
	public static class DqStatement {
		public static final String DEFAULT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	}

	/** xml文档类型常量类 */
	public static class DqDocType {
		public static final String MYBATIS = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";
	}

	public static class DqTableColumnKey {
		public static final String PRI = "PRI";
		public static final String UNI = "uni";

		/** colunmKey是主键类型 */
		public static boolean isPrimary(String columnKey) {
			if (DqStringUtils.equalsIgnoreCase(columnKey, PRI)) {
				return true;
			}
			return false;
		}

		/** colunmKey是唯一索引类型 */
		public static boolean isUnique(String columnKey) {
			if (DqStringUtils.equalsIgnoreCase(columnKey, UNI)) {
				return true;
			}
			return false;
		}
	}

	/** mybatis属性key */
	public static class DqMyBatisAttrKey {
		public static final String NAMESPACE = "namespace";
		public static final String ID = "id";
		public static final String TYPE = "type";
		public static final String KEY_PROPERTY = "keyProperty";
		public static final String USE_GENERATED_KEYS = "useGeneratedKeys";
		public static final String COLUNM = "colunm";
		public static final String JDBC_TYPE = "jdbcType";
		public static final String PROPERTY = "property";
		public static final String RESULT_MAP = "resultMap";
		public static final String PARAMETER_TYPE = "parameterType";
		public static final String REFID = "refid";
		public static final String TEST = "test";
	}

	/** mybatis sql节点类型 */
	public static enum DqMyBatisSqlType {
		/** 查询类型---根据id获取数据 */
		FIND_BY_ID(1, "findById"),
		/** 查询类型---获取列表数量 */
		LIST_COUNT(2, "listCount"),
		/** 查询类型---获取分页数据 */
		LIST_PAGE(3, "listPage"),
		/** 查询类型---列名列表类型 */
		COLUMN_LIST(4, "columnList"),
		/** 查询类型---设置列明的sql */
		SET_COLUMN_SQL(5, "setColumnSql"),;
		private Integer type;
		private String desc;

		private DqMyBatisSqlType(Integer type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	/** mybatis元素名称枚举 */
	public static enum DqMyBatisElementNameEnum {
		/** resultMap */
		RESULT_MAP(1, "resultMap"),
		/** sql */
		SQL(2, "sql"),
		/** select */
		SELECT(3, "select"),
		/** insert */
		INSERT(4, "insert"),
		/** update */
		UPDATE(5, "update"),
		/** delete */
		DELETE(6, "delete"),
		/** mapper */
		MAPPER(7, "mapper"), 
		ID(8, "id"), 
		RESULT(9, "result"),
		INCLUDE(10, "include"), 
		;
		private Integer type;
		private String desc;

		private DqMyBatisElementNameEnum(Integer type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}
}
