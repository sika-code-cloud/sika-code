package com.sika.code.core.base.errorcode;


import com.sika.code.core.base.constant.BaseCodeEnum;

/**
 * @author daiqi
 * @Description: 错误代码基类
 * @date 2018/3/21 21:17
 */
public interface BaseErrorCode extends BaseCodeEnum {
    String getMessage();

    @Override
    default String getDesc() {
        return getMessage();
    }
}
