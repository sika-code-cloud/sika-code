package com.sika.code.batch.standard.context;

import com.sika.code.core.base.constant.BaseConstant;
import com.sika.code.batch.core.context.BaseBatchContext;
import com.sika.code.batch.standard.bean.common.BatchBean;
import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 20:38
 */
@Data
public class StandardParamContext extends BaseBatchContext {
    private BatchBean batchBean;
    private ExecutorService writeExecutorService;

    protected void build() {
        if (isAsynWrite()) {
            setWriteExecutorService(Executors.newFixedThreadPool(batchBean.getItemWriterBeans().size()));
        }
    }

    public boolean isAsynWrite() {
        return BaseConstant.BooleanEnum.YES.getType().equals(this.batchBean.getAsynWrite());
    }

}
