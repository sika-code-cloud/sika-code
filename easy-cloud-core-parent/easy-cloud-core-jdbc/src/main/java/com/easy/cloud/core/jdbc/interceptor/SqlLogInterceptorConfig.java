package com.easy.cloud.core.jdbc.interceptor;

/**
 * @author lk
 * @Description: 需要自动配置的Bean
 * @date 2018/3/12 16:23
 */
public class SqlLogInterceptorConfig {

    private Boolean openLog = true;

    public Boolean getOpenLog() {
        return openLog;
    }

    public void setOpenLog(Boolean openLog) {
        this.openLog = openLog;
    }
}