package com.easy.cloud.pay.common.paymessage.pojo.dto.out;

import com.easy.cloud.pay.common.paymessage.constant.EcMsgType;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayOutMessageDTO;

/**
 * @author egan
 * 
 *         <pre>
 *      email egzosn@gmail.com
 *      date 2016-6-1 11:40:30
 *         </pre>
 */
public class EcPayJsonOutMessage extends EcPayOutMessageDTO {
	private static final long serialVersionUID = 1L;

	public EcPayJsonOutMessage() {
		this.msgType = EcMsgType.json.name();
	}

	@Override
	public String toMessage() {
		return getContent();
	}

}
