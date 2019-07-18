package com.sika.code.consumer.annotation;


import java.lang.annotation.*;

/**
 * <p>
 * redis注解
 * </p>
 *
 * <pre>
 *  说明：通过在需要消费的方法上添加该注解 ，即可对方法提供消费功能
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 * 
 * @author daiqi
 * @date 2018年4月20日 下午9:12:55
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsumerAnnotation {


}
