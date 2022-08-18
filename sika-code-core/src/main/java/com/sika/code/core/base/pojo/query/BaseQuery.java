package com.sika.code.core.base.pojo.query;

import com.sika.code.core.base.pojo.BasePoJo;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础查询对象
 *
 * @author daiqi
 * 创建日期 2018年1月6日 下午3:43:09
 */
@Data
public abstract class BaseQuery<PRIMARY extends Serializable> extends BasePoJo<PRIMARY> {
    protected PRIMARY id;
}
