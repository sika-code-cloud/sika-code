package com.sika.code.basic.pojo.dto;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.common.json.util.JSONUtil;
import lombok.Data;

import java.util.List;

/**
 * 所有消息的数据传输对象
 *
 * @author daiqi
 * @create 2019-06-29 23:17
 */
@Data
public class BaseMsgDTO {
    /**
     * 结果码
     */
    protected String code;

    /**
     * 描述
     */
    protected String message;

    /**
     * 是否成功
     */
    protected Boolean success;
    /**
     * 消息编号
     */
    protected String msgNo;
    /**
     * 数据
     */
    protected Object data;

    public BaseMsgDTO() {

    }

    public BaseMsgDTO(Object data) {
        init(data, BaseErrorCodeEnum.SUCCESS);
    }

    public BaseMsgDTO(Object data, BaseErrorCode errorCode) {
        init(data, errorCode);
    }

    /**
     * <p>
     * 创建MqDTO对象
     * </p>
     *
     * @param data      : 具体数据
     * @param errorCode : 错误枚举类
     * @return com.sika.code.mq.common.pojo.MsgDTO
     * @author daiqi
     * @date 2019/6/29 21:54
     */

    public void init(Object data, BaseErrorCode errorCode) {
        boolean success = false;
        if (BaseErrorCodeEnum.SUCCESS.equals(errorCode)) {
            success = true;
        }
        setCode(errorCode.getCode());
        setMessage(errorCode.getMessage());
        setSuccess(success);
        setData(data);
    }

    /**
     * <p>
     * 将data转化为class对应的对象
     * </p>
     *
     * @param tClass
     * @return T
     * @author daiqi
     * @date 2019/6/29 21:48
     */
    public <T> T parseObject(Class<T> tClass) {
        return JSONUtil.parseObject(this.getData(), tClass);
    }

    /**
     * <p>
     * 将data转化为class对应的数组
     * </p>
     *
     * @param tClass
     * @return T
     * @author daiqi
     * @date 2019/6/29 21:48
     */
    public <T> List<T> parseArray(Class<T> tClass) {
        return JSONUtil.parseArray(this.getData(), tClass);
    }
}
