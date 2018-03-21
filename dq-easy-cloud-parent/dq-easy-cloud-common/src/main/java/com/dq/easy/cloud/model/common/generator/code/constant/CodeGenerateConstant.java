package com.dq.easy.cloud.model.common.generator.code.constant;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;

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
public class CodeGenerateConstant {
	
	private static final String tableName = "tl_cyg_bkbgbase_info";
	private static final String packageName = "com.dq.user";
	private static final String tableAnnotation = "质量问题";
	private static final String diskPath = "D://codetemplate/";
	
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
		 * @author daiqi
		 * 创建时间    2018年3月21日 下午8:37:59
		 */
		public static final boolean isIgnoreField(String fieldName) {
			return DqBaseUtils.isExistConstantValue(DqIgnoreField.class, fieldName);
		}
	}
}
