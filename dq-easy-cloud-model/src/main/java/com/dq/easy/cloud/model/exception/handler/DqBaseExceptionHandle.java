package com.dq.easy.cloud.model.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;
import com.dq.easy.cloud.model.exception.dto.DqBaseServiceResultException;

@Component
public class DqBaseExceptionHandle {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    @ResponseBody
    public DqBaseServiceResult  handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	logger.error(ex.getMessage(), ex);
    	
    	DqBaseServiceResult dqBaseServiceResult = DqBaseServiceResult.newInstance();
    	DqBaseServiceResultException serviceResultException = DqBaseServiceResultException.newInstance();
        String path = request.getRequestURL().toString();
        
        if(ex instanceof DqBaseBusinessException) {
        	DqBaseBusinessException dbbException = (DqBaseBusinessException) ex;
        	dqBaseServiceResult.buildErrorCodeAndMsg(dbbException.getErrorCode(), dbbException.getErrorMsg());
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
        
        DqLogUtils.error("异常统一处理日志", dqBaseServiceResult, logger);
        return dqBaseServiceResult;
    }
}