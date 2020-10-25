package com.sika.code.basic.constant;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.util.EnumUtil;

/**
 * 类型枚举接口
 *
 * @author daiqi
 * @create 2019-05-23 14:58
 */
public interface TypeEnumInf {
    String GET_TYPE_NAME = "getType";

    /**
     * 获取类型
     */
    Integer getType();

    /**
     * 获取描述
     */
    String getDesc();

    /**
     * 获取类型 空使用默认的type
     */
    static Integer nullToDefault(Integer oldType, TypeEnumInf typeEnum) {
        if (BaseUtil.isNull(oldType)) {
            oldType = typeEnum.getType();
        }
        return oldType;
    }

    /**
     * <p>
     * 根据类型查找指定枚举类的枚举对象
     * </p>
     *
     * @param type          : 类型
     * @param typeEnumClass : 类型枚举class
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:26
     */
    static <T extends TypeEnumInf> T find(Integer type, Class<T> typeEnumClass) {
        return EnumUtil.find(type, GET_TYPE_NAME, typeEnumClass);
    }

    /**
     * <p>
     * 判断类型是否存在 存在返回true
     * </p>
     *
     * @param type
     * @param typeEnumClass
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:25
     */
    static <T extends TypeEnumInf> boolean exist(Integer type, Class<T> typeEnumClass) {
        return EnumUtil.exist(type, GET_TYPE_NAME, typeEnumClass);

    }

    /**
     * <p>
     * 判断类型是否不存在 不存在返回true
     * </p>
     *
     * @param type
     * @param typeEnumClass
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:25
     */
    static <T extends TypeEnumInf> boolean notExist(Integer type, Class<T> typeEnumClass) {
        return !exist(type, typeEnumClass);
    }
}
