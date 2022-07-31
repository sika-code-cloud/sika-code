package com.sika.code.batch.standard.entity;

import com.sika.code.batch.core.entity.BaseBatchEntity;
import com.sika.code.batch.standard.command.BatchCommander;
import com.sika.code.batch.standard.context.StandardContext;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  标准批次实体
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 13:42
 */
@Component
public class StandardBatchEntity extends BaseBatchEntity<StandardContext> {
    @Autowired
    protected BatchCommander batchCommander;

    @Override
    protected void executeBefore(StandardContext context) {
        super.executeBefore(context);
        // 根据配置进行初始化Writer、reader、processor
        buildStandardItem(context);
        //构建监听器
        buildListener(context);
    }

    @Override
    protected void doExecute(StandardContext context) {
        Job job = context.buildJob();
        // 执行一个批处理任务
        batchCommander.execute(job);
    }

    protected void buildStandardItem(StandardContext context) {
        context.buildStandardItem();
    }

    protected void buildListener(StandardContext context) {
        context.buildListeners();
    }

}
