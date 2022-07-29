package com.sika.code.core.result.generator;

import com.sika.code.core.base.errorcode.BaseErrorCode;
import com.sika.code.core.base.errorcode.BaseErrorCodeEnum;
import com.sika.code.core.exception.BusinessException;
import com.sika.code.core.result.Result;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Result对象生产者
 *
 * @author daiqi
 * @create 2019-07-03 21:25
 */
@Data
@Accessors(chain = true)
public class ResultGenerator {

    private boolean exceptionDetail;
    /**
     * 是否响应异常对象
     */
    private boolean rspException;

    public Result generateResult(Object object) {
        return Result.newInstance(object);
    }

    public Result generateResultError(String message) {
        return Result.newError(message);
    }

    public Result generateResultError(BaseErrorCode errorCode, String message) {
        Result result = Result.newError(message);
        result.setCode(errorCode.getCode());
        return result;
    }


    /**
     * <p>
     * 生成异常Result类
     * </p>
     *
     * @param path      : String : 请求的路径
     * @param exception : Exception : 异常对象
     * @return Result
     * @author daiqi
     * @date 2018/11/22 10:12
     */
    public Result generateExceptionResult(String path, Exception exception) {
        Result result = Result.newError(exception.getMessage());
        // 构建code和message
        buildCodeAndMessage(exception, result);
        return result;
    }

    /**
     * <p>
     * 根据异常构建result的code和message
     * </p>
     *
     * @param exception : 异常
     * @param result    : result
     * @return void
     * @author daiqi
     * @date 2019/7/3 22:48
     */
    protected void buildCodeAndMessage(Exception exception, Result result) {
        if (exception instanceof BusinessException) {
            result.setCode(((BusinessException) exception).getCode());
            result.setMessage(exception.getMessage());
        } else if (exception instanceof RuntimeException) {
            result.setCode(BaseErrorCodeEnum.RUNTIME_EXCEPTION.getCode());
            if (exception.getCause() != null) {
                result.setMessage(exception.getCause().getMessage());
            } else {
                result.setMessage(exception.getMessage());
            }

        } else {
            result.setCode(BaseErrorCodeEnum.SYS_EXCEPTION.getCode());
            result.setMessage(BaseErrorCodeEnum.SYS_EXCEPTION.getDesc());
        }
        result.setSuccess(false);
    }
}
