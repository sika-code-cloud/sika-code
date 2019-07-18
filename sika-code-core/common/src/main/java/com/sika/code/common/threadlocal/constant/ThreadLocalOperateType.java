package com.sika.code.common.threadlocal.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ThreadLocal操作类型
 *
 * @author daiqi
 * @create 2019-06-23 15:46
 */
@Getter
@AllArgsConstructor
public enum ThreadLocalOperateType implements TypeEnumInf {
    /** ThreadLocal类型枚举 */
    THREAD_LOCAL_INHERITABLE(0, "操作自定义的ThreadLocal类型和InheritableThreadLocal对象类型"),
    THREAD_LOCAL(1, "操作自定义ThreadLocal对象类型"),
    INHERITABLE(2, "操作InheritableThreadLocal对象类型")
    ;
    private Integer type;
    private String desc;
}
