package com.sika.code.lock.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁的公平类型
 *
 * @author daiqi
 * @create 2019-07-29 11:10
 */
@AllArgsConstructor
@Getter
public enum LockType implements TypeEnumInf {
    /** 锁公平非公平类型 */
    UN_FAIR(1, "非公平锁"),
    FAIR(2, "公平锁"),
    /** 该锁若key为list则使用list，若key为普通对象，则会封装进List中 */
    MULTI_LOCK(3, "级联锁"),
    ;
    private Integer type;
    private String desc;
}
