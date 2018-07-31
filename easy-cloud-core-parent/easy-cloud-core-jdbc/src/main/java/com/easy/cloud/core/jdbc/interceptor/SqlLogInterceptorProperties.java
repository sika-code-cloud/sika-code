package com.easy.cloud.core.jdbc.interceptor;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lk
 * @Description: TODO
 * @date 2018/3/12 15:34
 */

@ConfigurationProperties(prefix = "logging.sql")
public class SqlLogInterceptorProperties {
    private static final Boolean OPENLOG = true;
    private Boolean openLog = OPENLOG;

    public Boolean getOpenLog() {
        return openLog;
    }

    public void setOpenLog(Boolean openLog) {
        this.openLog = openLog;
    }
}
