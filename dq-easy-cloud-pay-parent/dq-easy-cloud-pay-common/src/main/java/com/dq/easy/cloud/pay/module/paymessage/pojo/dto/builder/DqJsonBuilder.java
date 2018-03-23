package com.dq.easy.cloud.pay.module.paymessage.pojo.dto.builder;

import com.alibaba.fastjson.JSONObject;
import com.dq.easy.cloud.pay.module.base.pojo.builder.DqBaseBuilder;
import com.dq.easy.cloud.pay.module.paymessage.pojo.dto.DqPayOutMessageDTO;
import com.dq.easy.cloud.pay.module.paymessage.pojo.dto.out.DqPayJsonOutMessage;

/**
 * @author: egan
 *  <pre>
 *      email egzosn@gmail.com
 *      date 2017/1/13 14:30
 *   </pre>
 */
public class DqJsonBuilder  extends DqBaseBuilder<DqJsonBuilder, DqPayOutMessageDTO>{
    JSONObject json = null;

    public DqJsonBuilder(JSONObject json) {
        this.json = json;
    }

    public DqJsonBuilder content(String key, Object content) {
        this.json.put(key, content);
        return this;
    }

    public JSONObject getJson() {
        return json;
    }

    @Override
    public DqPayOutMessageDTO build() {
        DqPayJsonOutMessage message = new DqPayJsonOutMessage();
        setCommon(message);
        message.setContent(json.toJSONString());
        return message;
    }
}
