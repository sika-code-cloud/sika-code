package com.sika.code.core.base.test;

import cn.hutool.core.date.StopWatch;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
@Data
public class BaseTest {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    protected StopWatch stopWatch = new StopWatch(this.getClass().getSimpleName() + "-单元测试");
    @Rule
    public TestName testName = new TestName();

    @Before
    public void testBegin() throws NoSuchMethodException {
        stopWatch.start();
        Method m = getClass().getMethod(testName.getMethodName());
        log.info("方法[{}]开始执行单元测试", m.getName());
    }

    @After
    public void testEnd() throws NoSuchMethodException {
        Method m = getClass().getMethod(testName.getMethodName());
        stopWatch.stop();
        log.info("方法[{}]结束执行单元测试，执行统计详情\n{}", m.getName(), stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
    }
}
