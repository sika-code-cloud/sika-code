package com.easy.cloud.core.common.generator.code.java.constant;

import java.lang.reflect.Modifier;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 代码生成组件java常量类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月28日 下午2:39:39
 */
public class EcCodeGenerateJavaConstant {

	/** 注释结尾 */
	public static class EcClassCommentEndWith {
		public static final String POJO_DO = "持久化类";
		public static final String POJO_PO = "持久化类";
		public static final String POJO_DTO = "数据传输类";
		public static final String POJO_BO = "业务逻辑类";
		public static final String POJO_QUERY = "查询类";
		public static final String POJO_VO = "视图类";
		public static final String DAO_INF = "数据处理接口";
		public static final String DAO_IMPL = "数据处理实现类";
		public static final String SERVICE_INF = "服务接口";
		public static final String SERVICE_IMPL = "服务实现类";
		public static final String LOGIC_INF = "业务逻辑接口";
		public static final String LOGIC_IMPL = "业务逻辑实现类";
		public static final String CONTROLLER = "控制转发类";
		public static final String ERROR_CODE = "错误代码枚举";
		public static final String UTIL = "工具类";
		public static final String PROXY = "代理类";
		public static final String TEMPLATE = "模板类";
		public static final String ASPECT = "切面类";
	}

	/** 类名结尾 */
	public static class EcClassNameEndWith {
		public static final String POJO_DO = "Entity";
		public static final String POJO_PO = "PO";
		public static final String POJO_DTO = "DTO";
		public static final String POJO_BO = "BO";
		public static final String POJO_QUERY = "Query";
		public static final String POJO_VO = "VO";
		public static final String DAO_INF = "DAO";
		public static final String DAO_IMPL = "DAOImpl";
		public static final String SERVICE_INF = "Service";
		public static final String SERVICE_IMPL = "ServiceImpl";
		public static final String LOGIC_INF = "Logic";
		public static final String LOGIC_IMPL = "LogicImpl";
		public static final String CONTROLLER = "Controller";
		public static final String ERROR_CODE = "ErrorCodeEnum";
		public static final String UTIL = "Util";
		public static final String PROXY = "Proxy";
		public static final String TEMPLATE = "Template";
		public static final String ASPECT = "Aspect";
	}

	/** 子模块默认的包名 */
	public static class EcSubModuleDefaultPackageName {
		public static final String POJO_DO = "pojo.entity";
		public static final String POJO_PO = "pojo.po";
		public static final String POJO_DTO = "pojo.dto";
		public static final String POJO_BO = "pojo.bo";
		public static final String POJO_QUERY = "pojo.query";
		public static final String POJO_VO = "pojo.vo";
		public static final String DAO_INF = "dao";
		public static final String DAO_IMPL = "dao.impl";
		public static final String SERVICE_INF = "service";
		public static final String SERVICE_IMPL = "service.impl";
		public static final String LOGIC_INF = "logic";
		public static final String LOGIC_IMPL = "logic.impl";
		public static final String CONTROLLER = "controller";
		public static final String ERROR_CODE = "constant.error";
		public static final String UTIL = "util";
		public static final String PROXY = "proxy";
		public static final String TEMPLATE = "template";
		public static final String ASPECT = "aspect";
	}

	/**
	 * 
	 * <p>
	 * 方法类型枚举
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年3月26日 上午11:56:31
	 */
	public static enum EcMethodTypeEnum {
		/** 方法类型枚举---其他方法--- -1 */
		OTHER(-1, "other"),
		/** 方法类型枚举---get---1 */
		GET(1, "get"),
		/** 方法类型枚举---set---2 */
		SET(2, "set"),
		/** 方法类型枚举---build---3 */
		BUILD(3, "build"),
		/** 方法类型枚举---抽象方法---3 */
		ABSTRACT(4, "abstract"),
		;

		private Integer type;
		private String typeDesc;

		private EcMethodTypeEnum(Integer type, String typeDesc) {
			this.type = type;
			this.typeDesc = typeDesc;
		}

		public Integer getType() {
			return type;
		}

		public String getTypeDesc() {
			return typeDesc;
		}

		public static boolean isGet(Integer type) {
			if (GET.getType().equals(type)) {
				return true;
			}
			return false;
		}

		public static boolean isSet(Integer type) {
			if (SET.getType().equals(type)) {
				return true;
			}
			return false;
		}

		public static boolean isBuild(Integer type) {
			if (BUILD.getType().equals(type)) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 
	 * <p>
	 * 忽略生成的属性
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年3月21日 下午7:44:25
	 */
	public static class EcIgnoreField {
		/** 忽略属性---id属性---id */
		public static final String ID = "id";
		/** 忽略属性---创建时间属性---createDate */
		 public static final String CREATE_DATE = "createDate";
		/** 忽略属性---更新时间属性---updateDate */
		public static final String UPDATE_DATE = "updateDate";
		/** 忽略属性---版本属性---version */
		public static final String VERSION = "version";
		/** 忽略属性---创建者属性---createBy */
		public static final String CREATE_BY = "createBy";
		/** 忽略属性---更新者属性---updateBy */
		public static final String UPDATE_BY = "updateBy";
		/** 忽略属性---删除属性---isDeleted */
		public static final String IS_DELETED = "isDeleted";
		/** 忽略属性---备注属性---remark */
		public static final String REMARK = "remark";
		/** 忽略属性---available属性---available */
		public static final String AVAILABLE = "available";

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
		public boolean isIgnoreField(String fieldName) {
			// 移除下划线的小写字符串
			String rmOnderLineLowerCaseStr = EcStringUtils
					.lowerCase(EcStringUtils.replace(fieldName, EcSymbol.UNDER_LINE, EcStringUtils.EMPTY));
			return EcBaseUtils.isExistConstantValue(this.getClass(), rmOnderLineLowerCaseStr);
		}
	}
	
	/**
	 * 修饰符映射美剧类
	 * 
	 * @author daiqi
	 * @date 2018年3月24日 上午1:25:14
	 */
	public static enum EcModifierMappingEnum {
		ABSTRACT(Modifier.ABSTRACT, "abstract"), 
		FINAL(Modifier.FINAL, "final"), 
		INTERFACE(Modifier.INTERFACE,"interface"), 
		NATIVE(Modifier.NATIVE, "native"), 
		PRIVATE(Modifier.PRIVATE, "private"), 
		PROTECTED(Modifier.PROTECTED, "protected"), 
		PUBLIC(Modifier.PUBLIC, "public"), 
		STATIC(Modifier.STATIC,"static"), 
		STRICT(Modifier.STRICT, "strict"), 
		SYNCHRONIZED(Modifier.SYNCHRONIZED,"synchronized"), 
		TRANSIENT(Modifier.TRANSIENT, "transient"), 
		VOLATILE(Modifier.VOLATILE, "volatile"), 
		ENUM(0x00004000, "enum"), CLASS(0x00004020, "class"), 
		ANNOTATION(0x00004040, "@interface");
		
		private int modifier;
		private String modifierDesc;
		
		private EcModifierMappingEnum(int modifier, String modifierDesc) {
			this.modifier = modifier;
			this.modifierDesc = modifierDesc;
		}
		
		public int getModifier() {
			return modifier;
		}
		
		public String getModifierDesc() {
			return modifierDesc;
		}
		
	}
}
