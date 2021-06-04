package com.sika.code.standard.footer.demo.common.mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daiqi
 * @create 2020-06-14 23:18
 */
@Slf4j
@Service
public class SayHelloController {
    @Autowired
    private SayHelloService sayHelloService;
    @Autowired
    private SayHello sayHello;

    public void test() {
        sayHelloService.test(sayHello);
    }
}
