package com.dq.easy.cloud.model.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;
import com.dq.easy.cloud.model.basic.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;
import com.dq.easy.cloud.model.exception.dto.DqBaseServiceResultException;

@Component
public class DqBaseExceptionHandle {

    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    @ResponseBody
    public DqBaseServiceResult  handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	DqBaseServiceResult dqBaseServiceResult = DqBaseServiceResult.newInstance();
    	DqBaseServiceResultException serviceResultException = DqBaseServiceResultException.newInstance();
        String path = request.getRequestURL().toString();
        
        if(ex instanceof DqBaseBusinessException) {
        	DqBaseBusinessException dbbException = (DqBaseBusinessException) ex;
        	dqBaseServiceResult.setErrorCode(dbbException.getErrorCode());
        }else if(ex instanceof RuntimeException){
        	dqBaseServiceResult.buildErrorCode(DqBaseErrorCode.RUNTIME_EXCEPTION);
        }else{
        	dqBaseServiceResult.buildErrorCode(DqBaseErrorCode.SYS_EXCEPTION);
        }
        serviceResultException.setException(ex.getClass().getName());
        serviceResultException.setFailureDetails(ex.getMessage());
        serviceResultException.setTimstamp(DqDateUtils.getCurrentDate());
        serviceResultException.setPath(path);
        dqBaseServiceResult.buildServiceResultException(serviceResultException);
        return dqBaseServiceResult;
    }
}