package com.sika.code.batch.listener.step;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;

/**
 * @author daiqi
 * @create 2019-10-04 22:31
 */
@Slf4j
public class DefaultItemProcessListener<I, O> implements ItemProcessListener<I, O> {

    @Override
    public void afterProcess(I item, O result) {
        LogUtil.info("afterProcess：item", item, log);
        LogUtil.info("afterProcess：result", result, log);
    }

    @Override
    public void beforeProcess(I item) {
        LogUtil.info("beforeProcess：item", item, log);
    }

    @Override
    public void onProcessError(I item, Exception e) {
        LogUtil.info("onProcessError：item", item, log);
        LogUtil.error("onProcessError：Exception", e, log);
    }
}
