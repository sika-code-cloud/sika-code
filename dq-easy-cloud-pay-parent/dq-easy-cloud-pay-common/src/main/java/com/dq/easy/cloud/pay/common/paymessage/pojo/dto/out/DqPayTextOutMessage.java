package com.dq.easy.cloud.pay.common.paymessage.pojo.dto.out;

import com.dq.easy.cloud.pay.common.paymessage.constant.DqMsgType;
import com.dq.easy.cloud.pay.common.paymessage.pojo.dto.DqPayOutMessageDTO;

/**
 * @author egan
 *  <pre>
 *      email egzosn@gmail.com
 *      date 2016-6-1 11:40:30
 *  </pre>
 */
public class DqPayTextOutMessage extends DqPayOutMessageDTO{
	private static final long serialVersionUID = 1L;

	public DqPayTextOutMessage() {
        this.msgType = DqMsgType.text.name();
    }

    @Override
    public String toMessage() {
        return getContent();
    }
}
