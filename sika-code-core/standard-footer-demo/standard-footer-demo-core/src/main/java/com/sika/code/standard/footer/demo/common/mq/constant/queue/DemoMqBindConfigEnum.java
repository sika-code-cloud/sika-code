package com.sika.code.standard.footer.demo.common.mq.constant.queue;

import com.sika.code.mq.rabbit.common.constant.MqBindingConfigEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author daiqi
 * @create 2019-07-07 12:07
 */
@AllArgsConstructor
@Getter
@ToString
public enum  DemoMqBindConfigEnum implements MqBindingConfigEnum {
    /** 绑定配置枚举 */
    API_CORE(1, "api.core", "测试"),
    API_CORE_ONLY(1, "api.*", "测试"),
    API_CORE_ANY(1, "api.core.#", "测试"),
    ;
    private Integer type;
    private String routingKey;
    private String desc;
}
