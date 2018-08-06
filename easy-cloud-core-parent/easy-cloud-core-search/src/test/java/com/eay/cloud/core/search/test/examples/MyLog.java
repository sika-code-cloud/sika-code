package com.eay.cloud.core.search.test.examples;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @Title: MyLog
 * @Description:
 * @Author tudou
 * @Date 2018/7/29 15:56
 */
public class MyLog {
    private String hostip;
    private String hostname;
    private String log_package;
    private String log_date;
    private String log_level;
    private String message;
    private long size;
    public MyLog(){}

    public MyLog(String hostip, String hostname, String log_package, String log_date, String log_level, String message, long size) {
        this.hostip = hostip;
        this.hostname = hostname;
        this.log_package = log_package;
        this.log_date = log_date;
        this.log_level = log_level;
        this.message = message;
        this.size = size;
    }

    public String getHostip() {
        return hostip;
    }

    public void setHostip(String hostip) {
        this.hostip = hostip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getLog_package() {
        return log_package;
    }

    public void setLog_package(String log_package) {
        this.log_package = log_package;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getLog_level() {
        return log_level;
    }

    public void setLog_level(String log_level) {
        this.log_level = log_level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
