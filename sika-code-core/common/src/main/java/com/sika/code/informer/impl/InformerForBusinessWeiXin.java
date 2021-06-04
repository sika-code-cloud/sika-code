package com.sika.code.informer.impl;

import cn.hutool.core.util.BooleanUtil;
import com.sika.code.common.thread.factory.Executors;
import com.sika.code.common.thread.factory.ThreadFactory;
import com.sika.code.informer.BaseInformer;
import com.sika.code.informer.constant.MsgTypeEnum;
import com.sika.code.informer.dto.BaseThirdMessageDTO;
import com.sika.code.informer.dto.BusinessWeiXinMessageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;

/**
 * <p>
 * 通知者---使用企业微信方式
 * </p>
 *
 * @author daiqi
 * @date 2019/3/25 11:39
 */
@Data
@Accessors(chain = true)
public class InformerForBusinessWeiXin extends BaseInformer {
    private static RestTemplate restTemplate = new RestTemplate();
    private static ExecutorService executorServiceForInformer = Executors.newFixedThreadPool(10, new ThreadFactory("informerFordingding"));
    /**
     * 钉钉接受消息的webhook
     */
    private String webhook;

    /**
     * 是否异步执行 true 是 ， false 否默认异步
     */
    private Boolean asyn;
    /**
     * 激活的环境
     */
    private String profilesActive;

    public InformerForBusinessWeiXin(String webhook) {
        this.asyn = true;
        this.webhook = webhook;
    }

    @Override
    public boolean doInform(String massage) {
        if (BooleanUtil.isTrue(asyn)) {
            executorServiceForInformer.submit(() -> informCore(massage, MsgTypeEnum.TEXT));
        } else {
            informCore(massage, MsgTypeEnum.TEXT);
        }
        return true;
    }


    protected void informCore(String massage, MsgTypeEnum msgtype) {
        BusinessWeiXinMessageDTO businessWeiXinMessageDTO = new BusinessWeiXinMessageDTO();
        BusinessWeiXinMessageDTO.WeiXinMsgDTO weiXinMsgDTO =
                new BusinessWeiXinMessageDTO.WeiXinMsgDTO().setContent(massage);
        businessWeiXinMessageDTO.setWeiXinMsgDTO(weiXinMsgDTO).setMsgTypeEnum(msgtype).buildAitAll();
        informCore(businessWeiXinMessageDTO, webhook);
    }

    protected void informCore(BaseThirdMessageDTO baseThirdMessageDTO, String webhook) {
        restTemplate.postForEntity(webhook, baseThirdMessageDTO.getSendMsg(), Object.class);
    }

    @Override
    public boolean inform(BaseThirdMessageDTO baseThirdMessageDTO, String webhook) {
        informCore(baseThirdMessageDTO, webhook);
        return true;
    }
}
