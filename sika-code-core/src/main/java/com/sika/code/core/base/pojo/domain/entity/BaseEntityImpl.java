package com.sika.code.core.base.pojo.domain.entity;


import cn.hutool.core.util.StrUtil;
import com.sika.code.core.log.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 基础领域接口-采取上下文的方式构建请求请求响应
 * </p>
 *
 * @author sikadai
 * @since 2021/4/4 21:26
 */
public abstract class BaseEntityImpl<Context> implements BaseEntity<Context> {
    /**
     * 获取当前领域对象的日志
     */
    protected Logger logger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    /**
     * 执行入口 -- 定义模板执行方式
     */
    @Override
    public void execute(Context context) {
        try {
            executeBefore(context);
            if (!needExecute(context)) {
                return;
            }
            doExecute(context);
            executeAfter(context);
        } catch (Exception e) {
            loggerException(context, e);
            executeException(context, e);
        } finally {
            executeFinally(context);
        }
    }

    protected boolean needExecute(Context context) {
        return true;
    }

    /**
     * 执行前
     */
    protected void executeBefore(Context context) {

    }

    /**
     * 执行者
     */
    protected abstract void doExecute(Context context);

    /**
     * 执行后
     */
    protected void executeAfter(Context context) {

    }

    protected void loggerException(Context context, Exception e) {
        LogUtil.error(StrUtil.format("领域对象【{}】执行异常", this.getClass().getName()), e, logger());
    }

    /**
     * 执行异常
     */
    protected void executeException(Context context, Exception e) {
        throw new RuntimeException(e);
    }

    /**
     * 执行最终
     */
    protected void executeFinally(Context context) {

    }
}
