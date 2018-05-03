package com.easy.cloud.core.generator.code.xml.constant;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 代码生成器xml常量类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月28日 下午2:49:17
 */
public class EcCodeGenerateXmlConstant {
	/** xml申明类 */
	public static class EcStatement {
		public static final String DEFAULT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	}

	/** xml文档类型常量类 */
	public static class EcDocType {
		public static final String MYBATIS = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";
	}

	public static class EcTableColumnKey {
		public static final String PRI = "PRI";
		public static final String UNI = "uni";

		/** colunmKey是主键类型 */
		public static boolean isPrimary(String columnKey) {
			if (EcStringUtils.equalsIgnoreCase(columnKey, PRI)) {
				return true;
			}
			return false;
		}

		/** colunmKey是唯一索引类型 */
		public static boolean isUnique(String columnKey) {
			if (EcStringUtils.equalsIgnoreCase(columnKey, UNI)) {
				return true;
			}
			return false;
		}
	}

	/** mybatis属性key */
	public static class EcMyBatisAttrKey {
		public static final String NAMESPACE = "namespace";
		public static final String ID = "id";
		public static final String TYPE = "type";
		public static final String KEY_PROPERTY = "keyProperty";
		public static final String USE_GENERATED_KEYS = "useGeneratedKeys";
		public static final String COLUMN = "column";
		public static final String JDBC_TYPE = "jdbcType";
		public static final String PROPERTY = "property";
		public static final String RESULT_MAP = "resultMap";
		public static final String RESULT_TYPE = "resultType";
		public static final String PARAMETER_TYPE = "parameterType";
		public static final String REFID = "refid";
		public static final String TEST = "test";
		public static final String RESOURCE = "resource";
		public static final String MAPPER = "mapper";
	}

	/** mybatis sql节点类型 */
	public static enum EcMyBatisSqlTypeEnum {
		/** 查询类型---resultMap  */
		RESULT_MAP(0, "resultMap"),
		/** 查询类型---列名列表类型 */
		COLUMN_LIST(1, "columnList"),
		/** 查询类型---根据id获取数据 */
		FIND_BY_ID(2, "findById"),
		/** 查询类型---获取列表数量 */
		LIST_COUNT(3, "listCount"),
		/** 查询类型---获取分页数据 */
		LIST_PAGE(4, "listPage"),
		/** 查询类型---设置列明的sql */
		SET_COLUMN_SQL(5, "setColumnSql"),
		/** 查询类型---保存数据 */
		SAVE(6, "save"),
		/** 查询类型---更新数据  */
		UPDATE(7, "update"),
		;
		private Integer type;
		private String desc;

		private EcMyBatisSqlTypeEnum(Integer type, String desc) {
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
	public static enum EcMyBatisElementNameEnum {
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
		/** id */
		ID(8, "id"), 
		/** result */
		RESULT(9, "result"),
		/** include */
		INCLUDE(10, "include"), 
		/** set */
		SET(11, "set"), 
		/** if */
		IF(12, "if"), 
		/** mappers */
		MAPPERS(13, "mappers"), 
		;
		private Integer type;
		private String desc;

		private EcMyBatisElementNameEnum(Integer type, String desc) {
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
	/**
	 * 
	 * <p>
	 * 忽略设置的列
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年3月30日 上午11:58:59
	 */
	public static class EcIgnoreSetField {
		public static final String ID = "id";
		/**
		 * 
		 * <p>
		 * 是否是忽略的属性
		 * </p>
		 *
		 * @param fieldName
		 *            : String : 属性名称
		 * @return true
		 * @author daiqi 创建时间 2018年3月21日 下午8:37:59
		 */
		public static final boolean isIgnoreField(String fieldName) {
			// 移除下划线的小写字符串
			String rmOnderLineLowerCaseStr = EcStringUtils
					.lowerCase(EcStringUtils.replace(fieldName, EcSymbol.UNDER_LINE, EcSymbol.EMPTY));
			return EcBaseUtils.isExistConstantValue(EcIgnoreSetField.class, rmOnderLineLowerCaseStr);
		}
	}
}
