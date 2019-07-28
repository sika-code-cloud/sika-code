package com.sika.code.cache.redis.constant;

/**
 *
 * @ClassName : DqRedisConstant
 * @Description : reids常量类
 * @author daiqi
 * @date 2017年12月7日 下午5:50:02
 *
 */
public class RedisConstant {

	/**
	 * 执行redis的Action类型
	 * @author daiqi
	 * @date 2018年4月25日 下午11:48:03
	 */
	public enum RedisActionType {
		/** action---查询 */
		QUERY(1, "query"),
		/** action---更新 */
		UPDATE(2, "update"),
		/** action---保存 */
		SAVE(3, "save"),
		/** action---删除 */
		DELETE(4, "delete"),
		;
		private int type;
		private String value;

		RedisActionType(int type, String value) {
			this.type = type;
			this.value = value;
		}
		public int getType() {
			return type;
		}
		public String getValue() {
			return value;
		}

	}
}
