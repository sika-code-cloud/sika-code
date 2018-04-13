package com.easy.cloud.core.distributedlock.constant;

import com.easy.cloud.core.basic.utils.EcBaseUtils;

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
	 * @author daiqi 创建时间 2018年4月12日 下午12:02:00
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
	 * @author daiqi 创建时间 2018年4月12日 上午11:58:18
	 */
	public static class EcDistributedLockNameDesc {
		/** 锁名称默认前缀---LOCK */
		public static final String LOCK_NAME_PRE_DEFAULT = "LOCK";
		/** 锁名称默认后缀---LOCK */
		public static final String LOCK_NAME_SUFFIX_DEFAULT = "LOCK";
	}

	/**
	 * 
	 * <p>
	 * 分布式锁模板类型枚举
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年4月13日 上午9:37:44
	 */
	public static enum EcDistributedLockTemplateTypeEnum {
		/** 分布式锁模板---redission分布式锁模板 */
		REDISSION(1, "redisson模板");

		private EcDistributedLockTemplateTypeEnum(int type, String name) {
			this.type = type;
			this.name = name;
		}

		private int type;
		private String name;
		
		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}
		
		/**
		 * 
		 * <p>
		 * 判断传入的枚举是否是Redission枚举
		 * </p>
		 *
		 * @param distributedLockTemplateTypeEnum
		 * @return
		 * @author daiqi
		 * 创建时间    2018年4月13日 上午9:45:26
		 */
		public static boolean isRedission(EcDistributedLockTemplateTypeEnum distributedLockTemplateTypeEnum) {
			if (EcBaseUtils.equals(REDISSION, distributedLockTemplateTypeEnum)) {
				return true;
			}
			return false;
		}
		
	}
	
	/**
	 * 
	 * <p>
	 * 分布式锁类型枚举类，定义所有锁类型
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年4月12日 上午11:41:51
	 */
	public static enum EcDistributedLockTypeEnum {
		/** 可重入锁---公平锁---即先来先得 */
		FAIR(1, "公平锁"),
		/** 可重入锁---非公平锁---锁资源释放期间，其他资源可以尝试获取。 */
		UNFAIR(2, "非公平锁"),
		/** 不可重入---读锁---没有线程正在做写操作，且没有线程在请求写操作 */
		READ(3, "读写锁"),
		/** 不可重入---读锁---没有线程正在做读写操作 */
		WRITE(4, "写锁"),
		;
		/** 锁的类型 */
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
		
		/**
		 * 
		 * <p>
		 * 当前枚举是公平锁返回true，否则返回false
		 * </p>
		 * 
		 * 创建时间    2018年4月13日 下午1:25:06
		 */
		public static boolean isFair(EcDistributedLockTypeEnum distributedLockTypeEnum) {
			if (EcBaseUtils.equals(FAIR, distributedLockTypeEnum)) {
				return true;
			}
			return false;
		}
		/**
		 * 
		 * <p>
		 * 当前枚举是非公平锁返回true，否则返回false
		 * </p>
		 * 
		 * 创建时间    2018年4月13日 下午1:25:06
		 */
		public static boolean isUnfair(EcDistributedLockTypeEnum distributedLockTypeEnum) {
			if (EcBaseUtils.equals(UNFAIR, distributedLockTypeEnum)) {
				return true;
			}
			return false;
		}
		
		/**
		 * 
		 * <p>
		 * 当前枚举是读锁返回true，否则返回false
		 * </p>
		 * 
		 * 创建时间    2018年4月13日 下午1:25:06
		 */
		public static boolean isRead(EcDistributedLockTypeEnum distributedLockTypeEnum) {
			if (EcBaseUtils.equals(READ, distributedLockTypeEnum)) {
				return true;
			}
			return false;
		}
		
		/**
		 * 
		 * <p>
		 * 当前枚举是写锁返回true，否则返回false
		 * </p>
		 * 
		 * 创建时间    2018年4月13日 下午1:25:06
		 */
		public static boolean isWrite(EcDistributedLockTypeEnum distributedLockTypeEnum) {
			if (EcBaseUtils.equals(WRITE, distributedLockTypeEnum)) {
				return true;
			}
			return false;
		}
	}
}
