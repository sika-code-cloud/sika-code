package com.sika.code.batch.listener;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-10-04 22:32
 */
@Slf4j
public class DefaultItemWriteListener<O> implements ItemWriteListener<O> {

    @Override
    public void afterWrite(List<? extends O> items) {
        LogUtil.info("afterWrite", items, log);
    }

    @Override
    public void beforeWrite(List<? extends O> items) {
        LogUtil.info("beforeWrite", items, log);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends O> items) {
        LogUtil.error("onWriteError：exception", exception, log);
        LogUtil.error("onWriteError：items", items, log);
    }
}
