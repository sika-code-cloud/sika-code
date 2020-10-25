package com.sika.code.database.primarykey;

import java.io.Serializable;

/**
 * 
 * <p>
 * 基础主键生成器接口
 * </p>
 *
 * <pre>
 *  说明：所有的主键生成策略最终实现该接口
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月8日 下午8:00:50
 */
public interface BaseKeyGenerator {
	/**
	 * 
	 * <p>
	 * 生成主键的接口
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param entityObj : Object : 持久化实体
	 * @return
	 * @author daiqi
	 * @创建时间 2018年5月8日 下午8:02:24
	 */
	Serializable generate(Object entityObj);
}
