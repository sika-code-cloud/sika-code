package com.sika.code.batch.standard.store.bean;

import lombok.Getter;
import org.springframework.batch.core.JobInstance;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 12:58
 */
@Getter
public class StandardJobInstance extends JobInstance {
    private String instanceKey;
    public StandardJobInstance(Long id, String jobName) {
        super(id, jobName);
    }

    public StandardJobInstance(Long id, String jobName, String instanceKey) {
        super(id, jobName);
        this.instanceKey = instanceKey;
    }
}
