package com.sika.code.batch.dto;

import com.sika.code.basic.util.BaseUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

/**
 * step数据
 *
 * @author daiqi
 * @create 2019-10-04 20:15
 */
@Data
@Accessors(chain = true)
public class StepData<I, O> {
    private ItemReader<I> itemReader;
    private ItemProcessor<I, O> itemProcessor;
    private ItemWriter<O> itemWriter;
    /**
     * 封装基础数据
     */
    private StepCommonData stepCommonData;
    /**
     * 封装监听器数据
     */
    private StepListenerData<I, O> stepListenerData;

    public StepData() {
        init();
    }

    public StepData<I, O> build() {
        this.stepCommonData.build();
        this.stepListenerData.build();
        return this;
    }

    protected void init() {
        initStepCommonData();
        initStepListenerData();
    }

    /**
     * 注册监听器
     */
    public StepData<I, O> register(StepListener stepListener) {
        stepListenerData.register(stepListener);
        return this;
    }


    protected void initStepCommonData() {
        if (BaseUtil.isNull(stepCommonData)) {
            stepCommonData = new StepCommonData();
        }
    }

    protected void initStepListenerData() {
        if (BaseUtil.isNull(stepListenerData)) {
            stepListenerData = new StepListenerData<>();
        }
    }
}
