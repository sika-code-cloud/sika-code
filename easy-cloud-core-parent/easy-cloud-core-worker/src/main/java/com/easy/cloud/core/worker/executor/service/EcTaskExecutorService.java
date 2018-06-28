package com.easy.cloud.core.worker.executor.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.worker.executor.pojo.query.EcTaskExecutorQuery;

import java.util.List;

/**
 * 描述：服务接口
 *
 * @author THINK
 * @date 2018-06-21 19:21:54
 */
public interface EcTaskExecutorService {
    /**
     * <p>
     * 根据任务数据获取任务执行者列表信息
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskExecutorQuery.jobGroup : jobGroup : 工作分组 : 是
     *     taskExecutorQuery.jobName : jobName : 工作名称 : 是
     *     taskExecutorQuery.triggerGroup : triggerGroup : 触发器分组 : 是
     *     taskExecutorQuery.triggerName : triggerName : 触发器名称 : 是
     * </pre>
     *
     * @param taskExecutorQuery
     * @return java.util.List<com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO>
     * @author daiqi
     * @date 2018/6/21 19:38
     */
    List<EcTaskExecutorDTO> listByTaskData(EcTaskExecutorQuery taskExecutorQuery);

    /**
     * <p>
     * 保存TaskExecutor信息
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskExecutorDTO.jobGroup : jobGroup : 工作分组 : 是
     *     taskExecutorDTO.jobName : jobName : 工作名称 : 是
     *     taskExecutorDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     *     taskExecutorDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskExecutorDTO.requestUrl : http:www.baidu.com : 请求的url : 是
     *     taskExecutorDTO.retryCount : 3 : 重试次数 : 否
     *     taskExecutorDTO.retryInterval : 10 : 重试间隔 单位秒 : 否
     *     taskExecutorDTO.requestMethod : post : 请求的方法统一使用post : 否
     *     taskExecutorDTO.requestBody : {"name": "zhangsan"} : 请求的主体内容[json对象字符串] : 否
     *     taskExecutorDTO.taskExecutorNo : 1213131 : 任务执行者编号 : 否
     *     taskExecutorDTO.jobCallbackClassName : com.easy.cloud.core.task.job.callback.impl.DefaultJobCallBackHandler : 异步回调执行handler : 否
     *     taskExecutorDTO.jobType : 1 : 工作类型 @see EcJobType常量类 : 否
     *     taskExecutorDTO.asyn : 1 : 是否为异步执行job 0同步 1为异步  默认异步: 否
     *     taskExecutorDTO.remark : erp系统充值任务 : 备注: 否
     *     taskExecutorDTO.systemType : 1 : 系统类型，可以根据配置表进行配置: 是
     * </pre>
     *
     * @param taskExecutorDTO
     * @return com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO
     * @author daiqi
     * @date 2018/6/21 19:49
     */
    EcTaskExecutorDTO saveTaskExecutor(EcTaskExecutorDTO taskExecutorDTO);

    /**
     * <p>
     * 更新TaskExecutor信息
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     taskExecutorDTO.jobGroup : jobGroup : 工作分组 : 是
     *     taskExecutorDTO.jobName : jobName : 工作名称 : 是
     *     taskExecutorDTO.triggerGroup : triggerGroup : 触发器分组 : 是
     *     taskExecutorDTO.triggerName : triggerName : 触发器名称 : 是
     *     taskExecutorDTO.requestUrl : http:www.baidu.com : 请求的url : 是
     *     taskExecutorDTO.retryCount : 3 : 重试次数 : 否
     *     taskExecutorDTO.retryInterval : 10 : 重试间隔 单位秒 : 否
     *     taskExecutorDTO.requestMethod : post : 请求的方法统一使用post : 否
     *     taskExecutorDTO.requestBody : {"name": "zhangsan"} : 请求的主体内容[json对象字符串] : 否
     *     taskExecutorDTO.taskExecutorNo : 1213131 : 任务执行者编号 : 否
     *     taskExecutorDTO.jobCallbackClassName : com.easy.cloud.core.task.job.callback.impl.DefaultJobCallBackHandler : 异步回调执行handler : 否
     *     taskExecutorDTO.jobType : 1 : 工作类型 @see EcJobType常量类 : 否
     *     taskExecutorDTO.asyn : 1 : 是否为异步执行job 0同步 1为异步  默认异步: 否
     *     taskExecutorDTO.remark : erp系统充值任务 : 备注: 否
     *     taskExecutorDTO.systemType : 1 : 系统类型，可以根据配置表进行配置: 是
     * </pre>
     *
     * @param taskExecutorDTO
     * @return com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO
     * @author daiqi
     * @date 2018/6/21 19:49
     */
    EcBaseServiceResult updateTaskExecutor(EcTaskExecutorDTO taskExecutorDTO);

}
