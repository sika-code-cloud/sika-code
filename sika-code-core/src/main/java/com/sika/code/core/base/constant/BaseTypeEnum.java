package com.sika.code.core.base.constant;


import cn.hutool.core.lang.Assert;
import com.sika.code.core.util.EnumUtil;

/**
 * 类型枚举接口
 *
 * @author daiqi
 * @create 2019-05-23 14:58
 */
public interface BaseTypeEnum<T> {
    String GET_TYPE_NAME = "getType";
    String GET_CODE_NAME = "getCode";

    /**
     * 获取类型
     */
    T getType();

    /**
     * 获取描述
     */
    String getDesc();

    /**
     * <p>
     * 根据类型查找指定枚举类的枚举对象
     * </p>
     *
     * @param value         : 类型
     * @param typeEnumClass : 类型枚举class
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:26
     */
    static <T, Enum extends BaseTypeEnum<T>> Enum find(T value, Class<Enum> typeEnumClass) {
        return EnumUtil.find(typeEnumClass, GET_TYPE_NAME, value);
    }

    static <T, Enum extends BaseTypeEnum<T>> Enum findByCode(T value, Class<Enum> typeEnumClass) {
        return EnumUtil.find(typeEnumClass, GET_CODE_NAME, value);
    }

    /**
     * <p>
     * 判断类型是否存在 存在返回true
     * </p>
     *
     * @param value
     * @param typeEnumClass
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:25
     */
    static <T, Enum extends BaseTypeEnum<T>> boolean exist(T value, Class<Enum> typeEnumClass) {
        return EnumUtil.exist(typeEnumClass, GET_TYPE_NAME, value);

    }

    /**
     * <p>
     * 判断类型是否不存在 不存在返回true
     * </p>
     *
     * @param value
     * @param typeEnumClass
     * @return boolean
     * @author daiqi
     * @date 2019/5/23 17:25
     */
    static <T, Enum extends BaseTypeEnum<T>> boolean notExist(T value, Class<Enum> typeEnumClass) {
        return !exist(value, typeEnumClass);
    }

    /**
     * <p>
     * 校验类型的值是否符合枚举值-不符合将抛出异常
     * </p>
     *
     * @param value         : 类型的值
     * @param typeEnumClass : 类型枚举类名
     * @param msg           : 消息前缀
     */
    static <T, Enum extends BaseTypeEnum<T>> void verifyTypeValue(T value, Class<Enum> typeEnumClass, String msg) {
        Assert.notNull(value, "【{}】类型值为空", msg);
        boolean exit = BaseTypeEnum.exist(value, typeEnumClass);
        Assert.isTrue(exit, "【{}】类型值【{}】有误", msg, value);
    }

    static <T, Enum extends BaseTypeEnum<T>> String getDesc(Class<Enum> tClass, T value) {
        if (value == null || tClass == null) {
            return null;
        }
        Enum baseEnumType = find(value, tClass);
        if (baseEnumType == null) {
            return null;
        }
        return baseEnumType.getDesc();
    }
}
