package com.sika.code.lock.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁的尝试类型
 *
 * @author daiqi
 * @create 2019-07-29 11:10
 */
@AllArgsConstructor
@Getter
public enum LockTryType implements TypeEnumInf {
    /** 锁尝试非尝试类型 */
    UN_TRY(1, "非尝试锁"),
    TRY(2, "尝试锁"),
    ;
    private Integer type;
    private String desc;
}
