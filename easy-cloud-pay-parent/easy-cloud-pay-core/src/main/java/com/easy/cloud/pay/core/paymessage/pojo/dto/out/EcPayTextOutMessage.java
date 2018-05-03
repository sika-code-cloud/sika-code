package com.easy.cloud.pay.core.paymessage.pojo.dto.out;

import com.easy.cloud.pay.core.paymessage.constant.EcMsgType;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayOutMessageDTO;

/**
 * @author egan
 *  <pre>
 *      email egzosn@gmail.com
 *      date 2016-6-1 11:40:30
 *  </pre>
 */
public class EcPayTextOutMessage extends EcPayOutMessageDTO{
	private static final long serialVersionUID = 1L;

	public EcPayTextOutMessage() {
        this.msgType = EcMsgType.text.name();
    }

    @Override
    public String toMessage() {
        return getContent();
    }
}
