package com.sika.code.demo.infrastructure.db.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sika.code.db.po.BasePoNone;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  基础持久化实体
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/6/22 9:52
 */
@Getter
@Setter
public abstract class BaseBizPO extends BasePoNone<Long> {

    /**
     * 搜索值
     */
    @TableField(exist = false)
    private String searchValue;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}
