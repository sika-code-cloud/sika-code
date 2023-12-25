package com.sika.code.demo.domain.common.dataprepare.context;

import com.sika.code.core.base.pojo.context.BaseContext;
import com.sika.code.demo.domain.common.dataprepare.converter.dto.ConvertDTO;
import lombok.Data;

/**
 * <p>
 *  基础数据准备阅读
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 10:10
 */
@Data
public class BaseDataPrepareContext<IN, Out> implements BaseContext {
    private ConvertDTO<IN, Out> convertDTO;
}
