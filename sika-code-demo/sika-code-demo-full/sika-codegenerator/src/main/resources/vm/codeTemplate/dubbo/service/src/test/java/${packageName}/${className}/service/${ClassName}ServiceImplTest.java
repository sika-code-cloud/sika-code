package ${packageName}.${className}.service;

import ${packageName}.service.${className}.service.impl.${ClassName}ServiceImpl;
import ${packageName}.service.${className}.service.I${ClassName}Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;


/**
 * ${className}Service接口
 *
 * @author ${author}
 * @date ${datetime}
 */
@ContextConfiguration(classes = ${ClassName}ServiceImplTest.MockBeans.class)
@Import(${ClassName}ServiceImpl.class)
public class ${ClassName}ServiceImplTest extends AbstractTestNGSpringContextTests {

    static class MockBeans{
        //
//    参考如下写法，mock一个spring bean实例的方法的返回
//    @Bean
//    ChannelFacade mockUser(){
//        ChannelFacade mock = Mockito.mock(ChannelFacade.class);
//        FundsBo fundsBo=new FundsBo();
//        fundsBo.setTradeId("111");
//        fundsBo.setPayFee(new Money(10d));
//        Mockito.when(mock.sendRequstToChannel(any())).thenReturn(fundsBo);
//        return mock;
//    }
    }

    static Logger logger= LoggerFactory.getLogger(${ClassName}ServiceImplTest.class);

    @Autowired
    I${ClassName}Service ${className}Service;

    //test your service here



}