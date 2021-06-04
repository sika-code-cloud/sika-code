package com.sika.code.batch.listener.step;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;

/**
 * @author daiqi
 * @create 2019-10-04 22:32
 */
@Slf4j
public class DefaultSkipListener<I, O> implements SkipListener<I, O> {
    @Override
    public void onSkipInProcess(I item, Throwable t) {
        LogUtil.error("onSkipInProcess：item", item, log);
        LogUtil.error("onSkipInProcess：Throwable", t, log);
    }

    @Override
    public void onSkipInRead(Throwable t) {
        LogUtil.error("onSkipInRead：Throwable", t, log);
    }

    @Override
    public void onSkipInWrite(O item, Throwable t) {
        LogUtil.error("onSkipInWrite：item", item, log);
        LogUtil.error("onSkipInWrite：Throwable", t, log);
    }
}
