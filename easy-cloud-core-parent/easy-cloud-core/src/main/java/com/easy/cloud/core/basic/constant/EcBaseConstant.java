package com.easy.cloud.core.basic.constant;

import com.easy.cloud.core.basic.utils.EcBaseUtils;

/**
 * 
 * <p>
 * 	基础常量类
 * </p>
 *
 * <pre>
 *  说明：所有常量都应该继承此类
 *  约定：各个模块的各状态等常量类应该做为静态累不内放在统一的模块常量类中并且最终应该继承DqBaseConstant
 *  命名规范：各个模块常量类以constant为后缀
 *  使用示例：用户模块：用户状态  
 *  public class UserConstant{
 *  	public static class UserStatus{
 *  		public static final int effective = 1; // 有效
 *  		public static final int invalid = 1; // 无效
 *  	} 
 *  }
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月6日 下午1:46:21
 */
public class EcBaseConstant {
	
	/**
	 * 
	 * <p>
	 * 校验当前值在clazz类中是否是可用的值 是返回true、否则返回false
	 * </p>
	 *
	 * @param clazz : Class : 校验的类
	 * @param value : Object : 校验的值
	 * @return boolean
	 * @author daiqi 创建时间 2018年2月6日 下午1:41:47
	 */
	public static <T extends EcBaseConstant> boolean isAvailableValue(Class<T> clazz, Object value) {
		if (EcBaseUtils.isNull(clazz) || EcBaseUtils.isNull(value)) {
			return false;
		}
		return EcBaseUtils.isExistConstantValue(clazz, value);
	}
	
	/**
	 * 
	 * <p>
	 * 校验当前值在clazz类中是否是不可用的值 是不可用返回true、否则返回false
	 * </p>
	 *
	 * @param clazz : Class : 校验的类
	 * @param value : Object : 校验的值
	 * @return boolean
	 * @author daiqi 创建时间 2018年2月6日 下午1:41:47
	 */
	public static <T extends EcBaseConstant> boolean isNotAvailableValue(Class<T> clazz, Object value) {
		return !isAvailableValue(clazz, value);
	}
	
	/**
	 * 
	 * <p>
	 * 字符集类型常量类
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年2月23日 下午2:08:00
	 */
	public static class EcCharset {
		public static final String UTF_8 = "UTF-8";
		public static final String GBK = "GBK";
		public static final String ISO_8859_1 = "ISO-8859-1";
		public static final String ISO8859_1 = "iso8859-1";
		public static final String UNICODE = "UNICODE";
		public static final String ASCII = "ASCII";
	}
	
	/** 图片格式常量类 */
	public static class EcImageFormat {
		public static final String BMP = "BMP";
		public static final String GIF = "GIF";
		public static final String EPS = "EPS";
		public static final String DCS = "DCS";
		public static final String JPEG = "JPEG";
		public static final String JPG = "JPG";
		public static final String JPE = "JPE";
		public static final String PCX = "PCX";
		public static final String PDF = "PDF";
		public static final String PNG = "PNG";
		public static final String TIFF = "TIFF";
		public static final String PXR = "PXR";
	}
}
