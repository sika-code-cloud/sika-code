package com.easy.cloud.core.reptile.common.config;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.reptile.common.config.properties.EcReptileEngineProperties;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineBeanClassDTO;
import com.geccocrawler.gecco.GeccoEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 爬虫引擎配置类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月6日 下午3:04:58
 */
@Configuration
@PropertySource("classpath:config/reptile-engine.properties")
public class EcReptileConfig {

    @Autowired
    private EcReptileEngineProperties reptileEngineProperties;
    /**
     * 保存EcReptileEngineBeanClassDTO对象的缓存
     */
    private static EcReptileEngineBeanClassDTO reptileEngineBeanClassDTO;

    private static List<Class<?>> spiderBeanClazzs = new ArrayList<>();

    public static void clearSpiderBeanClazzs() {
        EcReptileConfig.spiderBeanClazzs.clear();
    }

    public static void addSpiderBeanClazz(Class<?> spiderBeanClazz) {
        EcReptileConfig.spiderBeanClazzs.add(spiderBeanClazz);
    }

    public static void setSpiderBeanClazzs(List<Class<?>> spiderBeanClazzs) {
        EcReptileConfig.spiderBeanClazzs = spiderBeanClazzs;
    }

    public static List<Class<?>> getSpiderBeanClazzs() {
        if (EcReptileConfig.spiderBeanClazzs == null) {
            spiderBeanClazzs = new ArrayList<>();
        }
        return EcReptileConfig.spiderBeanClazzs;
    }

    /**
     * <p>
     * 将EcReptileEngineBeanClassDTO对象add到缓存
     * </p>
     *
     * @param reptileDynamicBeanDTO
     * @param geccoEngine
     * @author daiqi
     * @创建时间 2018年6月11日 上午10:24:09
     */
    public static synchronized void loadReptileEngineBeanClassDTO(EcReptileDynamicBeanDTO reptileDynamicBeanDTO, GeccoEngine geccoEngine) {
        EcAssert.verifyObjNull(reptileDynamicBeanDTO, "reptileDynamicBeanDTO");
        EcAssert.verifyObjNull(geccoEngine, "geccoEngine");
        reptileEngineBeanClassDTO = new EcReptileEngineBeanClassDTO(reptileDynamicBeanDTO, geccoEngine);
    }

    /**
     * <p>
     * 从缓存中获取EcReptileEngineBeanClassDTO对象
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param
     * @return com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineBeanClassDTO
     * @author daiqi
     * @date 2018/6/12 15:51
     */
    public static EcReptileEngineBeanClassDTO getReptileEngineBeanClassDTO() {
        return reptileEngineBeanClassDTO;
    }

    //	@Bean
    protected GeccoEngine geccoEngine() {
        GeccoEngine ge = GeccoEngine.create(reptileEngineProperties.getClasspath());
        ge.interval(reptileEngineProperties.getInterval());
        ge.loop(true);
        ge.thread(reptileEngineProperties.getThread());
        ge.debug(reptileEngineProperties.getDebug());
        ge.mobile(reptileEngineProperties.getMobile());
        ge.retry(reptileEngineProperties.getRetry());
        // 使用run方法保证后续的loop设置为false有效
        ge.run();
        ge.loop(false);
        return ge;
    }
}
