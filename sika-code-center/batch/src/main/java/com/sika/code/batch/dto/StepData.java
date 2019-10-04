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
     * 封装异常数据
     */
    private StepExceptionData<I, O> stepExceptionData;

    public StepData<I, O> build() {
        if (BaseUtil.isNull(stepCommonData)) {
            stepCommonData = new StepCommonData().build();
        }
        if (BaseUtil.isNull(stepExceptionData)) {
            stepExceptionData = new StepExceptionData<I, O>().build();
        }
        return this;
    }

    /**
     * 注册监听器
     */
    public StepData register(StepListener stepListener) {
        stepExceptionData.register(stepListener);
        return this;
    }
}
