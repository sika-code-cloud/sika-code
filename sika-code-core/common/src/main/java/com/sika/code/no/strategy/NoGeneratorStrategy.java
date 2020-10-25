package com.sika.code.no.strategy;

/**
 * @author daiqi
 * @create 2019-06-30 21:35
 */
public interface NoGeneratorStrategy  {

    /**
     * <p>
     * 生成字符串
     * </p>
     *
     * @param count : 指定位数
     * @return java.lang.String
     * @author daiqi
     * @date 2019/6/30 23:19
     */
    String generateStr(int count);

}
