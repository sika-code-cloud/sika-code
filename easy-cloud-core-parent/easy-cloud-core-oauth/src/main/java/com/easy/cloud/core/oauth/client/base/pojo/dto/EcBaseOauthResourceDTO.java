package com.easy.cloud.core.oauth.client.base.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;

/**
 * 基础授权资源数据传输类
 *
 * @author daiqi
 * @create 2018-07-16 15:17
 */
public class EcBaseOauthResourceDTO {
    @JSONField(deserialize = false, serialize = false)
    public <T extends EcBaseOauthResourceDTO> T getTObj() {
        return (T)EcJSONUtils.parseObject(this, this.getClass());
    }
}
