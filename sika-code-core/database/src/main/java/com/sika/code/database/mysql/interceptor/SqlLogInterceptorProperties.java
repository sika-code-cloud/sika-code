package com.sika.code.database.mysql.interceptor;

import com.sika.code.basic.constant.PropertiesConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 日志拦截器属性配置
 * </p>
 *
 * @author daiqi
 * @date 2019/5/24 23:54
 */
@Data
@ConfigurationProperties(prefix = PropertiesConstant.LOG_SQL)
public class SqlLogInterceptorProperties {
    private static final Boolean OPENLOG = true;
    private Boolean fire = OPENLOG;

}
