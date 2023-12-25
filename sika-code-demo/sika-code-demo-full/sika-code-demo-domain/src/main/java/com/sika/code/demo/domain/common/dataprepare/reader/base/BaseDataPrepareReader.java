package com.sika.code.demo.domain.common.dataprepare.reader.base;

import com.sika.code.demo.domain.common.dataprepare.context.DefaultDataPrepareContext;
import com.sika.code.core.base.pojo.domain.entity.BaseStandardEntity;

/**
 * <p>
 *  基础数据准备读取器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 9:25
 */
public abstract class BaseDataPrepareReader extends BaseStandardEntity<DefaultDataPrepareContext> {
    @Override
    protected void doExecute(DefaultDataPrepareContext context) {

    }
}
