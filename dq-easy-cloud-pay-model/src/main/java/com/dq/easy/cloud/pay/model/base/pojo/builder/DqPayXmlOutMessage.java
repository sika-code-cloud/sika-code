package com.dq.easy.cloud.pay.model.base.pojo.builder;

import com.dq.easy.cloud.pay.model.base.constant.DqMsgType;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOutMessageDTO;

/**
 * @author egan
 * <pre>
 *     email egzosn@gmail.com
 *     date 2016-6-1 13:53:3
 *  </pre>
 */
public class DqPayXmlOutMessage extends DqPayOutMessageDTO{
	private static final long serialVersionUID = 1L;
	
	private String code;

    public DqPayXmlOutMessage() {
        this.msgType = DqMsgType.xml.name();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toMessage() {
       return "<xml><return_code><![CDATA[" + code + "]]></return_code><return_msg><![CDATA[" + content
                + "]]></return_msg></xml>";
    }
}
