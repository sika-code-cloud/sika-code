package com.sika.code.batch.standard.context;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.sika.code.batch.standard.listener.StandChunkListener;
import com.sika.code.batch.standard.listener.StandardStepWriteListener;
import com.sika.code.core.util.BeanUtil;
import com.sika.code.batch.core.context.BaseBatchContext;
import com.sika.code.batch.core.factory.BatchFactory;
import com.sika.code.batch.core.item.writer.BaseWriterExecutor;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemProcessorBean;
import com.sika.code.batch.standard.bean.common.ItemReaderBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.processor.BaseProcessorBean;
import com.sika.code.batch.standard.bean.reader.BaseReaderBean;
import com.sika.code.batch.standard.bean.writer.BaseWriterBean;
import com.sika.code.batch.standard.item.processor.StandardProcessor;
import com.sika.code.batch.standard.item.reader.StandardReader;
import com.sika.code.batch.standard.listener.StandardJobExecutionListener;
import com.sika.code.batch.standard.listener.StandardStepExecutionListener;
import lombok.Data;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 13:41
 */
@Data
public class StandardContext extends BaseBatchContext {

    private BatchBean batchBean;
    private StandardParamContext standardParamContext;

    /**
     * 跳过的异常
     */
    private Class<? extends Exception> skipException;
    private Class<? extends Exception> noSkipException;
    /**
     * job相关类
     */
    private Job job;
    private StandardReader standardReader;
    private StandardProcessor standardProcessor;
    private BaseWriterExecutor<Map<String, Object>> writerExecutor;
    /**
     * 监听器对象
     */
    private List<JobExecutionListener> jobExecutionListeners;
    private List<StepExecutionListener> stepExecutionListeners;
    private List<ItemReadListener> itemReadListeners;
    private List<ItemProcessListener> itemProcessListeners;
    private List<ItemWriteListener> itemWriteListeners;
    private List<StandChunkListener> chunkListeners;

    public StandardContext() {

    }

    public StandardContext(BatchBean batchBean) {
        this.batchBean = batchBean;
    }

    public void buildStandardItem() {
        buildStandardParamContext();
        buildStandardReader();
        buildStandardProcessor();
        buildStandardWriter();
    }

    public void buildStandardReader() {
        ItemReaderBean<?> itemBean = batchBean.getItemReaderBean();
        BaseReaderBean readerBean = itemBean.buildBeanObj();
        ItemReader itemReader = BatchFactory.getItemReader(batchBean, readerBean.getBuilderClassName());
        StandardReader standardReader = new StandardReader();
        standardReader.setItemReader(itemReader);
        standardReader.setBatchBean(batchBean);

        // 校验chunk与pageSize是否满足相关条件
        verifyChunkForPageSize(readerBean);
        setStandardReader(standardReader);
    }

    protected void verifyChunkForPageSize(BaseReaderBean readerBean) {
        Assert.notNull(readerBean.getPageSize(), "读取的分页大小【pageSize】不能为空");
        Assert.notNull(batchBean.getChunk(), "每批写入【chunk】不能为空");
        Assert.isTrue(readerBean.getPageSize() > 0, "读取的分页大小【pageSize】必须大于0");
        Assert.isTrue(batchBean.getChunk() > readerBean.getPageSize(), "写入的【chunk】必须大于分页【pageSize】");
        Assert.isTrue(batchBean.getChunk() % readerBean.getPageSize() == 0, "每批写入【chunk】必须为读取【pageSize】的整数倍");
    }

    public void buildStandardProcessor() {
        ItemProcessorBean<?> itemBean = batchBean.getItemProcessorBean();
        BaseProcessorBean processorBean = itemBean.buildBeanObj();
        StandardProcessor itemProcessor = (StandardProcessor) BatchFactory.getItemProcessor(batchBean, processorBean.getBuilderClassName());
        itemProcessor.setBatchBean(batchBean);
        setStandardProcessor(itemProcessor);
    }

    public void buildStandardWriter() {
        List<ItemWriterBean<?>> itemBeans = batchBean.getItemWriterBeans();
        List<ItemWriter<Map<String, Object>>> itemWriters = Lists.newArrayList();
        for (ItemWriterBean<?> itemBean : itemBeans) {
            batchBean.setCurrentItemWriterBean(itemBean);
            BaseWriterBean baseWriterBean = itemBean.buildBeanObj();
            ItemWriter itemWriter = BatchFactory.getItemWriter(batchBean, baseWriterBean.getBuilderClassName());
            itemWriters.add(itemWriter);
        }
        BaseWriterExecutor<Map<String, Object>> writer = BatchFactory.getWriterExecutor(batchBean);
        writer.setBatchBean(batchBean);
        writer.setContext(standardParamContext);
        writer.setItemWriters(itemWriters);
        setWriterExecutor(writer);
    }

    protected void buildStandardParamContext() {
        this.standardParamContext = new StandardParamContext();
        this.standardParamContext.setBatchBean(batchBean);
        this.standardParamContext.build();
    }

    public void buildListeners() {
        buildJobExecutionListeners();
        buildStepExecutionListeners();
        buildItemListeners();
        buildChunkListeners();
    }

    private void buildChunkListeners() {
        if (chunkListeners == null) {
            chunkListeners = Lists.newArrayList();
            chunkListeners.add(new StandChunkListener(batchBean.getContextMap()));
        }
        if (CollUtil.isEmpty(batchBean.getChunkListenerClassNames())) {
            return;
        }
        for (String chunkListenerClassName : batchBean.getChunkListenerClassNames()) {
            StandChunkListener standChunkListener = BeanUtil.newInstance(chunkListenerClassName);
            standChunkListener.setContextMap(batchBean.getContextMap());
            chunkListeners.add(standChunkListener);
        }
    }

    public void buildJobExecutionListeners() {
        if (jobExecutionListeners == null) {
            jobExecutionListeners = Lists.newArrayList();
            jobExecutionListeners.add(new StandardJobExecutionListener());
        }
        if (CollUtil.isEmpty(batchBean.getJobExecutionListenerClassNames())) {
            return;
        }
        for (String jobExecutionListenerClassName : batchBean.getJobExecutionListenerClassNames()) {
            jobExecutionListeners.add(BeanUtil.newInstance(jobExecutionListenerClassName));
        }
    }

    public void buildStepExecutionListeners() {
        if (stepExecutionListeners == null) {
            stepExecutionListeners = Lists.newArrayList();
            StandardStepExecutionListener standardStepExecutionListener = new StandardStepExecutionListener();
            standardStepExecutionListener.setContextMap(batchBean.getContextMap());
            stepExecutionListeners.add(standardStepExecutionListener);
        }
        if (CollUtil.isEmpty(batchBean.getStepExecutionListenerClassNames())) {
            return;
        }
        for (String stepExecutionListenerClassName : batchBean.getStepExecutionListenerClassNames()) {
            stepExecutionListeners.add(BeanUtil.newInstance(stepExecutionListenerClassName));
        }
    }

    public void buildItemListeners() {
        buildItemReadListeners();
        buildItemProcessListeners();
        buildItemWriteListeners();
    }

    protected void buildItemReadListeners() {
        if (itemReadListeners == null) {
            itemReadListeners = Lists.newArrayList();
        }
        ItemReaderBean<?> itemBean = batchBean.getItemReaderBean();
        BaseReaderBean readerBean = itemBean.buildBeanObj();
        if (CollUtil.isEmpty(readerBean.getListenerClassNames())) {
            return;
        }
        for (String stepListenerClassName : readerBean.getListenerClassNames()) {
            itemReadListeners.add(BeanUtil.newInstance(stepListenerClassName));
        }
    }

    protected void buildItemProcessListeners() {
        if (itemProcessListeners == null) {
            itemProcessListeners = Lists.newArrayList();
        }
        ItemProcessorBean<?> itemBean = batchBean.getItemProcessorBean();
        BaseProcessorBean processorBean = itemBean.buildBeanObj();
        if (CollUtil.isEmpty(processorBean.getListenerClassNames())) {
            return;
        }
        for (String stepListenerClassName : processorBean.getListenerClassNames()) {
            itemProcessListeners.add(BeanUtil.newInstance(stepListenerClassName));
        }
    }

    protected void buildItemWriteListeners() {
        if (itemWriteListeners == null) {
            itemWriteListeners = Lists.newArrayList();
        }
        List<ItemWriterBean<?>> itemBeans = batchBean.getItemWriterBeans();
        for (ItemWriterBean<?> itemBean : itemBeans) {
            BaseWriterBean writerBean = itemBean.buildBeanObj();
            if (CollUtil.isEmpty(writerBean.getListenerClassNames())) {
                continue;
            }
            for (String writerListenerClassName : writerBean.getListenerClassNames()) {
                ItemWriteListener itemWriteListener = BeanUtil.newInstance(writerListenerClassName);
                if (itemWriteListener instanceof StandardStepWriteListener) {
                    ((StandardStepWriteListener) itemWriteListener).setContextMap(batchBean.getContextMap());
                }
                itemWriteListeners.add(itemWriteListener);
            }
        }
    }


    /**
     * 构建JOB
     */
    public Job buildJob() {
        Step step = buildStep();
        SimpleJobBuilder simpleJobBuilder = getJobBuilderFactory().get(batchBean.getJobName()).start(step);
        if (CollUtil.isNotEmpty(getJobExecutionListeners())) {
            for (JobExecutionListener listener : getJobExecutionListeners()) {
                simpleJobBuilder.listener(listener);
            }
        }
        return simpleJobBuilder.build();
    }

    protected JobBuilderFactory getJobBuilderFactory() {
        return BeanUtil.getBean(JobBuilderFactory.class);
    }

    protected StepBuilderFactory getStepBuilderFactory() {
        return BeanUtil.getBean(StepBuilderFactory.class);
    }

    /**
     * 构架step
     */
    protected Step buildStep() {
        SimpleStepBuilder<Map<String, Object>, Map<String, Object>> simpleStepBuilder = buildSimpleStepBuilder();
        FaultTolerantStepBuilder<Map<String, Object>, Map<String, Object>> faultTolerantStepBuilder = buildFaultTolerantStepBuilder(simpleStepBuilder);
        return faultTolerantStepBuilder.build();
    }

    protected SimpleStepBuilder<Map<String, Object>, Map<String, Object>> buildSimpleStepBuilder() {
        String stepName = batchBean.getStepName();
        return getStepBuilderFactory().get(stepName)
                .<Map<String, Object>, Map<String, Object>>chunk(batchBean.getChunk())
                // 给step绑定 reader
                .reader(getStandardReader())
                // 给step绑定 processor
                .processor(getStandardProcessor())
                // 给step绑定 writer
                .writer(getWriterExecutor());
    }

    protected FaultTolerantStepBuilder<Map<String, Object>, Map<String, Object>> buildFaultTolerantStepBuilder(SimpleStepBuilder<Map<String, Object>, Map<String, Object>> simpleStepBuilder) {
        FaultTolerantStepBuilder<Map<String, Object>, Map<String, Object>> faultTolerantStepBuilder = simpleStepBuilder.faultTolerant();
        if (batchBean.getSkipLimit() != null && batchBean.getSkipLimit() > 0) {
            faultTolerantStepBuilder.skipLimit(batchBean.getSkipLimit());
        }
        if (getSkipException() != null) {
            faultTolerantStepBuilder.skip(getSkipException());
        }
        if (getNoSkipException() != null) {
            faultTolerantStepBuilder.noSkip(getNoSkipException());
        }
        if (CollUtil.isNotEmpty(getStepExecutionListeners())) {
            for (StepExecutionListener stepExecutionListener : getStepExecutionListeners()) {
                faultTolerantStepBuilder.listener(stepExecutionListener);
            }
        }
        if (CollUtil.isNotEmpty(getItemReadListeners())) {
            for (ItemReadListener itemReadListener : getItemReadListeners()) {
                faultTolerantStepBuilder.listener(itemReadListener);
            }
        }
        if (CollUtil.isNotEmpty(getItemProcessListeners())) {
            for (ItemProcessListener itemProcessListener : getItemProcessListeners()) {
                faultTolerantStepBuilder.listener(itemProcessListener);
            }
        }
        if (CollUtil.isNotEmpty(getItemWriteListeners())) {
            for (ItemWriteListener itemWriteListener : getItemWriteListeners()) {
                faultTolerantStepBuilder.listener(itemWriteListener);
            }
        }
        if (CollUtil.isNotEmpty(getChunkListeners())) {
            for (ChunkListener chunkListener : getChunkListeners()) {
                faultTolerantStepBuilder.listener(chunkListener);
            }
        }
        return faultTolerantStepBuilder;
    }

}
