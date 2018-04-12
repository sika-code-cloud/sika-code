package com.easy.cloud.core.distributionlock.constant;

/**
 * 
 * <p>
 * 分布式锁常量类
 * </p>
 *
 * <pre>
 *  说明：定义与分布式锁组件相关的公共常量
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年4月12日 上午11:32:30
 */
public class EcDistributedLockConstant {
	/**
	 * 
	 * <p>
	 * 分布式锁时间常量类
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年4月12日 下午12:02:00
	 */
	public static class EcDistributedLockTime {
		/** 默认最长等待时间---30---单位秒 */
		public static final long WAIT_TIME_DEFAULT = 30;
		/** 默认锁超时时间---5---单位秒 */
		public static final long LEASE_TIME_DEFAULT = 5;
	}
	
	/**
	 * 
	 * <p>
	 * 分布式锁名称描述常量
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年4月12日 上午11:58:18
	 */
	public static class EcDistributedLockNameDesc {
		/** 锁名称默认前缀---lock */
		public static final String LOCK_NAME_PRE_DEFAULT = "LOCK";
		/** 锁名称默认后缀---lock */
		public static final String LOCK_NAME_SUFFIX_DEFAULT = "LOCK";
	}
	
	
	/**
	 * 
	 * <p>
	 * 分布式锁类型枚举类，定义所有锁类型
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年4月12日 上午11:41:51
	 */
	public static enum EcDistributedLockTypeEnum {
		firm(1, "公平锁")
		;
		private int type;
		private String name;

		private EcDistributedLockTypeEnum(int type, String name) {
			this.type = type;
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}

	}
}
