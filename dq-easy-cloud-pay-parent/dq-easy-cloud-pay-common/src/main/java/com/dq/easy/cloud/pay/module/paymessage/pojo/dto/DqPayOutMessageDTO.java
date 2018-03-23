package com.dq.easy.cloud.pay.module.paymessage.pojo.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.dq.easy.cloud.pay.module.paymessage.pojo.dto.builder.DqJsonBuilder;
import com.dq.easy.cloud.pay.module.paymessage.pojo.dto.builder.DqTextBuilder;
import com.dq.easy.cloud.pay.module.paymessage.pojo.dto.builder.DqXmlBuilder;

/**
 * 
 * <p>
 *  支付回调通知返回消息
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:04:03
 */
public abstract class DqPayOutMessageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String content;
    protected String msgType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * 获得文本消息builder
     * @return 文本消息builder
     */
    public static DqTextBuilder TEXT() {
        return new DqTextBuilder();
    }
    /**
     * 获得XML消息builder
     * @return XML消息builder
     */
    public static DqXmlBuilder XML() {
        return new DqXmlBuilder();
    }
    /**
     * 获得Json消息builder
     * @return Json消息builder
     */
    public static DqJsonBuilder JSON() {
        return new DqJsonBuilder(new JSONObject());
    }
    public abstract String toMessage();
}
