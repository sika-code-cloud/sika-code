package com.easy.cloud.pay.core.paymessage.pojo.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.pay.core.paymessage.pojo.dto.builder.EcJsonBuilder;
import com.easy.cloud.pay.core.paymessage.pojo.dto.builder.EcTextBuilder;
import com.easy.cloud.pay.core.paymessage.pojo.dto.builder.EcXmlBuilder;

/**
 * 
 * <p>
 *  支付回调通知返回消息
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:04:03
 */
public abstract class EcPayOutMessageDTO implements Serializable {
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
    public static EcTextBuilder TEXT() {
        return new EcTextBuilder();
    }
    /**
     * 获得XML消息builder
     * @return XML消息builder
     */
    public static EcXmlBuilder XML() {
        return new EcXmlBuilder();
    }
    /**
     * 获得Json消息builder
     * @return Json消息builder
     */
    public static EcJsonBuilder JSON() {
        return new EcJsonBuilder(new JSONObject());
    }
    public abstract String toMessage();
}
