package com.sika.code.core.base.pojo.domain.entity;

import com.sika.code.core.base.pojo.context.BaseContext;

/**
 * @author sikadai
 * @Description:基础领域实体抽象类
 * @date 2021/4/421:26
 */
public abstract class BaseStandardEntity<Context extends BaseContext> extends BaseEntityImpl<Context> {

    /**
     * 执行最终
     */
    @Override
    protected void executeFinally(Context context) {
        context.clear();
    }

}
