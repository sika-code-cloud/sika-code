package com.sika.code.core.informer.impl;

import com.sika.code.core.informer.BaseInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常通知者---使用log方式
 *
 * @author daiqi
 * @create 2018-07-31 11:37
 */
public class InformerForLog extends BaseInformer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean doInform(String message) {
        logger.error(message);
        return true;

    }
}
