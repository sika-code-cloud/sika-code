package com.sika.code.batch.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

/**
 * @author daiqi
 * @create 2019-09-29 23:17
 */
@Data
@ToString
@Accessors(chain = true)
public class JobParametersData<I, O> {
    private ItemReader<I> itemReader;
    private ItemProcessor<I, O> itemProcessor;
    private ItemWriter<O> itemWriter;
    private StepData<I, O> stepData;

    public JobParametersData<I, O> build() {
        if (this.stepData == null) {
            this.stepData = new StepData<I, O>().build();
        }
        return this;
    }

    public JobParametersData<I, O> register(StepListener stepListener) {
        stepData.register(stepListener);
        return this;
    }
}
