package com.sika.code.batch.core.item.writer;


import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.context.StandardParamContext;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;


/**
 * <p>
 * 基础写入器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 12:53
 */
@Data
public class BaseWriterExecutor<O> implements ItemStreamWriter<O>, InitializingBean {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected BatchBean batchBean;
    protected StandardParamContext context;
    private List<ItemWriter<O>> itemWriters;

    private boolean ignoreItemStream = false;

    public void setIgnoreItemStream(boolean ignoreItemStream) {
        this.ignoreItemStream = ignoreItemStream;
    }

    @Override
    public void write(List<? extends O> items) throws Exception {
        if (context.isAsynWrite()) {
            writeForAsyn(items);
        } else {
            writeForSync(items);
        }
    }

    protected void writeForAsyn(List<? extends O> items) {
        logger.info("开启异步写");
        ExecutorService executorService = context.getWriteExecutorService();
        CountDownLatch countDownLatch = new CountDownLatch(itemWriters.size());
        for (ItemWriter<O> writer : itemWriters) {
            executorService.execute(() -> {
                try {
                    doWrite(writer, items);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeForSync(List<? extends O> items) {
        logger.info("开启同步写");
        for (ItemWriter<O> writer : itemWriters) {
            doWrite(writer, items);
        }
    }

    protected void doWrite(ItemWriter<O> writer, List<? extends O> items) {
        try {
            writer.write(items);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(itemWriters, "The 'delegates' may not be null");
        Assert.notEmpty(itemWriters, "The 'delegates' may not be empty");
    }

    /**
     * The list of item writers to use as delegates. Items are written to each of the
     * delegates.
     *
     * @param itemWriters the list of delegates to use.  The delegates list must not be null nor be empty.
     */
    public void setItemWriters(List<ItemWriter<O>> itemWriters) {
        this.itemWriters = itemWriters;
    }

    @Override
    public void close() throws ItemStreamException {
        for (ItemWriter<O> writer : itemWriters) {
            if (!ignoreItemStream && (writer instanceof ItemStream)) {
                ((ItemStream) writer).close();
            }
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        for (ItemWriter<? super O> writer : itemWriters) {
            if (!ignoreItemStream && (writer instanceof ItemStream)) {
                ((ItemStream) writer).open(executionContext);
            }
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        for (ItemWriter<? super O> writer : itemWriters) {
            if (!ignoreItemStream && (writer instanceof ItemStream)) {
                ((ItemStream) writer).update(executionContext);
            }
        }
    }

}
