package com.sika.code.batch.dto;

import com.sika.code.basic.util.BaseUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.batch.core.StepListener;

/**
 * step数据
 *
 * @author daiqi
 * @create 2019-10-04 20:15
 */
@Data
@Accessors(chain = true)
public class StepData<I, O> {
    /**
     * 封装基础数据
     */
    private StepCommonData stepCommonData;
    /**
     * 封装监听器数据
     */
    private StepListenerData<I, O> stepListenerData;

    public StepData<I, O> build() {
        if (BaseUtil.isNull(stepCommonData)) {
            stepCommonData = new StepCommonData().build();
        }
        if (BaseUtil.isNull(stepListenerData)) {
            stepListenerData = new StepListenerData<I, O>().build();
        }
        return this;
    }

    /**
     * 注册监听器
     */
    public StepData register(StepListener stepListener) {
        stepListenerData.register(stepListener);
        return this;
    }
}
