package com.sika.code.migrate.constant;

import com.sika.code.core.base.constant.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *  迁移类型枚举
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 18:33
 */
@Getter
@AllArgsConstructor
public enum MigrateTypeEnum implements BaseTypeEnum<String> {
    DOUBLE_WRITE("10", "流量双写"),
    CONTRAST("20", "流量对比"),
    SWITCH("30", "流量切换"),
    ;
    private final String type;
    private final String desc;

    public static boolean isDoubleWrite(String type) {
        return DOUBLE_WRITE.getType().equals(type);
    }

    public static boolean isContrast(String type) {
        return CONTRAST.getType().equals(type);
    }

    public static boolean isSwitch(String type) {
        return SWITCH.getType().equals(type);
    }
}