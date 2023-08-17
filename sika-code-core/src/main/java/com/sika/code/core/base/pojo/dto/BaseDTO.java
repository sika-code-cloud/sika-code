package com.sika.code.core.base.pojo.dto;

import com.sika.code.core.base.pojo.BasePoJo;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础数据传输对象
 *
 * @author daiqi
 * @create 2021-10-13 23:27
 */
@Data
public abstract class BaseDTO<PRIMARY extends Serializable> extends BasePoJo<PRIMARY> {
    protected PRIMARY id;
}
