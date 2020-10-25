package com.sika.code.result.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 结果编号配置属性
 *
 * @author daiqi
 * @create 2020-08-30 18:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "sika.code.result.no-config")
public class ResultNoConfigProperties {
    /**
     * result的msgNo默认前缀
     */
    private static final String PREFIX_DEFAULT = "RMN";
    /**
     * 异常的msgNo默认前缀
     */
    private static final String EXCEPTION_PREFIX_DEFAULT = "EMN";
    /**
     * result的msgNo默认后缀长度
     */
    private static final int SUFFIX_COUNT_DEFAULT = 6;

    private String noPrefix = PREFIX_DEFAULT;
    private String exceptionNoPrefix = EXCEPTION_PREFIX_DEFAULT;
    private Integer suffixCount = SUFFIX_COUNT_DEFAULT;

}
