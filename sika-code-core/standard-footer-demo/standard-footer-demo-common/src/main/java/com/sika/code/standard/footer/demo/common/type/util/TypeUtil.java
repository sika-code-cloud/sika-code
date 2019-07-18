package com.sika.code.standard.footer.demo.common.type.util;

import com.sika.code.basic.constant.BaseConstant;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.footer.demo.common.type.constant.CompareType;
import com.sika.code.standard.footer.demo.common.type.constant.DataType;
import com.sika.code.standard.footer.demo.common.type.constant.ShowFlagType;
import com.sika.code.standard.footer.demo.common.type.constant.UnitType;

/**
 * @author daiqi
 * @create 2019-05-27 17:38
 */
public class TypeUtil {

    /**
     * <p>
     * 校验可用类型
     * </p>
     *
     * @param available
     * @return void
     * @author daiqi
     * @date 2019/5/28 15:10
     */
    public static void verifyAvailable(Integer available) {
        TypeUtil.verifyType(available, BaseConstant.AvailableEnum.class, "不支持的可用类型available");
    }

    /**
     * <p>
     * 校验比较类型
     * </p>
     *
     * @param compareType : 比较类型
     * @return void
     * @author daiqi
     * @date 2019/5/22 17:28
     */
    public static void verifyCompareType(Integer compareType) {
        TypeUtil.verifyType(compareType, CompareType.class, "不支持的比较类型compareType");
    }

    /**
     * <p>
     * 校验单位类型
     * </p>
     *
     * @param unitType : 单位类型
     * @return void
     * @author daiqi
     * @date 2019/5/27 17:44
     */
    public static void verifyUnitType(Integer unitType) {
        TypeUtil.verifyType(unitType, UnitType.class, "不支持的单位类型unitType");
    }

    /**
     * <p>
     * 校验显示类型
     * </p>
     *
     * @param showFlag : 显示类型
     * @return void
     * @author daiqi
     * @date 2019/5/27 17:44
     */
    public static void verifyShowType(Integer showFlag) {
        TypeUtil.verifyType(showFlag, ShowFlagType.class, "不支持的显示类型showFlag");
    }

    /**
     * <p>
     * 校验数据类型
     * </p>
     *
     * @param dataType
     * @return void
     * @author daiqi
     * @date 2019/5/27 17:44
     */
    public static void verifyDataType(Integer dataType) {
        TypeUtil.verifyType(dataType, DataType.class, "不支持的数据类型dataType");
    }

    /**
     * <p>
     * 校验类型
     * </p>
     *
     * @param type          : 类型
     * @param typeEnumClass : 类型枚举class
     * @param errorMsg      : 错误返回的错误信息
     * @return void
     * @author daiqi
     * @date 2019/5/27 18:04
     */
    public static <T extends TypeEnumInf> void verifyType(Integer type, Class<T> typeEnumClass, String errorMsg) {
        boolean exist = TypeEnumInf.exist(type, typeEnumClass);
        if (!exist) {
            throwDataError(errorMsg + ":" + type);
        }
    }

    /**
     * 抛出数据错误异常
     */
    public static void throwDataError(String errorMsg) {
        throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR, errorMsg);
    }
}
