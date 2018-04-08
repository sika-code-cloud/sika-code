package com.easy.cloud.pay.common.paymessage.pojo.dto.builder;

import com.easy.cloud.pay.common.base.pojo.builder.EcBaseBuilder;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayOutMessageDTO;
import com.easy.cloud.pay.common.paymessage.pojo.dto.out.EcPayXmlOutMessage;

/**
 * 
 * <p>
 * source chanjarster/weixin-java-tools
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:16:43
 */
public class EcXmlBuilder extends EcBaseBuilder<EcXmlBuilder, EcPayOutMessageDTO> {
	private String content;
	private String code;

	public EcXmlBuilder content(String content) {
		this.content = content;
		return this;
	}

	public EcXmlBuilder code(String code) {
		this.code = code;
		return this;
	}

	@Override
	public EcPayOutMessageDTO build() {
		EcPayXmlOutMessage message = new EcPayXmlOutMessage();
		setCommon(message);
		message.setContent(content);
		message.setCode(code);
		return message;
	}
}
