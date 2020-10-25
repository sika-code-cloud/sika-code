package com.sika.code.exception.advice;

import cn.hutool.core.util.BooleanUtil;
import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import com.sika.code.exception.properties.ExceptionProperties;
import com.sika.code.informer.properties.InformerProperties;
import com.sika.code.informer.util.InformerUtil;
import com.sika.code.result.Result;
import com.sika.code.result.generator.ResultGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 统一异常处理类
 * </p>
 *
 * @author daiqi
 * @date 2018/8/6 9:49
 * @return
 */
@Data
@ControllerAdvice
@ConditionalOnClass(Filter.class)
@Slf4j
public class ExceptionAdvice {

    @Autowired
    private ExceptionProperties exceptionProperties;
    @Autowired
    private InformerProperties informerProperties;
    @Autowired
    private ResultGenerator resultGenerator;

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        Result result = buildResultAndInform(request, exception);
        return result;
    }

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, RuntimeException runtimeException) {
        return buildResultAndInform(request, runtimeException);
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessExceptionHandler(HttpServletRequest request, BusinessException businessException) {
        return buildResultAndInform(request, businessException);
    }

    /**-
     * 构建异常的Result
     */
    protected Result buildResultAndInform(HttpServletRequest request, Exception exception) {
        Result result = buildResult(request, exception);
        log.error(exception.getMessage(), exception);
        // 通知
        if (isInformCode(result.getCode())) {
            inform(exception);
        }
        return result;
    }

    /**
     * <p>
     * 判断当前errorCode是否是需要通知的errorCode
     * </p>
     *
     * @param errorCode
     * @return boolean 是返回true  否则返回false
     * @author daiqi
     * @date 2019/3/25 14:18
     */
    protected boolean isInformCode(String errorCode) {
        boolean isInform = true;
        if (BooleanUtil.isTrue(exceptionProperties.getExclude())) {
            Map<String, BaseErrorCode> needExcludeInformCodesMap = exceptionProperties.getNeedExcludeInformCodesMap();
            if (BaseUtil.isNotNull(needExcludeInformCodesMap.get(errorCode))) {
                isInform = false;
            }
        } else {
            Map<String, BaseErrorCode> needInformCodesMap = exceptionProperties.getNeedInformCodesMap();
            if (!needInformCodesMap.containsKey(errorCode)) {
                isInform = false;
            }
        }
        return isInform;
    }

    protected Result buildResult(HttpServletRequest request, Exception exception) {
        return resultGenerator.generateExceptionResult(request.getRequestURL().toString(), exception);
    }

    /**
     * 通知
     */
    protected void inform(Exception exception) {
        InformerUtil.inform(exception, informerProperties.getExceptionInformers(), log);
    }

}