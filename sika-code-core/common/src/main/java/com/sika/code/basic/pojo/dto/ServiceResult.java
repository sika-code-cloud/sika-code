package com.sika.code.basic.pojo.dto;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.exception.dto.BaseBusinessExceptionDTO;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 服务返回结果基础类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月2日 下午3:57:33
 */
@Data
public class ServiceResult<Result> {
    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
    private Boolean success;
    /**
     * 返回结果
     */
    private Result result;
    /**
     * 错误代码接口
     */
    private BaseErrorCode baseErrorCode;
    /**
     * 服务异常结果
     */
    private BaseBusinessExceptionDTO businessExceptionDTO;

    public static ServiceResult newInstance() {
        return new ServiceResult();
    }

    public static ServiceResult newInstance(BaseErrorCode baseErrorCode) {
        return new ServiceResult().buildCode(baseErrorCode);
    }

    /**
     * <p>
     * 创建异常信息的serviceResult对象
     * </p>
     *
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:42:04
     */
    public static ServiceResult newInstanceOfException(BaseBusinessExceptionDTO businessExceptionDTO) {
        return newInstance().buildServiceResultException(businessExceptionDTO);
    }

    /**
     * <p>
     * 创建成功状态的serviceResult对象
     * </p>
     *
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:42:04
     */
    public static ServiceResult newInstanceOfSuccess() {
        return newInstance().buildCode(BaseErrorCodeEnum.SUCCESS).buildSuccess(true);
    }

    /**
     * <p>
     * 创建携带错误代码的serviceResult对象
     * </p>
     *
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:42:04
     */
    public static ServiceResult newInstanceOfError(BaseErrorCode errorCode) {
        return newInstance(errorCode);
    }
    /**
     * <p>
     * 创建携带错误代码的serviceResult对象
     * </p>
     *
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:42:04
     */
    public static ServiceResult newInstanceOfErrorFormat(BaseErrorCode errorCode, Object... formatValues) {
        String message = errorCode.getMessage();
        return newInstance(errorCode).buildMessage(String.format(message, formatValues));
    }

    /**
     * <p>
     * 创建携带错误代码的serviceResult对象
     * </p>
     *
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:42:04
     */
    public static ServiceResult newInstanceOfErrorResult(BaseErrorCode baseErrorCode, Object result) {
        return newInstance(baseErrorCode).buildResult(result);
    }

    /**
     * <p>
     * 创建成功状态的带有响应结果的serviceResult对象
     * </p>
     *
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:42:04
     */
    public static ServiceResult newInstanceOfSucResult(Object result) {
        return newInstanceOfSuccess().buildResult(result);
    }

    /**
     * <p>
     * 创建带错误代码和响应结果的DqBaseServiceResult实例
     * </p>
     *
     * @param baseErrorCode
     * @param result
     * @return
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:43:20
     */
    public static ServiceResult newInstance(BaseErrorCode baseErrorCode, Object result) {
        return newInstance(baseErrorCode).buildResult(result);
    }

    /**
     * <p>
     * 根据泛型class获取result对应的泛型对象，若result为list类型将抛出异常
     * </p>
     *
     * @param clazz
     * @return T
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:33:23
     */
    public <T> T getTObj(Class<T> clazz) {
        return JSONUtil.parseObject(this.result, clazz);
    }

    /**
     * <p>
     * 如果result为list则可以将其转换为泛型对应的列表，否则将抛出异常
     * </p>
     *
     * @param clazz : Class<T> : 泛型class
     * @return List
     * @author daiqi
     * 创建时间    2018年2月2日 下午4:36:05
     */
    public <T> List<T> getTList(Class<T> clazz) {
        return JSONUtil.parseArray(this.result, clazz);
    }

    /**
     * 构建errorMsg
     */
    public ServiceResult buildCode(BaseErrorCode baseErrorCode) {
        this.baseErrorCode = baseErrorCode;
        buildCodeAndMessage(baseErrorCode.getCode(), baseErrorCode.getMessage());
        return this;
    }
 /**
     * 构建errorMsg
     */
    public ServiceResult buildMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 构建errorCode和errorMsg
     */
    public ServiceResult buildCodeAndMessage(String errorCode, String errorMsg) {
        this.code = errorCode;
        this.message = errorMsg;
        this.success = false;
        return this;
    }


    /**
     * 构建result
     */
    public ServiceResult buildResult(Result result) {
        this.result = result;
        return this;
    }

    /**
     * 构建businessExceptionDTO
     */
    public ServiceResult buildServiceResultException(BaseBusinessExceptionDTO businessExceptionDTO) {
        this.businessExceptionDTO = businessExceptionDTO;
        return this;
    }

    public String getMessage() {
        if (StringUtil.isEmpty(this.message) && BaseUtil.isNotNull(baseErrorCode)) {
            this.message = baseErrorCode.getMessage();
        }
        return message;
    }

    public ServiceResult buildSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Boolean getSuccess() {
        return Boolean.TRUE.equals(this.success);
    }
}
