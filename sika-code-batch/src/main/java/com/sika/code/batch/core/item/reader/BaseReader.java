package com.sika.code.batch.core.item.reader;


import com.sika.code.batch.standard.bean.common.BatchBean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>
 * 基础读取器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 12:53
 */
@Setter
@Getter
public class BaseReader<I> implements ItemStreamReader<I>, InitializingBean {
    protected BatchBean batchBean;
    protected ItemReader<I> itemReader;

    @Override
    public I read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return itemReader.read();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(batchBean, "The batchBean may not be null");
        Assert.notNull(itemReader, "The 'itemReader' may not be null");
    }

    @Override
    public void close() throws ItemStreamException {
        if (itemReader instanceof ItemStream) {
            ((ItemStream) itemReader).close();
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        if (itemReader instanceof ItemStream) {
            ((ItemStream) itemReader).open(executionContext);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        if (itemReader instanceof ItemStream) {
            ((ItemStream) itemReader).update(executionContext);
        }
    }
}
