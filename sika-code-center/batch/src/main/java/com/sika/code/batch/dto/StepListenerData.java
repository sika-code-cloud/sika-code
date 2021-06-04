package com.sika.code.batch.dto;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.listener.step.*;
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
    private CompositeStepExecutionListener stepListener;
    /**
     * chunk的监听器 --- 批处理时将会触发
     */
    private CompositeChunkListener chunkListener;
    /**
     * 读取item监听器 --- 读书item时将会触发
     */
    private CompositeItemReadListener<I> itemReadListener;
    /**
     * 处理item监听器 --- 处理item的时将会触发
     */
    private CompositeItemProcessListener<I, O> itemProcessListener;
    /**
     * 写入item监听器 --- 发生写入时将会触发
     */
    private CompositeItemWriteListener<O> itemWriteListener;
    /**
     * 跳过的监听器 --- 若发生跳过将会触发
     */
    private CompositeSkipListener<I, O> skipListener;
    /**
     * 重试读取的监听器 --- 重试读的时候将会触发
     */
    private CompositeRetryReadListener retryReadListener;
    /**
     * 重试处理的监听器 --- 重试处理的时候将会触发
     */
    private CompositeRetryProcessListener retryProcessListener;
    /**
     * 重试写的监听器 --- 重试写的时候将会触发
     */
    private CompositeRetryWriteListener retryWriteListener;

    public StepListenerData() {
        init();
    }

    public StepListenerData<I, O> build() {
        registers();
        return this;
    }

    public StepListenerData<I, O> register(StepListener listener) {
        if (BaseUtil.isNull(listener)) {
            log.debug("注册的stepListener监听器为空");
            return this;
        }
        if (listener instanceof StepExecutionListener) {
            this.stepListener.register((StepExecutionListener) listener);
        }
        if (listener instanceof ChunkListener) {
            this.chunkListener.register((ChunkListener) listener);
        }
        if (listener instanceof ItemReadListener<?>) {
            @SuppressWarnings("unchecked")
            ItemReadListener<I> itemReadListener = (ItemReadListener<I>) listener;
            this.itemReadListener.register(itemReadListener);
        }
        if (listener instanceof ItemProcessListener<?, ?>) {
            @SuppressWarnings("unchecked")
            ItemProcessListener<I, O> itemProcessListener = (ItemProcessListener<I, O>) listener;
            this.itemProcessListener.register(itemProcessListener);
        }
        if (listener instanceof ItemWriteListener<?>) {
            @SuppressWarnings("unchecked")
            ItemWriteListener<O> itemWriteListener = (ItemWriteListener<O>) listener;
            this.itemWriteListener.register(itemWriteListener);
        }
        if (listener instanceof SkipListener<?, ?>) {
            @SuppressWarnings("unchecked")
            SkipListener<I, O> skipListener = (SkipListener<I, O>) listener;
            this.skipListener.register(skipListener);
        }
        if (listener instanceof RetryReadListener) {
            this.retryReadListener.register((RetryReadListener) listener);
        }
        if (listener instanceof RetryProcessListener) {
            this.retryProcessListener.register((RetryProcessListener) listener);
        }
        if (listener instanceof RetryWriteListener) {
            this.retryWriteListener.register((RetryWriteListener) listener);
        }
        return this;
    }


    /**
     * 构建组合的Exception
     */
    private StepListenerData<I, O> init() {
        if (BaseUtil.isNull(this.stepListener)) {
            this.stepListener = new CompositeStepExecutionListener();
        }
        if (BaseUtil.isNull(this.chunkListener)) {
            this.chunkListener = new CompositeChunkListener();
        }
        if (BaseUtil.isNull(this.itemReadListener)) {
            this.itemReadListener = new CompositeItemReadListener<>();
        }
        if (BaseUtil.isNull(this.itemProcessListener)) {
            this.itemProcessListener = new CompositeItemProcessListener<>();
        }
        if (BaseUtil.isNull(this.itemWriteListener)) {
            this.itemWriteListener = new CompositeItemWriteListener<>();
        }
        if (BaseUtil.isNull(this.skipListener)) {
            this.skipListener = new CompositeSkipListener<>();
        }
        if (BaseUtil.isNull(this.retryReadListener)) {
            this.retryReadListener = new CompositeRetryReadListener();
        }
        if (BaseUtil.isNull(this.retryProcessListener)) {
            this.retryProcessListener = new CompositeRetryProcessListener();
        }
        if (BaseUtil.isNull(this.retryWriteListener)) {
            this.retryWriteListener = new CompositeRetryWriteListener();
        }
        return this;
    }

    /**
     * 将默认的监听器注册到组合的监听器
     */
    public StepListenerData<I, O> registers() {
        this.register(new DefaultStepExecutionListener());
        this.register(new DefaultChunkListener());
        this.register(new DefaultItemReadListener());
        this.register(new DefaultItemProcessListener());
        this.register(new DefaultItemWriteListener());
        this.register(new DefaultSkipListener());
        this.register(new DefaultRetryReadListener());
        this.register(new DefaultRetryProcessListener());
        this.register(new DefaultRetryWriteListener());
        return this;
    }
}
