package com.dq.easy.cloud.pay.model.base.pojo.builder;

import com.dq.easy.cloud.pay.model.payment.dto.DqPayOutMessageDTO;

/**
 * 
 * <p>
 * source chanjarster/weixin-java-tools
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:16:29
 */
public class DqTextBuilder extends DqBaseBuilder<DqTextBuilder, DqPayOutMessageDTO> {
	private String content;

	public DqTextBuilder content(String content) {
		this.content = content;
		return this;
	}

	@Override
	public DqPayOutMessageDTO build() {
		DqPayTextOutMessage message = new DqPayTextOutMessage();
		setCommon(message);
		message.setContent(content);
		return message;
	}
}
