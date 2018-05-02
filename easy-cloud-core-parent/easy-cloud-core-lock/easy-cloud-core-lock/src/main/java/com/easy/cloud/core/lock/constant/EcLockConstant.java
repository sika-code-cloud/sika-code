package com.easy.cloud.core.lock.constant;

import java.util.Map;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;

/**
 * 
 * <p>
 * 锁常量类
 * </p>
 *
 * <pre>
 *  说明：定义与锁组件相关的公共常量
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年4月12日 上午11:32:30
 */
public class EcLockConstant {

	/**
	 * 
	 * <p>
	 * 锁的容器
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年4月16日 上午10:30:08
	 */
	public static class EcLockContainer {
		private static final Map<String, Object> JDK_LOCK_CONTAINER = EcMapUtils.newConcurrentHashMap();

		/** 获取jdk锁容器 */
		public static Map<String, Object> getJdkLockContainer() {
			return JDK_LOCK_CONTAINER;
		}

		/** 往jdk锁容器中压入数据 */
		public static Object putValueToJdkLockContainer(String key, Object value) {
			return getJdkLockContainer().put(key, value);
		}

		/** 从jdk锁容器中获取数据 */
		public static Object getValueFromJdkLockContainer(String key) {
			return getJdkLockContainer().get(key);
		}
		/** 从jdk锁容器中删除数据 */
		public static Object removeValueFromJdkLockContainer(String key) {
			return getJdkLockContainer().remove(key);
		}
	}

	/**
	 * 
	 * <p>
	 * 锁时间常量类
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年4月12日 下午12:02:00
	 */
	public static class EcLockTime {
		/** 默认最长等待时间---30---单位根据TimeUnit进行选择 */
		public static final long WAIT_TIME_DEFAULT = 30;
		/** 默认锁超时时间---5---单位根据TimeUnit进行选择 */
		public static final long LEASE_TIME_DEFAULT = 5;
	}

	/**
	 * 
	 * <p>
	 * 锁名称描述常量
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年4月12日 上午11:58:18
	 */
	public static class EcLockNameDesc {
		/** 锁名称默认前缀---LOCK */
		public static final String LOCK_NAME_PRE_DEFAULT = "LOCK";
		/** 锁名称默认后缀---LOCK */
		public static final String LOCK_NAME_SUFFIX_DEFAULT = "LOCK";
	}

	/**
	 * 
	 * <p>
	 * 锁模板类型枚举
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年4月13日 上午9:37:44
	 */
	public static enum EcLockTemplateTypeEnum {
		/** 锁模板---jedis锁模板 */
		JEDIS(1, "jedis模板"),
		/** 锁模板---redission锁模板 */
		REDISSION(2, "redisson模板"),;

		private EcLockTemplateTypeEnum(int type, String name) {
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
		 * 判断传入的枚举是否是JEDIS枚举
		 * </p>
		 *
		 * @param lockTemplateTypeEnum
		 * @return
		 * @author daiqi 创建时间 2018年4月13日 上午9:45:26
		 */
		public static boolean isJedis(EcLockTemplateTypeEnum lockTemplateTypeEnum) {
			if (EcBaseUtils.equals(JEDIS, lockTemplateTypeEnum)) {
				return true;
			}
			return false;
		}
		/**
		 * 
		 * <p>
		 * 判断传入的枚举是否是Redission枚举
		 * </p>
		 *
		 * @param lockTemplateTypeEnum
		 * @return
		 * @author daiqi 创建时间 2018年4月13日 上午9:45:26
		 */
		public static boolean isRedission(EcLockTemplateTypeEnum lockTemplateTypeEnum) {
			if (EcBaseUtils.equals(REDISSION, lockTemplateTypeEnum)) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 
	 * <p>
	 * 锁类型枚举类，定义所有锁类型
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年4月12日 上午11:41:51
	 */
	public static enum EcLockTypeEnum {
		/** 可重入锁---公平锁---即先来先得 */
		FAIR(1, "公平锁"),
		/** 可重入锁---非公平锁---锁资源释放期间，其他资源可以尝试获取。 */
		UNFAIR(2, "非公平锁"),
		/** 不可重入---读锁---没有线程正在做写操作，且没有线程在请求写操作 */
		READ(3, "读写锁"),
		/** 不可重入---读锁---没有线程正在做读写操作 */
		WRITE(4, "写锁"),;
		/** 锁的类型 */
		private int type;
		private String name;

		private EcLockTypeEnum(int type, String name) {
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
		 * 创建时间 2018年4月13日 下午1:25:06
		 */
		public static boolean isFair(EcLockTypeEnum lockTypeEnum) {
			if (EcBaseUtils.equals(FAIR, lockTypeEnum)) {
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
		 * 创建时间 2018年4月13日 下午1:25:06
		 */
		public static boolean isUnfair(EcLockTypeEnum lockTypeEnum) {
			if (EcBaseUtils.equals(UNFAIR, lockTypeEnum)) {
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
		 * 创建时间 2018年4月13日 下午1:25:06
		 */
		public static boolean isRead(EcLockTypeEnum lockTypeEnum) {
			if (EcBaseUtils.equals(READ, lockTypeEnum)) {
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
		 * 创建时间 2018年4月13日 下午1:25:06
		 */
		public static boolean isWrite(EcLockTypeEnum lockTypeEnum) {
			if (EcBaseUtils.equals(WRITE, lockTypeEnum)) {
				return true;
			}
			return false;
		}
	}
}
