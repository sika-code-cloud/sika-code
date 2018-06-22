package com.easy.cloud.core.task.job;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.task.common.constant.EcTaskConstant.EcJobType;
import com.easy.cloud.core.task.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.task.executor.pojo.query.EcTaskExecutorQuery;
import com.easy.cloud.core.task.executor.service.EcTaskExecutorService;
import com.easy.cloud.core.task.job.impl.EcMqJob;
import com.easy.cloud.core.task.job.impl.EcRedisJob;
import com.easy.cloud.core.task.job.impl.EcRestJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 核心job类
 *
 * @author daiqi
 * @create 2018-06-22 9:57
 */
public class EcCoreJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EcTaskExecutorService taskExecutorService;
    @Autowired
    private EcMqJob mqJob;
    @Autowired
    private EcRedisJob redisJob;
    @Autowired
    private EcRestJob restJob;

    @Override
    protected void executeInternal(JobExecutionContext executionContext) throws JobExecutionException {
        EcTaskExecutorQuery taskExecutorQuery = new EcTaskExecutorQuery();
        JobDetail jobDetail = executionContext.getJobDetail();
        taskExecutorQuery.setJobGroup(jobDetail.getKey().getGroup());
        taskExecutorQuery.setJobName(jobDetail.getKey().getName());

        Trigger trigger = executionContext.getTrigger();
        taskExecutorQuery.setTriggerGroup(trigger.getKey().getGroup());
        taskExecutorQuery.setTriggerName(trigger.getKey().getName());

        List<EcTaskExecutorDTO> taskExecutorDTOS = taskExecutorService.listByTaskData(taskExecutorQuery);
        logger.info("\r\n");
        logger.info("任务执行者的数据列表为：" + EcJSONUtils.toJSONString(taskExecutorDTOS));
        logger.info("执行任务的触发器信息为：" + executionContext.getTrigger().getKey());
        for (EcTaskExecutorDTO taskExecutorDTO : taskExecutorDTOS) {
            selectJobByType(taskExecutorDTO).execute(executionContext, taskExecutorDTO);
        }
    }

    /**
     * <p>
     * 根据类型选择job
     * </p>
     *
     * @param taskExecutorDTO
     * @return com.easy.cloud.core.task.job.EcBaseJob
     * @author daiqi
     * @date 2018/6/22 11:33
     */
    private EcBaseJob selectJobByType(EcTaskExecutorDTO taskExecutorDTO) {
        EcBaseJob baseJob = null;
        Integer jobType = taskExecutorDTO.getJobType();
        if (EcBaseUtils.equals(jobType, EcJobType.REST)) {
            baseJob = restJob;
        } else if (EcBaseUtils.equals(jobType, EcJobType.MQ)) {
            baseJob = mqJob;
        } else if (EcBaseUtils.equals(jobType, EcJobType.REDIS)) {
            baseJob = redisJob;
        } else {
            EcAssert.verifyDataNotExistent(null, "Job对象");
        }
        return baseJob;
    }
}
