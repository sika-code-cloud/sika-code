package com.zyd.blog.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 来源枚举
 * @author daiqi
 * @create 2020-09-25 12:43
 */
@Getter
@AllArgsConstructor
public enum SourceEnum {
    /** 流量来源枚举 */
    NORMAL(0, "正常流量"),
    SYSTEM(10, "系统补冲流量"),
    ;
    private Integer type;
    private String desc;

}
