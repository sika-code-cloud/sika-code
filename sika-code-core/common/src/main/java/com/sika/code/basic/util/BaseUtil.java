package com.sika.code.basic.util;

import com.sika.code.common.array.ArrayUtil;

/**
 * @author daiqi
 * @ClassName : DqBaseUtils
 * @Description : 基础工具类
 * @date 2017年12月6日 下午1:46:43
 */
public class BaseUtil {
    /**
     * <p>
     * 判断obj是否为空，为空返回true
     * </p>
     *
     * @param obj
     * @return true 对象为空
     * @author daiqi
     * @date 2017年12月6日 下午1:47:50
     */
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 判断传入的对象数组是不是都不为空
     * 都不为空返回true
     * </p>
     *
     * @param objects : 对象列表
     * @return boolean
     * @author daiqi
     * @date 2019/6/30 9:22
     */
    public static boolean isAllNotNull(Object... objects) {
        return !isAnyNull(objects);
    }

    /**
     * <p>
     * 判断传入的objs列表是否存在为null的值
     * 1：若objs本身为null或者空数组也返回true
     * 2：只要存在一个为null即返回true
     * </p>
     *
     * @param objs : 对象数组
     * @return boolean
     * @author daiqi
     * @date 2019/6/30 9:12
     */
    public static boolean isAnyNull(Object... objs) {
        if (ArrayUtil.isEmpty(objs)) {
            return true;
        }
        for (Object obj : objs) {
            if (isNull(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * 判断传入的objs列表是否都是null
     * 1：若objs本身为null或者空数组也返回true
     * 2：若objs中的对象都是null返回true
     * </p>
     *
     * @param objs : 对象数组
     * @return boolean
     * @author daiqi
     * @date 2019/6/30 9:06
     */
    public static boolean isAllNull(Object... objs) {
        if (ArrayUtil.isEmpty(objs)) {
            return true;
        }
        for (Object obj : objs) {
            if (isNotNull(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 判断obj是否不为空
     * </p>
     *
     * @param obj
     * @return
     * @author daiqi
     * @date 2017年12月6日 下午1:48:38
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * <p>
     * 判断当前对象是否是boolean类型的对象
     * 为空或者非boolean型返回false
     * </p>
     * <pre>
     *     obj = null return false
     *     obj instanceof Boolean return true
     * </pre>
     *
     * @param obj
     * @return boolean
     * @author daiqi
     * @date 2019/6/22 21:49
     */
    public static boolean isBooleanObj(Object obj) {
        if (isNull(obj)) {
            return false;
        }
        return (obj instanceof Boolean && boolean.class.equals(obj.getClass()));
    }

    /**
     * <p>
     * 比较两个对象是否相等、若为原始类型与包装类型比较则将原始类型放第二个参数
     * </p>
     *
     * @param obj1
     * @param obj2
     * @return
     * @author daiqi 创建时间 2018年2月7日 下午7:52:56
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (isNull(obj1) || isNull(obj2)) {
            return false;
        }
        if (obj1.equals(obj2)) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 比较两个对象是否不相等、若为原始类型与包装类型比较则将原始类型放第二个参数
     * </p>
     *
     * @param obj1
     * @param obj2
     * @return
     * @author daiqi 创建时间 2018年2月7日 下午7:52:56
     */
    public static boolean notEquals(Object obj1, Object obj2) {
        return !equals(obj1, obj2);
    }

}
