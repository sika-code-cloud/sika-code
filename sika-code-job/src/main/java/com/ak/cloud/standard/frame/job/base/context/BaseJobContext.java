package com.ak.cloud.standard.frame.job.base.context;

import com.sika.code.core.base.pojo.context.BaseContext;
import com.sika.code.core.log.util.LogUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <pre>
 *  基础job上下文
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/5/11 15:32
 */
@Getter
@Setter
public class BaseJobContext implements BaseContext {
    /**
     * 记录日期
     */
    private LocalDate recordDate;
    /** traceId */
    private String traceId = LogUtil.getTraceId();

}
