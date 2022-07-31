package com.sika.code.batch.standard.store;

import cn.hutool.core.util.StrUtil;
import com.sika.code.batch.standard.store.bean.StandardJobInstance;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

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
public class StandardMapJobInstanceDao implements JobInstanceDao {
    private static final String STAR_WILDCARD = "\\*";
    private static final String STAR_WILDCARD_PATTERN = ".*";

    // JDK6 Make a ConcurrentSkipListSet: tends to add on end
    private final Map<String, StandardJobInstance> jobInstances = new ConcurrentHashMap<>();

    private JobKeyGenerator<JobParameters> jobKeyGenerator = new DefaultJobKeyGenerator();

    private final AtomicLong currentId = new AtomicLong(0L);

    public void clear() {
        jobInstances.clear();
    }

    @Override
    public JobInstance createJobInstance(String jobName, JobParameters jobParameters) {

        Assert.state(getJobInstance(jobName, jobParameters) == null, "JobInstance must not already exist");
        String instanceKey = StrUtil.join("|", jobName, jobKeyGenerator.generateKey(jobParameters));
        StandardJobInstance jobInstance = new StandardJobInstance(currentId.getAndIncrement(), jobName, instanceKey);
        jobInstance.incrementVersion();
        jobInstances.put(instanceKey, jobInstance);
        return jobInstance;
    }

    @Override
    @Nullable
    public JobInstance getJobInstance(String jobName, JobParameters jobParameters) {
        return jobInstances.get(jobName + "|" + jobKeyGenerator.generateKey(jobParameters));
    }

    @Override
    @Nullable
    public JobInstance getJobInstance(@Nullable Long instanceId) {
        for (Map.Entry<String, StandardJobInstance> instanceEntry : jobInstances.entrySet()) {
            JobInstance instance = instanceEntry.getValue();
            if (instance.getId().equals(instanceId)) {
                return instance;
            }
        }
        return null;
    }

    @Override
    public List<String> getJobNames() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, StandardJobInstance> instanceEntry : jobInstances.entrySet()) {
            result.add(instanceEntry.getValue().getJobName());
        }
        Collections.sort(result);
        return result;
    }

    @Override
    public List<JobInstance> getJobInstances(String jobName, int start, int count) {
        List<JobInstance> result = new ArrayList<>();
        for (Map.Entry<String, StandardJobInstance> instanceEntry : jobInstances.entrySet()) {
            JobInstance instance = instanceEntry.getValue();
            if (instance.getJobName().equals(jobName)) {
                result.add(instance);
            }
        }

        sortDescending(result);

        return subset(result, start, count);
    }

    @Override
    @Nullable
    public JobInstance getLastJobInstance(String jobName) {
        List<JobInstance> jobInstances = getJobInstances(jobName, 0, 1);
        return jobInstances.isEmpty() ? null : jobInstances.get(0);
    }

    @Override
    @Nullable
    public JobInstance getJobInstance(JobExecution jobExecution) {
        return jobExecution.getJobInstance();
    }

    @Override
    public int getJobInstanceCount(@Nullable String jobName) throws NoSuchJobException {
        int count = 0;

        for (Map.Entry<String, StandardJobInstance> instanceEntry : jobInstances.entrySet()) {
            String key = instanceEntry.getKey();
            String curJobName = key.substring(0, key.lastIndexOf("|"));

            if (curJobName.equals(jobName)) {
                count++;
            }
        }

        if (count == 0) {
            throw new NoSuchJobException("No job instances for job name " + jobName + " were found");
        } else {
            return count;
        }
    }

    @Override
    public List<JobInstance> findJobInstancesByName(String jobName, int start, int count) {
        List<JobInstance> result = new ArrayList<>();
        String convertedJobName = jobName.replaceAll(STAR_WILDCARD, STAR_WILDCARD_PATTERN);

        for (Map.Entry<String, StandardJobInstance> instanceEntry : jobInstances.entrySet()) {
            JobInstance instance = instanceEntry.getValue();

            if (instance.getJobName().matches(convertedJobName)) {
                result.add(instance);
            }
        }

        sortDescending(result);

        return subset(result, start, count);
    }

    private void sortDescending(List<JobInstance> result) {
        Collections.sort(result, new Comparator<JobInstance>() {
            @Override
            public int compare(JobInstance o1, JobInstance o2) {
                return Long.signum(o2.getId() - o1.getId());
            }
        });
    }

    private List<JobInstance> subset(List<JobInstance> jobInstances, int start, int count) {
        int startIndex = Math.min(start, jobInstances.size());
        int endIndex = Math.min(start + count, jobInstances.size());

        return jobInstances.subList(startIndex, endIndex);
    }

    public Map<String, StandardJobInstance> getJobInstances() {
        return jobInstances;
    }

    public void clearJobInstance(JobExecution jobExecution) {
        JobInstance jobInstance = jobExecution.getJobInstance();
        if (jobInstance instanceof StandardJobInstance) {
            clearStandardJobInstance((StandardJobInstance) jobInstance);
        } else {
            clearJobInstanceOld(jobInstance);
        }
    }

    private void clearStandardJobInstance(StandardJobInstance jobInstance) {
        jobInstances.remove(jobInstance.getInstanceKey());
    }

    private void clearJobInstanceOld(JobInstance jobInstance) {
        String jobInstanceKey = null;
        for (Map.Entry<String, StandardJobInstance> instanceEntry : jobInstances.entrySet()) {
            JobInstance instance = instanceEntry.getValue();
            if (instance.getId().equals(jobInstance.getId())) {
                jobInstanceKey = instanceEntry.getKey();
            }
        }
        if (jobInstanceKey != null) {
            jobInstances.remove(jobInstanceKey);
        }
    }
}
