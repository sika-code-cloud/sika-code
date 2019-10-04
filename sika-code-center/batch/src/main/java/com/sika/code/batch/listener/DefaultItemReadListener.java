package com.sika.code.batch.listener;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

/**
 * @author daiqi
 * @create 2019-10-04 22:30
 */
@Slf4j
public class DefaultItemReadListener<I> implements ItemReadListener<I> {
    @Override
    public void afterRead(I item) {
        LogUtil.info("afterRead", item, log);
    }

    @Override
    public void beforeRead() {
        LogUtil.info("beforeRead", null, log);
    }

    @Override
    public void onReadError(Exception ex) {
        LogUtil.error("onReadError", ex, log);
    }

}
