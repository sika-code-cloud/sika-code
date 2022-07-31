package com.sika.code.batch.core.item.processor;

import com.sika.code.batch.standard.bean.common.BatchBean;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * <p>
 * 基础处理器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 12:53
 */
@Slf4j
@Data
@Accessors(chain = true)
public abstract class BaseProcessor<I, O> implements ItemProcessor<I, O> {
    protected BatchBean batchBean;


}