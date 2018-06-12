package com.easy.cloud.core.reptile.engine.utils;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.reptile.common.config.EcReptileConfig;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.engine.constant.EcReptileEngineConstant.EcDebugEnum;
import com.easy.cloud.core.reptile.engine.constant.EcReptileEngineConstant.EcLoopEnum;
import com.easy.cloud.core.reptile.engine.constant.EcReptileEngineConstant.EcMobileEnum;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineBeanClassDTO;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineDTO;
import com.geccocrawler.gecco.GeccoEngine;

/**
 * <p>
 * 爬虫引擎工具类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月11日 上午11:24:07
 */
public class EcReptileEngineUtils {
    /**
     * <p>
     * 根据数据传输对象运行引擎 加载爬虫引擎
     * </p>
     *
     * @param reptileEngineDTO
     * @author daiqi
     * @创建时间 2018年6月11日 上午11:25:52
     */
    public synchronized static GeccoEngine loadReptileEngine(EcReptileEngineDTO reptileEngineDTO, EcReptileDynamicBeanDTO reptileDynamicBeanDTO) {
        EcAssert.verifyObjNull(reptileEngineDTO, "reptileEngineDTO");
        GeccoEngine geccoEngine1FromCache = null;
        EcReptileEngineBeanClassDTO reptileEngineBeanClassDTO = EcReptileConfig.getReptileEngineBeanClassDTO();
        if (reptileEngineBeanClassDTO != null) {
            geccoEngine1FromCache = reptileEngineBeanClassDTO.getGeccoEngine();
        }
        GeccoEngine geccoEngine = convertFromEnginDTO(reptileEngineDTO, geccoEngine1FromCache);
        if (EcBaseUtils.isNull(geccoEngine1FromCache)) {
            geccoEngine.run();
        }
        geccoEngine.loop(false);
        EcReptileConfig.loadReptileEngineBeanClassDTO(reptileDynamicBeanDTO, geccoEngine);
        return geccoEngine;
    }

    /**
     * <p>
     * 将dto转换为引擎
     * </p>
     *
     * @param reptileEngineDTO
     * @return
     * @author daiqi
     * @创建时间 2018年6月11日 上午11:25:36
     */
    private static GeccoEngine convertFromEnginDTO(EcReptileEngineDTO reptileEngineDTO, GeccoEngine geccoEngine) {
        if (EcBaseUtils.isNull(geccoEngine)) {
            geccoEngine = GeccoEngine.create(reptileEngineDTO.getClasspath());
        }
        geccoEngine.classpath(reptileEngineDTO.getClasspath() + reptileEngineDTO.getReptileEngineNo());
        geccoEngine.interval(reptileEngineDTO.getInterval());
        geccoEngine.loop(EcLoopEnum.loop(reptileEngineDTO.getLoop()));
        geccoEngine.thread(reptileEngineDTO.getThread());
        geccoEngine.debug(EcDebugEnum.debug(reptileEngineDTO.getDebug()));
        geccoEngine.mobile(EcMobileEnum.mobile(reptileEngineDTO.getMobile()));
        geccoEngine.retry(reptileEngineDTO.getRetry());
        return geccoEngine;
    }
}
