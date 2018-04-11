package com.easy.cloud.pay.common.paymessage.pojo.dto.out;

import com.easy.cloud.pay.common.paymessage.constant.EcMsgType;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayOutMessageDTO;

/**
 * @author egan
 * <pre>
 *     email egzosn@gmail.com
 *     date 2016-6-1 13:53:3
 *  </pre>
 */
public class EcPayXmlOutMessage extends EcPayOutMessageDTO{
	private static final long serialVersionUID = 1L;
	
	private String code;

    public EcPayXmlOutMessage() {
        this.msgType = EcMsgType.xml.name();
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
