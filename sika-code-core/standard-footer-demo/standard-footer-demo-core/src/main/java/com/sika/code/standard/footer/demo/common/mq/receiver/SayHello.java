package com.sika.code.standard.footer.demo.common.mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE
        , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SayHello {
    public void say() {
        log.info("hello");
    }
}
