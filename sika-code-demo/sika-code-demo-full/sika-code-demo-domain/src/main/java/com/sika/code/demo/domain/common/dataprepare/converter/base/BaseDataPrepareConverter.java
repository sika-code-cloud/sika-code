package com.sika.code.demo.domain.common.dataprepare.converter.base;


import com.sika.code.demo.domain.common.dataprepare.context.BaseDataPrepareContext;

import java.util.List;

/**
 * <p>
 * 基础数据准话转换器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 9:27
 */
public abstract class BaseDataPrepareConverter<IN, OUT, Context extends BaseDataPrepareContext<IN, OUT>> {
    /**
     * 将输入的数据转化为输出的数据
     *
     * @param inData
     * @return
     */
    public abstract List<OUT> convert(List<IN> inData, Context context);
}
