package com.sika.code.standard.footer.demo.common.config;

import com.sika.code.standard.chain.pipeline.Pipeline;
import com.sika.code.standard.chain.pipeline.StandardPipeline;
import com.sika.code.standard.chain.valve.StandardValve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 责任链配置 在这里配置管道相应的bean
 *
 * @author daiqi
 * @create 2019-05-16 14:47
 */
@Configuration
public class ChainConfig {
    public Pipeline<Object> testPipeline() {
        return new StandardPipeline<>()
                .addValve(new ValveTest("test1"))
                .addValve(new ValveTest1("test2"))
                ;

    }

    /**
     * <p>
     * 阀门测试类 -- 只是为了测试
     * </p>
     *
     * @author daiqi
     * @date 2019/5/16 15:46
     * @return
     */
    @Slf4j
    public static class ValveTest extends StandardValve<Object> {
        public ValveTest() {
        }

        public ValveTest(String name) {
            super(name);
        }

        @Override
        public boolean doInvoke(Object ruleType) {
           return true;
        }
    }

    @Slf4j
    public static class ValveTest1 extends StandardValve<Object> {
        public ValveTest1() {
        }

        public ValveTest1(String name) {
            super(name);
        }

        @Override
        public boolean doInvoke(Object ruleType) {
           return true;
        }
    }
}
