package com.sika.code.batch.standard.store;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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
public class StandardMapJobExecutionDao implements JobExecutionDao {

    // JDK6 Make this into a ConcurrentSkipListMap: adds and removes tend to be very near the front or back
    private final ConcurrentMap<Long, JobExecution> executionsById = new ConcurrentHashMap<>();

    private final AtomicLong currentId = new AtomicLong(0L);

    public void clear() {
        executionsById.clear();
    }

    public void clearJobExecution(JobExecution jobExecution) {
        executionsById.remove(jobExecution.getId());
    }

    private static JobExecution copy(JobExecution original) {
        JobExecution copy = (JobExecution) SerializationUtils.deserialize(SerializationUtils.serialize(original));
        return copy;
    }

    @Override
    public void saveJobExecution(JobExecution jobExecution) {
        Assert.isTrue(jobExecution.getId() == null, "jobExecution id is not null");
        Long newId = currentId.getAndIncrement();
        jobExecution.setId(newId);
        jobExecution.incrementVersion();
        executionsById.put(newId, copy(jobExecution));
    }

    @Override
    public List<JobExecution> findJobExecutions(JobInstance jobInstance) {
        List<JobExecution> executions = new ArrayList<>();
        for (JobExecution exec : executionsById.values()) {
            if (exec.getJobInstance().equals(jobInstance)) {
                executions.add(copy(exec));
            }
        }
        Collections.sort(executions, new Comparator<JobExecution>() {

            @Override
            public int compare(JobExecution e1, JobExecution e2) {
                long result = (e1.getId() - e2.getId());
                if (result > 0) {
                    return -1;
                }
                else if (result < 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        return executions;
    }

    @Override
    public void updateJobExecution(JobExecution jobExecution) {
        Long id = jobExecution.getId();
        Assert.notNull(id, "JobExecution is expected to have an id (should be saved already)");
        JobExecution persistedExecution = executionsById.get(id);
        if (persistedExecution == null) {
            return;
        }
        Assert.notNull(persistedExecution, "JobExecution must already be saved");

        synchronized (jobExecution) {
            if (!persistedExecution.getVersion().equals(jobExecution.getVersion())) {
                throw new OptimisticLockingFailureException("Attempt to update job execution id=" + id
                        + " with wrong version (" + jobExecution.getVersion() + "), where current version is "
                        + persistedExecution.getVersion());
            }
            jobExecution.incrementVersion();
            executionsById.put(id, copy(jobExecution));
        }
    }

    @Nullable
    @Override
    public JobExecution getLastJobExecution(@Nullable JobInstance jobInstance) {
        JobExecution lastExec = null;
        for (JobExecution exec : executionsById.values()) {
            if (!exec.getJobInstance().equals(jobInstance)) {
                continue;
            }
            if (lastExec == null) {
                lastExec = exec;
            }
            if (lastExec.getCreateTime().before(exec.getCreateTime())) {
                lastExec = exec;
            }
        }
        return copy(lastExec);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.batch.core.repository.dao.JobExecutionDao#
     * findRunningJobExecutions(java.lang.String)
     */
    @Override
    public Set<JobExecution> findRunningJobExecutions(String jobName) {
        Set<JobExecution> result = new HashSet<>();
        for (JobExecution exec : executionsById.values()) {
            if (!exec.getJobInstance().getJobName().equals(jobName) || !exec.isRunning()) {
                continue;
            }
            result.add(copy(exec));
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.batch.core.repository.dao.JobExecutionDao#getJobExecution
     * (java.lang.Long)
     */
    @Override
    @Nullable
    public JobExecution getJobExecution(Long executionId) {
        return copy(executionsById.get(executionId));
    }

    @Override
    public void synchronizeStatus(JobExecution jobExecution) {
        JobExecution saved = getJobExecution(jobExecution.getId());
        if (saved != null && saved.getVersion().intValue() != jobExecution.getVersion().intValue()) {
            jobExecution.upgradeStatus(saved.getStatus());
            jobExecution.setVersion(saved.getVersion());
        }
    }

    public ConcurrentMap<Long, JobExecution> getExecutionsById() {
        return executionsById;
    }
}
