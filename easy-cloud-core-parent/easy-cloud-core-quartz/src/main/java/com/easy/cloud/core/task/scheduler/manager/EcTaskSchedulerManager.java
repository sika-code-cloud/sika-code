package com.easy.cloud.core.task.scheduler.manager;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.task.scheduler.pojo.dto.EcHolidayDTO;
import com.easy.cloud.core.task.scheduler.pojo.dto.EcTaskSchedulerDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.calendar.AnnualCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <p>
 * 任务调度管理类
 * </p>
 *
 * @author daiqi
 * @date 2018/6/20 11:32
 */
@Component
public class EcTaskSchedulerManager {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private Scheduler scheduler;

    /**
     * <p>
     * 初始化任务调度
     * </p>
     *
     * @param taskSchedulerDTO
     * @param quartzJobClass
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:33
     */
    public void initTask(EcTaskSchedulerDTO taskSchedulerDTO, Class quartzJobClass) {
        logger.info("初始化任务调度");
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(taskSchedulerDTO.getTriggerName(), taskSchedulerDTO.getTriggerGroup());
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                addTaskToScheduler(taskSchedulerDTO, quartzJobClass);
            }
        } catch (Exception e) {
            logger.error("初始化任务调度异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 向任务调度中添加定时任务
     * </p>
     *
     * @param taskSchedulerDTO
     * @param jobClass
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:39
     */
    private void addTaskToScheduler(EcTaskSchedulerDTO taskSchedulerDTO, Class jobClass) {
        logger.info("向任务调度中添加定时任务");
        try {

            JobKey jobKey = JobKey.jobKey(taskSchedulerDTO.getJobName(), taskSchedulerDTO.getJobGroup());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            TriggerKey triggerKey = TriggerKey.triggerKey(taskSchedulerDTO.getTriggerName(), taskSchedulerDTO.getTriggerGroup());
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskSchedulerDTO.getCronExpression());
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing())
                        .withDescription(taskSchedulerDTO.getDescription())
                        .build();
                trigger = buildCalendar(taskSchedulerDTO, trigger);
                if (jobDetail != null) {
                    trigger = trigger.getTriggerBuilder().forJob(jobDetail).build();
                    scheduler.scheduleJob(trigger);
                } else {
                    jobDetail = JobBuilder.newJob(jobClass)
                            .withIdentity(taskSchedulerDTO.getJobName(), taskSchedulerDTO.getJobGroup())
                            .build();
                    scheduler.scheduleJob(jobDetail, trigger);
                }
            }
        } catch (Exception e) {
            logger.error("向任务调度中添加定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 立即运行定时任务
     * </p>
     *
     * @param taskSchedulerDTO
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:39
     */
    public void runJob(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("立即运行任务调度中的定时任务");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("定时任务信息为空，无法立即运行");
                return;
            }
            JobKey jobKey = JobKey.jobKey(taskSchedulerDTO.getJobName(), taskSchedulerDTO.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + taskSchedulerDTO.getJobName() + "]定时任务，不予立即运行！");
                return;
            }
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            logger.error("立即运行任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 修改任务调度中的定时任务
     * </p>
     *
     * @param taskSchedulerDTO : 定时任务信息
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:40
     */
    public void updateTaskScheduler(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("修改任务调度中的定时任务");
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(taskSchedulerDTO.getTriggerName(), taskSchedulerDTO.getTriggerGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == taskSchedulerDTO || EcStringUtils.isEmpty(taskSchedulerDTO.getCronExpression())) {
                logger.info("修改调度任务参数不正常！");
                return;
            }
            logger.info("原始任务表达式:" + trigger.getCronExpression()
                    + "，现在任务表达式:" + taskSchedulerDTO.getCronExpression());
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskSchedulerDTO.getCronExpression());
            Trigger triggerUpdate = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withDescription(taskSchedulerDTO.getDescription())
                    .withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing())
                    .build();
            triggerUpdate = buildCalendar(taskSchedulerDTO, triggerUpdate);
            scheduler.rescheduleJob(triggerKey, triggerUpdate);
        } catch (Exception e) {
            logger.error("修改任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    private Trigger buildCalendar(final EcTaskSchedulerDTO taskSchedulerDTO, final Trigger trigger) throws SchedulerException {
        logger.info("holidayName：" + taskSchedulerDTO.getHolidayName());
        logger.info("holidays：" + taskSchedulerDTO.getHolidayDTOs());
        if (taskSchedulerDTO.getHolidayName() != null && EcCollectionsUtils.isNotEmpty(taskSchedulerDTO.getHolidayDTOs())) {
            AnnualCalendar holidays = new AnnualCalendar();
            for (EcHolidayDTO holidayDTO : taskSchedulerDTO.getHolidayDTOs()) {
                Calendar calendar = new GregorianCalendar(holidayDTO.getYear(), holidayDTO.getMonth() - 1, holidayDTO.getDay());
                holidays.setDayExcluded(calendar, true);
            }
            scheduler.addCalendar(taskSchedulerDTO.getHolidayName(), holidays, true, true);
            return trigger.getTriggerBuilder().modifiedByCalendar(taskSchedulerDTO.getHolidayName()).build();
        } else {
            return trigger.getTriggerBuilder().modifiedByCalendar(null).build();
        }
//        return trigger;
    }

    /**
     * <p>
     * 暂停任务调度中的触发器
     * </p>
     *
     * @param taskSchedulerDTO : 定时任务信息
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:42
     */
    public void pauseTaskTrigger(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("暂停任务调度中的触发器");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("暂停调度任务参数不正常！");
                return;
            }
            logger.info("暂停任务调度中的触发器triggerKey：" + taskSchedulerDTO.getTriggerKey());

            TriggerKey triggerKey = TriggerKey.triggerKey(taskSchedulerDTO.getTriggerName(), taskSchedulerDTO.getTriggerGroup());
            if (null == triggerKey) {
                logger.info("任务调度中不存在[" + triggerKey.getGroup() + "." + triggerKey.getName() + "]触发器，不予进行暂停！");
                return;
            }
            scheduler.pauseTrigger(triggerKey);
        } catch (Exception e) {
            logger.error("暂停任务调度中的触发器异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 恢复任务调度中的触发器
     * </p>
     *
     * @param taskSchedulerDTO : 定时任务信息
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:43
     */
    public void resumeTaskTrigger(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("恢复任务调度中的定时触发器: ");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("恢复调度任务触发器参数不正常！");
                return;
            }
            logger.info("恢复任务调度中的定时触发器triggerKey：" + taskSchedulerDTO.getTriggerKey());
            TriggerKey triggerKey = TriggerKey.triggerKey(taskSchedulerDTO.getTriggerName(), taskSchedulerDTO.getTriggerGroup());
            if (null == triggerKey) {
                logger.info("任务调度中不存在[" + taskSchedulerDTO.getTriggerKey() + "]触发器，不予进行重启！");
                return;
            }
            scheduler.resumeTrigger(triggerKey);
        } catch (Exception e) {
            logger.error("恢复任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 暂停任务调度中的定时任务
     * </p>
     *
     * @param taskSchedulerDTO : 定时任务信息
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:42
     */
    public void pauseJob(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("暂停任务调度中的定时任务");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("暂停调度任务参数不正常！");
                return;
            }
            JobKey jobKey = JobKey.jobKey(taskSchedulerDTO.getJobName(), taskSchedulerDTO.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + taskSchedulerDTO.getJobName() + "]定时任务，不予进行暂停！");
                return;
            }
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            logger.error("暂停任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 恢复任务调度中的定时任务
     * </p>
     *
     * @param taskSchedulerDTO : 定时任务信息
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:43
     */
    public void resumeJob(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("恢复任务调度中的定时任务");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("恢复调度任务参数不正常！");
                return;
            }
            JobKey jobKey = JobKey.jobKey(taskSchedulerDTO.getJobName(), taskSchedulerDTO.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + taskSchedulerDTO.getJobName() + "]定时任务，不予进行恢复！");
                return;
            }
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            logger.error("恢复任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 删除任务调度中的定时任务
     * </p>
     *
     * @param taskSchedulerDTO : 定时任务信息
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:43
     */
    public void deleteJob(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("删除任务调度中的定时任务");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("删除调度任务参数不正常！");
                return;
            }
            JobKey jobKey = JobKey.jobKey(taskSchedulerDTO.getJobName(), taskSchedulerDTO.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + taskSchedulerDTO.getJobName() + "]定时任务，不予进行删除！");
                return;
            }
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            logger.error("删除任务调度中的定时任务异常！" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * 删除任务调度定时器
     * </p>
     *
     * @return void
     * @author daiqi
     * @date 2018/6/20 11:44
     */
    public void deleteTrigger(EcTaskSchedulerDTO taskSchedulerDTO) {
        logger.info("删除任务调度定时器");
        try {
            if (null == taskSchedulerDTO) {
                logger.info("停止任务定时器触发器参数不正常，不予进行停止！");
                return;
            }
            TriggerKey triggerKey = new TriggerKey(taskSchedulerDTO.getTriggerName(), taskSchedulerDTO.getTriggerGroup());
            logger.info("停止触发器");
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
        } catch (Exception e) {
            logger.info("删除任务调度定时器异常！" + e.getMessage(), e);
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
