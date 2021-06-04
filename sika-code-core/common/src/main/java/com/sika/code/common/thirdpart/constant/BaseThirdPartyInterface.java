package com.sika.code.common.thirdpart.constant;

/**
 * 接口基础类
 *
 * @author daiqi
 * @create 2018-08-15 17:21
 */
public interface BaseThirdPartyInterface {
    /**
     * <p>
     * 请求的接口uri不包括域名和端口以及项目名称
     * </p>
     *
     * @param
     * @return java.lang.String
     * @author daiqi
     * @date 2019/4/4 15:56
     */
    String getUri();

    /**
     * <p>
     * 接口描述
     * </p>
     *
     * @param
     * @return java.lang.String
     * @author daiqi
     * @date 2019/4/4 15:56
     */
    String getDesc();
}
