package com.sika.code.monitor.core.common.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;

/**
 * UnifyMetricsManagerHandler
 *
 * @author : daiqi
 * @date : 2023-09-04
 */
@Slf4j
public class UnifyMetricsManagerHandler implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Map<String, BaseMetricsManager> baseMetricsManagerMap = SpringUtil.getBeansOfType(BaseMetricsManager.class);
        if (CollUtil.isEmpty(baseMetricsManagerMap)) {
            return;
        }
        for (Map.Entry<String, BaseMetricsManager> entry : baseMetricsManagerMap.entrySet()) {
            try {
                entry.getValue().registerMetrics();
            } catch (Exception e) {
                String msg = StrUtil.format("name:【{}】,值的class为【{}】监控管理器注册异常", entry.getKey(), entry.getValue().getClass());
                log.error(msg, e);
            }
        }
    }
}
