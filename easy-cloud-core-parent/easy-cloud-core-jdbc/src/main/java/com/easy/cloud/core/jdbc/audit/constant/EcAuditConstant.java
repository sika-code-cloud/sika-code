package com.easy.cloud.core.jdbc.audit.constant;

/**
 * 
 * <p>
 * 审计常量类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月5日 上午11:05:13
 */
public class EcAuditConstant {

	/**
	 * 
	 * <p>
	 * 审计操作类型
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年5月5日 上午11:05:46
	 */
	public static class EcActionType {
		/** 操作类型---保存(type = 1)---保存单个实体 */
		public static final int SAVE = 1;
		/** 操作类型---update(type = 2)---更新单个实体 */
		public static final int UPDATE = 2;
		/** 操作类型---save_batch(type = 1)---批量保存实体列表 */
		public static final int SAVE_BATCH = 3;
		/** 操作类型---update_batch(type = 2)---批量更新实体列表 */
		public static final int UPDATE_BATCH = 4;
	}

	/**
	 * 
	 * <p>
	 * 审计类型
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年5月5日 上午11:05:46
	 */
	public static class EcType {
		/** 类型---保存(type = 1)---1 */
		public static final int SAVE = 1;
		/** 类型---更新---2 */
		public static final int UPDATE = 2;
	}
}
