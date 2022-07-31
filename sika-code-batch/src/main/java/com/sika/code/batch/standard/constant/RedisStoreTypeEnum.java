package com.sika.code.batch.standard.constant;

import com.sika.code.core.base.constant.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *  Redis存储类型枚举
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/6/26 13:47
 */
@Getter
@AllArgsConstructor
public enum RedisStoreTypeEnum implements BaseTypeEnum<Integer> {
    STRING(10, "字符串类型"),
    LIST(20, "List类型"),
    SET(30, "Set类型"),
    HASH(40, "HASH类型"),
    ;
    private final Integer type;
    private final String desc;
}