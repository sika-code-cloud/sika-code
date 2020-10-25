package com.sika.code.consumer.pojo.dto;

/**
 * 消费者数据传输对象
 *
 * @author daiqi
 * @create 2018-08-22 20:20
 */
public interface BaseConsumerDTO {

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
     * 接收的数据
     */
    Object getData();

    void setData(Object data);

    /**
     * 防重code
     */
    String getReCode();

    void setReCode(String reCode);

    String getDataSources();
    /** 数据来源 */
    void setDataSources(String dataSources);
}
