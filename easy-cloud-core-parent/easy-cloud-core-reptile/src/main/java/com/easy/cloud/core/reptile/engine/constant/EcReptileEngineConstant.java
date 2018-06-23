package com.easy.cloud.core.reptile.engine.constant;

/**
 * 
 * <p>
 * 爬虫引擎常量类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月11日 上午11:07:47
 */
public class EcReptileEngineConstant {

	/**
	 * 
	 * <p>
	 * 循环标志枚举类
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年6月11日 上午11:11:56
	 */
	public static enum EcLoopEnum {
		NO(0, false), YES(1, true),;
		private int key;
		private boolean value;

		private EcLoopEnum(int key, boolean value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public boolean getValue() {
			return value;
		}

		public static boolean loop(Integer key) {
			if (YES.key == key) {
				return YES.value;
			} else {
				return NO.value;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * debug标志枚举类
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年6月11日 上午11:11:56
	 */
	public static enum EcDebugEnum {
		NO(0, false), YES(1, true),;
		private int key;
		private boolean value;

		private EcDebugEnum(int key, boolean value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public boolean getValue() {
			return value;
		}

		public static boolean debug(Integer key) {
			if (YES.key == key) {
				return YES.value;
			} else {
				return NO.value;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * debug标志枚举类
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年6月11日 上午11:11:56
	 */
	public static enum EcMobileEnum {
		PC(1, false), MOBILE(2, true),;
		private int key;
		private boolean value;

		private EcMobileEnum(int key, boolean value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public boolean getValue() {
			return value;
		}

		public static boolean mobile(Integer key) {
			if (MOBILE.key == key) {
				return MOBILE.value;
			} else {
				return PC.value;
			}
		}
	}
}
