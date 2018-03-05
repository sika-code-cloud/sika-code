package com.dq.easy.cloud.pay.model.base.pojo.builder;

import com.dq.easy.cloud.pay.model.paymessage.pojo.dto.DqPayOutMessageDTO;

/**
 * source chanjarster/weixin-java-tools
 *
 * @author  egan
 * <pre>
 *     email egzosn@gmail.com
 *     date 2016-6-1 11:40:30
 *  </pre>
 */
public abstract class DqBaseBuilder<BuilderType, ValueType> {


    public abstract ValueType build();

    public void setCommon(DqPayOutMessageDTO payOutMessage) {

    }

}