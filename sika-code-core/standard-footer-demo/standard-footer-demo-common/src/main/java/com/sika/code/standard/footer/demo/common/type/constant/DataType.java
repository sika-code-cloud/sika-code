package com.sika.code.standard.footer.demo.common.type.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据类型
 *
 * @author daiqi
 * @create 2019-05-21 17:36
 */
@Getter
@AllArgsConstructor
public enum DataType implements TypeEnumInf {
    /**
     * 数据类型 --- 忽略 --- 0
     */
    IGNORE(0, "忽略"),
    /**
     * 数据类型 --- 选择型 --- 1
     */
    CHOICE(1, "选择型"),

    /**
     * 数据类型 --- 填写型 --- 2
     */
    WRITE(2, "填写型"),
    ;

    private Integer type;
    private String desc;

    public static Boolean isChoice(Integer dataType) {
        if (BaseUtil.isNull(dataType)) {
            return false;
        }
        return BaseUtil.equals(dataType, DataType.CHOICE.getType());
    }
}
