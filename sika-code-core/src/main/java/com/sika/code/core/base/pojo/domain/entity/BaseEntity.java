package com.sika.code.core.base.pojo.domain.entity;


import com.sika.code.core.base.pojo.context.BaseContext;

/**
 * <p>
 * 基础领域接口-采取上下文的方式构建请求请求响应
 * </p>
 *
 * @author sikadai
 * @since 2021/4/4 21:26
 */
public interface BaseEntity<Context extends BaseContext> {

    /**
     * 执行入口 -- 定义模板执行方式
     */
    void execute(Context context);
}
