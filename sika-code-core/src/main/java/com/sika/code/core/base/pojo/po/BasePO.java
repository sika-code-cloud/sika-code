package com.sika.code.core.base.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class BasePO<PRIMARY extends Serializable> extends BasePoJo<PRIMARY> {
    @TableId(type = IdType.AUTO)
    private PRIMARY id;
}
