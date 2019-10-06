package com.sika.code.batch.dto;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.string.util.StringUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * step基础数据
 *
 * @author daiqi
 * @create 2019-10-04 21:41
 */
@Data
@Accessors(chain = true)
public class StepCommonData {
    private static String NAME_DEFAULT = "STEP_DEFAULT";
    private static int CHUNK_DEFAULT = 100;
    private static Class<? extends Throwable> EXCEPTION_DEFAULT = Exception.class;

    /**
     * Step的名称
     */
    private String name;
    /**
     * 每次处理的条数
     */
    private int chunk;
    /**
     * 跳过的次数
     */
    private int skipLimit;
    /**
     * 跳过的异常
     */
    private Class<? extends Throwable> skipException;
    /**
     * 重试的次数
     */
    private int retryLimit;
    /**
     * 重试的异常
     */
    private Class<? extends Throwable> retryException;

    public StepCommonData build() {
        if (StringUtil.isEmpty(this.name)) {
            this.name = NAME_DEFAULT;
        }
        if (this.chunk == 0) {
            this.chunk = CHUNK_DEFAULT;
        }
        if (BaseUtil.isNull(this.skipException)) {
            this.skipException = EXCEPTION_DEFAULT;
        }
        if (BaseUtil.isNull(this.retryException)) {
            this.retryException = EXCEPTION_DEFAULT;
        }
        return this;
    }
}
