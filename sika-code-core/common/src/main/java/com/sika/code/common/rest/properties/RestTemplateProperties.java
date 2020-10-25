package com.sika.code.common.rest.properties;

import com.sika.code.basic.constant.PropertiesConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author daiqi
 * @create 2018-12-04 11:17
 */
@Data
@ConfigurationProperties(prefix = PropertiesConstant.REST_TEMPLATE)
public class RestTemplateProperties {
    /** 默认值-------------------------------------------------------------------------begin */
    /**
     * 总连接数 --- 默认值
     */
    private static final Integer MAX_TOTAL_DEFAULT = 1000;
    /**
     * 同路由的并发数 --- 默认值
     */
    private static final Integer DEFAULT_MAX_PER_ROUTE_DEFAULT = 1000;
    /**
     * 连接超时 --- 默认值
     * 单位毫秒
     */
    private static final Integer CONNECT_TIMEOUT_DEFAULT = 12000;
    /**
     * 数据读取超时时间，即SocketTimeout --- 默认值
     * 单位毫秒
     */
    private static final Integer READ_TIMEOUT_DEFAULT = 90000;
    /**
     * 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的 --- 默认值
     * 单位毫秒
     */
    private static final Integer CONNECTION_REQUEST_TIMEOUT_DEFAULT = 200;
    /**
     * 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。 --- 默认值
     */
    private static final boolean BUFFER_REQUEST_BODY_DEFAULT = false;
    /** 默认值-------------------------------------------------------------------------end */

    /**
     * 总连接数
     */
    private Integer maxTotal = MAX_TOTAL_DEFAULT;
    /**
     * 同路由的并发数
     */
    private Integer defaultMaxPerRoute = DEFAULT_MAX_PER_ROUTE_DEFAULT;
    /**
     * 连接超时
     * 单位毫秒
     */
    private Integer connectTimeout = CONNECT_TIMEOUT_DEFAULT;
    /**
     * 数据读取超时时间，即SocketTimeout
     * 单位毫秒
     */
    private Integer readTimeout = READ_TIMEOUT_DEFAULT;
    /**
     * 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
     * 单位毫秒
     */
    private Integer connectionRequestTimeout = CONNECTION_REQUEST_TIMEOUT_DEFAULT;
    /**
     * 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
     */
    private Boolean bufferRequestBody = BUFFER_REQUEST_BODY_DEFAULT;
}
