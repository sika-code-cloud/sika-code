package com.sika.code.standard.footer.demo.common.type.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 显示类型
 *
 * @author daiqi
 * @create 2019-05-21 21:41
 */
@AllArgsConstructor
@Getter
public enum ShowFlagType implements TypeEnumInf {
    /**
     * 显示类型 --- 不显示 --- 0
     */
    NO(0, "不显示"),
    /**
     * 显示类型 --- 显示 --- 1
     */
    YES(1, "显示"),
    ;

    private Integer type;
    private String desc;
}
