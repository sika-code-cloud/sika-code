package com.easy.cloud.pay.common.paymessage.pojo.dto.builder;

import com.easy.cloud.pay.common.base.pojo.builder.EcBaseBuilder;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayOutMessageDTO;
import com.easy.cloud.pay.common.paymessage.pojo.dto.out.EcPayTextOutMessage;

/**
 * 
 * <p>
 * source chanjarster/weixin-java-tools
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:16:29
 */
public class EcTextBuilder extends EcBaseBuilder<EcTextBuilder, EcPayOutMessageDTO> {
	private String content;

	public EcTextBuilder content(String content) {
		this.content = content;
		return this;
	}

	@Override
	public EcPayOutMessageDTO build() {
		EcPayTextOutMessage message = new EcPayTextOutMessage();
		setCommon(message);
		message.setContent(content);
		return message;
	}
}
