package com.easy.cloud.core.task.scheduler.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.task.scheduler.pojo.dto.EcTaskSchedulerDTO;

/**
 * @author daiqi
 * @create 2018-06-20 19:55
 */
public interface EcTaskSchedulerService {
    /**
     * <p>
     * 初始化任务
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskSchedulerDTO.jobName : jobName : 工作名称 : 是
     *     taskSchedulerDTO.jobGroup : jobGroup : 工作分组 : 是
     *     taskSchedulerDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskSchedulerDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     *     taskSchedulerDTO.jobType : 1 : 触发器分组 : 是
     *     taskSchedulerDTO.cronExpression : 0/5 * * * * ?  : cron表达式 : 是
     *
     *     taskSchedulerDTO.taskExecutor.requestUrl : http:www.baidu.com : 请求的url : 是
     *     taskSchedulerDTO.taskExecutor.retryCount : 3 : 重试次数 : 否
     *     taskSchedulerDTO.taskExecutor.retryInterval : 10 : 重试间隔 单位秒 : 否
     *     taskSchedulerDTO.taskExecutor.requestMethod : post : 请求的方法统一使用post : 否
     *     taskSchedulerDTO.taskExecutor.requestBody : {"name": "zhangsan"} : 请求的主体内容[json对象字符串] : 否
     *     taskSchedulerDTO.taskExecutor.taskExecutorNo : 1213131 : 任务执行者编号 : 否
     *     taskSchedulerDTO.taskExecutor.jobCallbackClassName : com.easy.cloud.core.task.job.callback.impl.EcDefaultJobCallBackHandler : 异步回调执行handler : 否
     *     taskSchedulerDTO.taskExecutor.jobType : 1 : 工作类型 @see EcJobType常量类 : 否
     *     taskSchedulerDTO.taskExecutor.asyn : 1 : 是否为异步执行job 0同步 1为异步  默认异步: 否
     * </pre>
     *
     * @param taskSchedulerDTO
     * @return void
     * @author daiqi
     * @date 2018/6/21 11:30
     */
    EcBaseServiceResult initTask(EcTaskSchedulerDTO taskSchedulerDTO);

    /**
     * <p>
     * 更新任务调度器
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskSchedulerDTO.jobName : jobName : 工作名称 : 是
     *     taskSchedulerDTO.jobGroup : jobGroup : 工作分组 : 是
     *     taskSchedulerDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskSchedulerDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     *     taskSchedulerDTO.jobType : 1 : 触发器分组 : 是
     *     taskSchedulerDTO.cronExpression : 0/5 * * * * ?  : cron表达式 : 是
     * </pre>
     *
     * @param taskSchedulerDTO
     * @return void
     * @author daiqi
     * @date 2018/6/21 11:30
     */
    EcBaseServiceResult updateTaskScheduler(EcTaskSchedulerDTO taskSchedulerDTO);
    
    /**  
     * <p>
     *  暂停任务触发器
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskSchedulerDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskSchedulerDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     * </pre>
     * @author daiqi  
     * @date 2018/6/21 16:46
     * @param taskSchedulerDTO  
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult  
     */  
    EcBaseServiceResult pauseTaskTrigger(EcTaskSchedulerDTO taskSchedulerDTO);

    /**
     * <p>
     *  重启任务触发器
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskSchedulerDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskSchedulerDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     * </pre>
     * @author daiqi
     * @date 2018/6/21 16:46
     * @param taskSchedulerDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     */
    EcBaseServiceResult resumeTaskTrigger(EcTaskSchedulerDTO taskSchedulerDTO);

    /**
     * <p>
     *  删除任务触发器
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskSchedulerDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskSchedulerDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     * </pre>
     * @author daiqi
     * @date 2018/6/21 16:46
     * @param taskSchedulerDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     */
    EcBaseServiceResult deleteTrigger(EcTaskSchedulerDTO taskSchedulerDTO);
}
