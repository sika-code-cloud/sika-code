package com.sika.code.batch.dto;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.listener.step.*;
import com.sika.code.batch.listener.step.base.StepRetryProcessListener;
import com.sika.code.batch.listener.step.base.StepRetryReadListener;
import com.sika.code.batch.listener.step.base.StepRetryWriteListener;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.listener.*;

import javax.batch.api.chunk.listener.RetryProcessListener;
import javax.batch.api.chunk.listener.RetryReadListener;
import javax.batch.api.chunk.listener.RetryWriteListener;

/**
 * step监听器数据
 *
 * @author daiqi
 * @create 2019-10-04 21:31
 */
@Data
@Accessors(chain = true)
@Slf4j
public class StepListenerData<I, O> {

    /**
     * step的执行监听器 --- step执行时将会触发
     */
    private StepExecutionListener stepExecutionListener;
    /**
     * chunk的监听器 --- 批处理时将会触发
     */
    private ChunkListener chunkListener;
    /**
     * 读取item监听器 --- 读书item时将会触发
     */
    private ItemReadListener<I> itemReadListener;
    /**
     * 处理item监听器 --- 处理item的时将会触发
     */
    private ItemProcessListener<I, O> itemProcessListener;
    /**
     * 写入item监听器 --- 发生写入时将会触发
     */
    private ItemWriteListener<O> itemWriteListener;
    /**
     * 跳过的监听器 --- 若发生跳过将会触发
     */
    private SkipListener<I, O> skipListener;
    /**
     * 重试读取的监听器 --- 重试读的时候将会触发
     */
    private StepRetryReadListener retryReadListener;
    /**
     * 重试处理的监听器 --- 重试处理的时候将会触发
     */
    private StepRetryProcessListener retryProcessListener;
    /**
     * 重试写的监听器 --- 重试写的时候将会触发
     */
    private StepRetryWriteListener retryWriteListener;

    private CompositeStepExecutionListener compositeStepExecutionListener;
    private CompositeChunkListener compositeChunkListener;
    private CompositeItemReadListener<I> compositeItemReadListener;
    private CompositeItemProcessListener<I, O> compositeItemProcessListener;
    private CompositeItemWriteListener<O> compositeItemWriteListener;
    private CompositeSkipListener<I, O> compositeSkipListener;
    private CompositeRetryReadListener compositeRetryReadListener;
    private CompositeRetryProcessListener compositeRetryProcessListener;
    private CompositeRetryWriteListener compositeRetryWriteListener;

    public StepListenerData<I, O> build() {
        buildCompositeException();
        buildDefaultException();
        registers();
        return this;
    }

    public StepListenerData<I, O> register(StepListener stepListener) {
        if (BaseUtil.isNull(stepListener)) {
            log.debug("注册的stepListener监听器为空");
            return this;
        }
        if (stepListener instanceof StepExecutionListener) {
            compositeStepExecutionListener.register((StepExecutionListener) stepListener);
        }
        if (stepListener instanceof ChunkListener) {
            compositeChunkListener.register((ChunkListener) stepListener);
        }
        if (stepListener instanceof ItemReadListener) {
            compositeItemReadListener.register((ItemReadListener) stepListener);
        }
        if (stepListener instanceof ItemProcessListener) {
            compositeItemProcessListener.register((ItemProcessListener) stepListener);
        }
        if (stepListener instanceof ItemWriteListener) {
            compositeItemWriteListener.register((ItemWriteListener) stepListener);
        }
        if (stepListener instanceof SkipListener) {
            compositeSkipListener.register((SkipListener) stepListener);
        }
        if (stepListener instanceof RetryReadListener) {
            compositeRetryReadListener.register((RetryReadListener) stepListener);
        }
        if (stepListener instanceof RetryProcessListener) {
            compositeRetryProcessListener.register((RetryProcessListener) stepListener);
        }
        if (stepListener instanceof RetryWriteListener) {
            compositeRetryWriteListener.register((RetryWriteListener) stepListener);
        }
        return this;
    }

    /**
     * 构建默认的Exception
     */
    private StepListenerData<I, O> buildDefaultException() {
        if (BaseUtil.isNull(this.stepExecutionListener)) {
            this.stepExecutionListener = new DefaultStepExecutionListener();
        }
        if (BaseUtil.isNull(this.chunkListener)) {
            this.chunkListener = new DefaultChunkListener();
        }
        if (BaseUtil.isNull(this.itemReadListener)) {
            this.itemReadListener = new DefaultItemReadListener<>();
        }
        if (BaseUtil.isNull(this.itemProcessListener)) {
            this.itemProcessListener = new DefaultItemProcessListener<>();
        }
        if (BaseUtil.isNull(this.itemWriteListener)) {
            this.itemWriteListener = new DefaultItemWriteListener<>();
        }
        if (BaseUtil.isNull(this.skipListener)) {
            this.skipListener = new DefaultSkipListener<>();
        }
        if (BaseUtil.isNull(this.retryReadListener)) {
            this.retryReadListener = new DefaultRetryReaderListener();
        }
        if (BaseUtil.isNull(this.retryProcessListener)) {

            this.retryProcessListener = new DefaultRetryProcessListener();
        }
        if (BaseUtil.isNull(this.retryWriteListener)) {
            this.retryWriteListener = new DefaultRetryWriteListener();
        }
        return this;
    }

    /**
     * 构建组合的Exception
     */
    private StepListenerData<I, O> buildCompositeException() {
        if (BaseUtil.isNull(this.compositeStepExecutionListener)) {
            this.compositeStepExecutionListener = new CompositeStepExecutionListener();
        }
        if (BaseUtil.isNull(this.compositeChunkListener)) {
            this.compositeChunkListener = new CompositeChunkListener();
        }
        if (BaseUtil.isNull(this.compositeItemReadListener)) {
            this.compositeItemReadListener = new CompositeItemReadListener<>();
        }
        if (BaseUtil.isNull(this.compositeItemProcessListener)) {
            this.compositeItemProcessListener = new CompositeItemProcessListener<>();
        }
        if (BaseUtil.isNull(this.compositeItemWriteListener)) {
            this.compositeItemWriteListener = new CompositeItemWriteListener<>();
        }
        if (BaseUtil.isNull(this.compositeRetryReadListener)) {
            this.compositeRetryReadListener = new CompositeRetryReadListener();
        }
        if (BaseUtil.isNull(this.compositeRetryProcessListener)) {
            this.compositeRetryProcessListener = new CompositeRetryProcessListener();
        }
        if (BaseUtil.isNull(this.compositeRetryWriteListener)) {
            this.compositeRetryWriteListener = new CompositeRetryWriteListener();
        }
        return this;
    }

    /**
     * 将默认的Exception注册到组合的Exception
     */
    public StepListenerData<I, O> registers() {
        this.register(stepExecutionListener);
        this.register(chunkListener);
        this.register(itemReadListener);
        this.register(itemProcessListener);
        this.register(itemWriteListener);
        this.register(retryReadListener);
        this.register(retryProcessListener);
        this.register(retryWriteListener);
        return this;
    }
}
