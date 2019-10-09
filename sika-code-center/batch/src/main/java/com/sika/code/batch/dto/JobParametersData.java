package com.sika.code.batch.dto;

import com.sika.code.basic.util.Assert;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.batch.core.StepListener;

/**
 * @author daiqi
 * @create 2019-09-29 23:17
 */
@Data
@ToString
@Accessors(chain = true)
public class JobParametersData<I, O> {
    private StepData<I, O> stepData;

    public JobParametersData<I, O> build() {
        Assert.verifyObjNull(stepData, "stepData");
        return this;
    }

    public JobParametersData<I, O> register(StepListener stepListener) {
        stepData.register(stepListener);
        return this;
    }
}
