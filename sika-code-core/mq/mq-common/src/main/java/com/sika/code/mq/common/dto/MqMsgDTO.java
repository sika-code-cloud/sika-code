package com.sika.code.mq.common.dto;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.pojo.dto.BaseMsgDTO;
import com.sika.code.mq.common.constant.MqDataType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息队列数据传输对象
 *
 * @author daiqi
 * @create 2019-06-29 20:42
 */
@Data
@Accessors(chain = true)
public class MqMsgDTO extends BaseMsgDTO {
    /**
     * 数据类型
     */
    private MqDataType dataType;

    public MqMsgDTO() {
    }

    public MqMsgDTO(Object data) {
        init(data, BaseErrorCodeEnum.SUCCESS);
    }

    public MqMsgDTO(Object data, BaseErrorCode errorCode) {
        init(data, errorCode);
    }

    /**
     * <p>
     * 根据数据创建实例
     * </p>
     *
     * @param data : 传输的数据
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/6/29 21:01
     */
    public static MqMsgDTO newInstance(Object data) {
        return new MqMsgDTO(data);
    }

    /**
     * <p>
     * 创建MqDTO对象
     * </p>
     *
     * @param data      : 具体数据
     * @param errorCode : 错误枚举类
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/6/29 21:54
     */
    public static MqMsgDTO newInstance(Object data, BaseErrorCode errorCode) {
        return new MqMsgDTO(data, errorCode);
    }

}
