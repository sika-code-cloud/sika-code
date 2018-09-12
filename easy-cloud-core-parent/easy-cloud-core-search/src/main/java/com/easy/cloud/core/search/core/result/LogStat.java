package com.easy.cloud.core.search.core.result;

/**
 * @author tudou
 * @Title: LogStat
 * @Description:
 * @date 2018/8/14 11:20
 */
public interface LogStat {
    /**
    * <p>
    * 设置count值
    * </p >
    * @author tudou
    * @date 2018/8/14 11:20
    * @param count count值
    * @return void
    */
    void setCount(String count);
    /**
    * <p>
    * 获取结果值
    * </p >
    * @author tudou
    * @date 2018/8/14 11:21
    * @return java.lang.String
    */
    String getCount();
    /**
    * <p>
    * 日期
    * </p >
    * @author tudou
    * @date 2018/8/14 11:21
    * @param
    * @return java.lang.String
    */
    String getDate();
}
