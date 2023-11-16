package com.sika.code.monitor.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author daiqi
 * @create 2023-10-20 22:50
 */
public class AbstractConfigMonitorRefresh implements InitializingBean, ApplicationRunner {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
