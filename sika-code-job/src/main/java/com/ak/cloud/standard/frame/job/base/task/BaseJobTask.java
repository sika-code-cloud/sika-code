package com.ak.cloud.standard.frame.job.base.task;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.ak.cloud.standard.frame.job.base.context.BaseJobContext;
import com.ak.cloud.standard.frame.job.base.executor.BaseJobExecutor;
import com.sika.code.core.base.pojo.domain.entity.BaseEntityImpl;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.core.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * <pre>
 *  基础计价JOB
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/5/11 15:32
 */
public abstract class BaseJobTask<Context extends BaseJobContext> extends BaseEntityImpl<String> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doExecute(String param) {
        // 1. 根据JOB参数构建上下文对象
        Context context = buildContext(param);
        if (!needDistributedLock(context)) {
            return;
        }
        try {
            executeBody(context);
        } finally {
            finallyDoExecute(context);
        }
    }

    protected void executeBody(Context context) {
        // 2. 获取执行器
        BaseJobExecutor<Context> executor = getExecutor(context);
        // 3. 调用执行器-执行业务逻辑
        executor.execute(context);
    }

    protected boolean needDistributedLock(Context context) {
        String redisKey = buildRedisLockKey(context);
        if (CharSequenceUtil.isBlank(redisKey)) {
            return true;
        }
        // 执行加锁逻辑
        Redisson redisson = BeanUtil.getBean(Redisson.class);
        RLock lock = redisson.getLock(redisKey);
        return lock.tryLock();
    }

    protected void finallyDoExecute(Context context) {
        String redisKey = buildRedisLockKey(context);
        if (StringUtils.isBlank(redisKey)) {
            return;
        }
        // 释放redis锁
        Redisson redisson = BeanUtil.getBean(Redisson.class);
        RLock lock = redisson.getLock(redisKey);
        if (lock != null) {
            lock.unlock();
        }
    }

    protected String buildRedisLockKey(Context context) {
        String customKey = buildCustomRedisLockKey(context);
        if (CharSequenceUtil.isBlank(customKey)) {
            return null;
        }
        String commonKey = redisLockKeyPrefix() + this.getContextClass().getName();
        return CharSequenceUtil.join(StrPool.COLON, commonKey, customKey);
    }

    protected String redisLockKeyPrefix() {
        return StrUtil.EMPTY;
    }

    /**
     * 构建自定义的redisKey
     */
    protected String buildCustomRedisLockKey(Context context) {
        return null;
    }

    protected Context buildContext(String param) {
        Class<Context> contextClass = getContextClass();
        Context context;
        if (CharSequenceUtil.isBlank(param)) {
            context = getContextForEmpty(contextClass);
        } else {
            context = getContextFromParam(param, contextClass);
        }
        logger().info("【{}】通过入参构建的上下文对象为：{}", getClass().getName(), JSONUtil.toJSONString(context));
        // 构建上下文自定义的参数
        buildContextCustomer(context);
        return context;
    }

    protected void buildContextCustomer(Context context) {
        if (context.getRecordDate() == null) {
            context.setRecordDate(LocalDate.now());
        }
    }

    protected Context getContextFromParam(String param, Class<Context> contextClass) {
        try {
            return JSONUtil.parseObject(param, contextClass);
        } catch (Exception e) {
            logger().error("从参数转化为上下文对象有误-请合适参数是否不符合JSON格式", e);
            return getContextForEmpty(contextClass);
        }
    }

    /**
     * <p>
     * 构建空的上下文对象
     * </p >
     *
     * @param contextClass : 上下文对象的Class
     * @return Context
     * @author sikadai
     * @since 2022/5/12 16:27
     */
    protected Context getContextForEmpty(Class<Context> contextClass) {
        return ReflectUtil.newInstance(contextClass);
    }

    protected <Executor extends BaseJobExecutor<Context>> Executor createExecutor(Class<Executor> executorClass) {
        return BeanUtil.getBean(executorClass);
    }

    /**
     * <p>
     * 从容器中获取执行器的Bean
     * </p >
     *
     * @param executorClass
     * @return Executor
     * @author sikadai
     * @since 2022/5/19 14:36
     */
    protected <Executor extends BaseJobExecutor<Context>> Executor getExecutorBean(Class<Executor> executorClass) {
        return BeanUtil.getBean(executorClass);
    }

    /**
     * <p>
     * 获取上下问的class-由子类实现
     * </p >
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @return java.lang.Class<Context>
     * @author sikadai
     * @since 2022/5/12 16:08
     */
    protected abstract Class<Context> getContextClass();

    /**
     * <p>
     * 获取执行器
     * </p >
     *
     * @param context - 贯穿逻辑的上下文对象
     * @return com.asinking.cost.pricing.server.job.base.executor.BaseJobExecutor<Context>
     * @author sikadai
     * @since 2022/5/12 16:10
     */
    protected abstract BaseJobExecutor<Context> getExecutor(Context context);
}
