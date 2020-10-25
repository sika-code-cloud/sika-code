package com.sika.code.exception.dto;

import com.sika.code.common.date.constant.DateConstant;
import com.sika.code.common.date.util.DateUtil;
import com.sika.code.informer.BaseInformer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基础服务异常结果类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午7:24:48
 */
@Data
public class BaseBusinessExceptionDTO {
    private Date timestamp;
    private String timestampStr;
    private String exception;
    private String path;
    private String failureDetails;
    private BaseInformer exceptionInformer;

    private List<BaseInformer> exceptionInformers;

    public static BaseBusinessExceptionDTO newInstance() {
        return new BaseBusinessExceptionDTO();
    }

    /**  
     * <p>
     * 根据异常构建数据
     * </p>
     *   
     * @param path
     * @param exception
     * @param failureDetail
     * @return BaseBusinessExceptionDTO
     * @author daiqi 
     * @date 2019/7/3 22:40
     */  
    public BaseBusinessExceptionDTO build(String path, Exception exception, boolean failureDetail) {
        this.setException(exception.getClass().getName());
        if (failureDetail) {
            this.setFailureDetails(exception.getMessage());
        }
        this.setTimestamp(DateUtil.date());
        this.setPath(path);
        return this;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        this.timestampStr = DateUtil.format(this.timestamp, DateConstant.FORMAT_NORMAL);
    }
}
