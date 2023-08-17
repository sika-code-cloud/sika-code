package com.sika.code.batch.standard.store;

import cn.hutool.core.collection.CollUtil;
import org.springframework.batch.core.Entity;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 11:22
 */
public class StandardMapStepExecutionDao implements StepExecutionDao {

    private Map<Long, Map<Long, StepExecution>> executionsByJobExecutionId = new ConcurrentHashMap<>();

    private Map<Long, StepExecution> executionsByStepExecutionId = new ConcurrentHashMap<>();

    private AtomicLong currentId = new AtomicLong();

    public void clear() {
        executionsByJobExecutionId.clear();
        executionsByStepExecutionId.clear();
    }

    private static StepExecution copy(StepExecution original) {
        return (StepExecution) SerializationUtils.deserialize(SerializationUtils.serialize(original));
    }

    private static void copy(final StepExecution sourceExecution, final StepExecution targetExecution) {
        // Cheaper than full serialization is a reflective field copy, which is
        // fine for volatile storage
        ReflectionUtils.doWithFields(StepExecution.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                field.setAccessible(true);
                field.set(targetExecution, field.get(sourceExecution));
            }
        }, ReflectionUtils.COPYABLE_FIELDS);
    }

    @Override
    public void saveStepExecution(StepExecution stepExecution) {

        Assert.isTrue(stepExecution.getId() == null, "stepExecution id was not null");
        Assert.isTrue(stepExecution.getVersion() == null, "stepExecution version was not null");
        Assert.notNull(stepExecution.getJobExecutionId(), "JobExecution must be saved already.");

        Map<Long, StepExecution> executions = executionsByJobExecutionId.get(stepExecution.getJobExecutionId());
        if (executions == null) {
            executions = new ConcurrentHashMap<>();
            executionsByJobExecutionId.put(stepExecution.getJobExecutionId(), executions);
        }

        stepExecution.setId(currentId.incrementAndGet());
        stepExecution.incrementVersion();
        StepExecution copy = copy(stepExecution);
        executions.put(stepExecution.getId(), copy);
        executionsByStepExecutionId.put(stepExecution.getId(), copy);

    }

    @Override
    public void updateStepExecution(StepExecution stepExecution) {

        Assert.notNull(stepExecution.getJobExecutionId(), "jobExecution id is null");

        Map<Long, StepExecution> executions = executionsByJobExecutionId.get(stepExecution.getJobExecutionId());
        Assert.notNull(executions, "step executions for given job execution are expected to be already saved");

        final StepExecution persistedExecution = executionsByStepExecutionId.get(stepExecution.getId());
        Assert.notNull(persistedExecution, "step execution is expected to be already saved");

        synchronized (stepExecution) {
            if (!persistedExecution.getVersion().equals(stepExecution.getVersion())) {
                throw new OptimisticLockingFailureException("Attempt to update step execution id="
                        + stepExecution.getId() + " with wrong version (" + stepExecution.getVersion()
                        + "), where current version is " + persistedExecution.getVersion());
            }

            stepExecution.incrementVersion();
            StepExecution copy = new StepExecution(stepExecution.getStepName(), stepExecution.getJobExecution());
            copy(stepExecution, copy);
            executions.put(stepExecution.getId(), copy);
            executionsByStepExecutionId.put(stepExecution.getId(), copy);
        }
    }

    @Override
    @Nullable
    public StepExecution getStepExecution(JobExecution jobExecution, Long stepExecutionId) {
        return executionsByStepExecutionId.get(stepExecutionId);
    }

    @Override
    public StepExecution getLastStepExecution(JobInstance jobInstance, String stepName) {
        StepExecution latest = null;
        for (StepExecution stepExecution : executionsByStepExecutionId.values()) {
            if (!stepExecution.getStepName().equals(stepName)
                    || stepExecution.getJobExecution().getJobInstance().getInstanceId() != jobInstance.getInstanceId()) {
                continue;
            }
            if (latest == null) {
                latest = stepExecution;
            }
            if (latest.getStartTime().getTime() < stepExecution.getStartTime().getTime()) {
                latest = stepExecution;
            }
            // Use step execution ID as the tie breaker if start time is identical
            if (latest.getStartTime().getTime() == stepExecution.getStartTime().getTime() &&
                    latest.getId() < stepExecution.getId()) {
                latest = stepExecution;
            }
        }
        return latest;
    }

    @Override
    public void addStepExecutions(JobExecution jobExecution) {
        Map<Long, StepExecution> executions = executionsByJobExecutionId.get(jobExecution.getId());
        if (executions == null || executions.isEmpty()) {
            return;
        }
        List<StepExecution> result = new ArrayList<>(executions.values());
        Collections.sort(result, new Comparator<Entity>() {

            @Override
            public int compare(Entity o1, Entity o2) {
                return Long.signum(o2.getId() - o1.getId());
            }
        });

        List<StepExecution> copy = new ArrayList<>(result.size());
        for (StepExecution exec : result) {
            copy.add(copy(exec));
        }
        jobExecution.addStepExecutions(copy);
    }

    @Override
    public void saveStepExecutions(Collection<StepExecution> stepExecutions) {
        Assert.notNull(stepExecutions, "Attempt to save an null collect of step executions");
        for (StepExecution stepExecution : stepExecutions) {
            saveStepExecution(stepExecution);
        }
    }

    public Map<Long, Map<Long, StepExecution>> getExecutionsByJobExecutionId() {
        return executionsByJobExecutionId;
    }

    public Map<Long, StepExecution> getExecutionsByStepExecutionId() {
        return executionsByStepExecutionId;
    }

    public void clearStepExecutionDao(JobExecution jobExecution) {
        // 清除executionsByJobExecutionId
        executionsByJobExecutionId.remove(jobExecution.getId());
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        if (CollUtil.isNotEmpty(stepExecutions)) {
            for (StepExecution stepExecution : stepExecutions) {
                executionsByStepExecutionId.remove(stepExecution.getId());
            }
        }
    }
}
