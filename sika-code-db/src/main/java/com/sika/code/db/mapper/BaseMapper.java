package com.sika.code.db.mapper;


import com.sika.code.core.base.pojo.po.BasePO;

import java.io.Serializable;

/**
 * <p>
 * 业务逻辑基础类
 * </p>
 *
 * <pre>
 *  说明：所有业务逻辑类可以继承此类
 *  约定：
 *  命名规范：以Mapper结尾
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:24:24
 */
public interface BaseMapper<T extends BasePO> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

}
