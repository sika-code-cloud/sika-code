package com.sika.code.informer.impl;

import cn.hutool.core.util.BooleanUtil;
import com.sika.code.common.thread.factory.Executors;
import com.sika.code.common.thread.factory.ThreadFactory;
import com.sika.code.informer.BaseInformer;
import com.sika.code.informer.dto.DingdingMessageDTO;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;

/**
 * <p>
 * 通知者---使用dingding方式
 * </p>
 *
 * @author daiqi
 * @date 2019/3/25 11:39
 */
@Data
public class InformerForDingding extends BaseInformer {
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

    public InformerForDingding(String webhook) {
        this.asyn = true;
        this.webhook = webhook;
    }

    @Override
    public boolean doInform(String massage) {
        if (BooleanUtil.isTrue(asyn)) {
            executorServiceForInformer.submit(() -> informCore(massage));
        } else {
            informCore(massage);
        }
        return true;
    }


    protected void informCore(String massage) {
        DingdingMessageDTO dingdingMessageDTO = new DingdingMessageDTO();
        dingdingMessageDTO.setContent(profilesActive + "环境：\n" + massage);
        restTemplate.postForEntity(webhook, dingdingMessageDTO, Object.class);
    }
}
