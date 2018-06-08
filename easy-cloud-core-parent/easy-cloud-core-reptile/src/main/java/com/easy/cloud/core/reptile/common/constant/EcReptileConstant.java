package com.easy.cloud.core.reptile.common.constant;

/**
 * 常量类
 * 
 * @author daiqi
 * @date 2018年6月6日 下午9:42:32
 */
public class EcReptileConstant {
	public static final String MATCH_URL_DETAIL = "http://tl.cyg.changyou.com/goods/char_detail?serial_num={code}";
	public static final String MATCH_URL_INDEX = "http://tl.cyg.changyou.com";

	public static enum EcDataFieldValueSourceEnum {
		TEXT(1, "text来源"), 
		HTML(2, "html来源"), 
		HREF(3, "href来源"), 
		IMAGE(4, "image来源"), 
		ATTR(5, "attr来源"), 
		AJAX(6, "ajax来源"), 
		;

		private int type;
		private String desc;

		private EcDataFieldValueSourceEnum(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public int type() {
			return type;
		}

		public String desc() {
			return desc;
		}
	}
	public static enum EcDataFieldValueSourceTypeEnum {
		CSS_PATH(1, "cssPath类型"), 
		JSON_PATH(2, "jsonPath类型"), 
		;
		
		private int type;
		private String desc;
		
		private EcDataFieldValueSourceTypeEnum(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}
		
		public int type() {
			return type;
		}
		
		public String desc() {
			return desc;
		}
	}

	/** 数据属性类型枚举类 */
	public static enum EcDataFieldTypeEnum {
		STRING("string", "字符串类型"), 
		INTEGER("int", "整型类型"), 
		LONG("long", "长整型类型"), 
		FLOAT("float", "Float类型"), 
		DOUBLE("double", "Double类型"), 
		LIST("list", "列表类型"), 
		REF("ref", "引用类型"),
		;
		private String type;
		private String desc;

		private EcDataFieldTypeEnum(String type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public String type() {
			return type;
		}

		public String desc() {
			return desc;
		}

	}
}
