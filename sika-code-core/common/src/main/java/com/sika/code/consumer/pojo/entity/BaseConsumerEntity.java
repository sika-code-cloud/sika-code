package com.sika.code.consumer.pojo.entity;

/**
 * @author daiqi
 * @create 2018-08-22 21:00
 */
public interface BaseConsumerEntity {

    /**
     * 数据类型
     */
    Integer getDataType();

    void setDataType(Integer dataType);

    /**
     * 数据子类型
     */
    Integer getDataSubType();

    void setDataSubType(Integer dataSubType);

    /**
     * 接收的json数据
     */
    String getDataJson();

    void setDataJson(String jsonData);

    /**
     * 防重code
     */
    String getReCode();

    void setReCode(String reCode);
}
