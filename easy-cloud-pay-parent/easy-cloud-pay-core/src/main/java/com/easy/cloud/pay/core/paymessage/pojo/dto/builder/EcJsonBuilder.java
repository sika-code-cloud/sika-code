package com.easy.cloud.pay.core.paymessage.pojo.dto.builder;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.pay.core.base.pojo.builder.EcBaseBuilder;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayOutMessageDTO;
import com.easy.cloud.pay.core.paymessage.pojo.dto.out.EcPayJsonOutMessage;

/**
 * @author: egan
 *  <pre>
 *      email egzosn@gmail.com
 *      date 2017/1/13 14:30
 *   </pre>
 */
public class EcJsonBuilder  extends EcBaseBuilder<EcJsonBuilder, EcPayOutMessageDTO>{
    JSONObject json = null;

    public EcJsonBuilder(JSONObject json) {
        this.json = json;
    }

    public EcJsonBuilder content(String key, Object content) {
        this.json.put(key, content);
        return this;
    }

    public JSONObject getJson() {
        return json;
    }

    @Override
    public EcPayOutMessageDTO build() {
        EcPayJsonOutMessage message = new EcPayJsonOutMessage();
        setCommon(message);
        message.setContent(json.toJSONString());
        return message;
    }
}
