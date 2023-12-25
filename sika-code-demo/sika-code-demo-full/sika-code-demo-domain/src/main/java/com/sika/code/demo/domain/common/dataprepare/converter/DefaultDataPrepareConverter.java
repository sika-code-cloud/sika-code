package com.sika.code.demo.domain.common.dataprepare.converter;

import com.sika.code.demo.domain.common.dataprepare.context.DefaultDataPrepareContext;
import com.sika.code.demo.domain.common.dataprepare.converter.base.BaseDataPrepareConverter;
import com.sika.code.demo.domain.common.dataprepare.converter.dto.ConvertDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 数据准备默认转化器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 9:26
 */
@Component
public class DefaultDataPrepareConverter<IN, OUT> extends BaseDataPrepareConverter<IN, OUT, DefaultDataPrepareContext<IN, OUT>> {

    @Override
    public List<OUT> convert(List<IN> inData, DefaultDataPrepareContext<IN, OUT> context) {
        ConvertDTO<IN, OUT> convertDTO = context.getConvertDTO();
        if (convertDTO.getInDataType() == 1) {

        }
        return null;
    }
}
