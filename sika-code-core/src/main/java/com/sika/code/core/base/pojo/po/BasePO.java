package com.sika.code.core.base.pojo.po;

import com.sika.code.core.base.pojo.BasePoJo;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础持久化类
 *
 * @author daiqi
 * @create 2021-10-13 0:42
 */
@Data
public abstract class BasePO<PRIMARY extends Serializable> extends BasePoJo<PRIMARY> {
}
