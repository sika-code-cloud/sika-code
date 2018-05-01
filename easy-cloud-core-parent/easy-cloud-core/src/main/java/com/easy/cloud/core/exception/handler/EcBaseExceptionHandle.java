package com.easy.cloud.core.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.exception.dto.EcBaseServiceResultException;

@Component
public class EcBaseExceptionHandle {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    @ResponseBody
    public EcBaseServiceResult  handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	logger.error(ex.getMessage(), ex);
    	
    	EcBaseServiceResult ecBaseServiceResult = EcBaseServiceResult.newInstance();
    	EcBaseServiceResultException serviceResultException = EcBaseServiceResultException.newInstance();
        String path = request.getRequestURL().toString();
        
        if(ex instanceof EcBaseBusinessException) {
        	EcBaseBusinessException dbbException = (EcBaseBusinessException) ex;
        	ecBaseServiceResult.buildErrorCodeAndMsg(dbbException.getErrorCode(), dbbException.getErrorMsg());
        }else if(ex instanceof RuntimeException){
        	ecBaseServiceResult.buildDqBaseErrorCodeInf(EcBaseErrorCodeEnum.RUNTIME_EXCEPTION);
        }else{
        	ecBaseServiceResult.buildDqBaseErrorCodeInf(EcBaseErrorCodeEnum.SYS_EXCEPTION);
        }
        serviceResultException.setException(ex.getClass().getName());
        serviceResultException.setFailureDetails(ex.getMessage());
        serviceResultException.setTimstamp(EcDateUtils.getCurrentDate());
        serviceResultException.setPath(path);
        ecBaseServiceResult.buildServiceResultException(serviceResultException);
        
        EcLogUtils.error("异常统一处理日志", ecBaseServiceResult, logger);
        return ecBaseServiceResult;
    }
}