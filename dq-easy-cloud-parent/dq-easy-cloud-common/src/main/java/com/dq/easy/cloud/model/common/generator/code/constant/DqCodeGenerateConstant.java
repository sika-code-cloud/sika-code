package com.dq.easy.cloud.model.common.generator.code.constant;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 *  代码生成常量类
 * </p>
 *
 * <pre>
 *  说明：代码生成组件所需要的常量
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月21日 下午4:46:23
 */
public class DqCodeGenerateConstant {
	
	private static final String tableName = "tl_cyg_bkbgbase_info";
	private static final String packageName = "com.dq.user";
	private static final String tableAnnotation = "质量问题";
	private static final String diskPath = "D://codetemplate/";
	
	
	/**
	 * 
	 * <p>
	 * 代码项目常量类
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午5:28:11
	 */
	public static class DqCodeProject {
		/** 项目根路径---默认路径---\\src\\main\\java\\ */
		public static final String PROJECT_ROOT_PATH_DEFAULT = System.getProperty("user.dir")+"\\src\\main\\java\\" ;
		/** 源代码后缀--- .java */
		public static final String SOURCE_CODE_SUFFIX = ".java";	
		/** mybaties的mapper文件后缀--- .xml */
		public static final String MYBATIES_MAPPER_SUFFIX = ".xml";
	}
	public static class DqClassNameStartWith {
		public static final String DEFAULT = "Dq"; 
	}
	public static class DqClassNameEndWith {
		public static final String POJO_DO_END_WITH = "Entity";
		public static final String POJO_DTO_END_WITH = "DTO";
		public static final String POJO_BO_END_WITH = "BO";
		public static final String POJO_QUERY_END_WITH = "Query";
		public static final String DAO_INF_END_WITH = "DAO";
		public static final String DAO_IMPL_END_WITH = "DAOImpl";
		public static final String SERVICE_INF_END_WITH = "Service";
		public static final String SERVICE_IMPL_END_WITH = "ServiceImpl";
		public static final String LOGIC_INF_END_WITH = "Logic";
		public static final String LOGIC_IMPL_END_WITH = "LogicImpl";
		public static final String CONTROLLER_END_WITH = "Controller";
		public static final String ERROR_CODE_END_WITH = "ErrorCodeEnum";
	}
	/**
	 * 
	 * <p>
	 * 字段名标签常量
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午5:28:11
	 */
	public static class DqColumnLabel {
		/** 数据库表的字段名标签---字段名---COLUMN_NAME */
		public static final String COLUMN_NAME = "COLUMN_NAME";
		/** 数据库表的字段名标签---字段的类型名称---TYPE_NAME */
		public static final String TYPE_NAME = "TYPE_NAME";
		/** 数据库表的字段名标签---字段的备注---COLUMN_NAME */
		public static final String REMARKS = "REMARKS";
	}
	
	/**
	 * 
	 * <p>
	 * 忽略生成的属性
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年3月21日 下午7:44:25
	 */
	public static class DqIgnoreField{
		/** 忽略属性---id属性---id */
		public static final String IGNORE_ID = "id";
		/** 忽略属性---创建时间属性---createDate */
		public static final String IGNORE_CREATE_DATE = "createdate";
		/** 忽略属性---更新时间属性---updateDate */
		public static final String IGNORE_UPDATE_DATE = "updatedate";
		/** 忽略属性---版本属性---version */
		public static final String IGNORE_VERSION = "version";
		/** 忽略属性---创建者属性---createBy */
		public static final String IGNORE_CREATE_BY = "createby";
		/** 忽略属性---更新者属性---updateBy */
		public static final String IGNORE_UPDATE_BY = "updateby";
		/** 忽略属性---删除属性---isDeleted */
		public static final String IGNORE_IS_DELETED = "isdeleted";
		/** 忽略属性---备注属性---remark */
		public static final String IGNORE_REMARK = "remark";
		
		/**
		 * 
		 * <p>
		 * 是否是忽略的属性
		 * </p>
		 *
		 * @param fieldName : String : 属性名称
		 * @return true
		 * @author daiqi 创建时间 2018年3月21日 下午8:37:59
		 */
		public static final boolean isIgnoreField(String fieldName) {
//			移除下划线的小写字符串
			String rmOnderLineLowerCaseStr = DqStringUtils.lowerCase(DqStringUtils.replace(fieldName, DqSymbol.UNDER_LINE, DqStringUtils.EMPTY));
			return DqBaseUtils.isExistConstantValue(DqIgnoreField.class, rmOnderLineLowerCaseStr);
		}
	}
}
