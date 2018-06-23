package com.easy.cloud.core.reptile.common.config;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineBeanClassDTO;
import com.geccocrawler.gecco.GeccoEngine;

/**
 * <p>
 * 爬虫引擎配置类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月6日 下午3:04:58
 */
public class EcReptileConfig {

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
        EcReptileEngineBeanClassDTO reptileEngineBeanClassDTO = EcReptileEngineBeanClassDTO.getInstance();
        reptileEngineBeanClassDTO.setReptileDynamicBeanDTO(reptileDynamicBeanDTO);
        reptileEngineBeanClassDTO.setGeccoEngine(geccoEngine);
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
        return EcReptileEngineBeanClassDTO.getInstance();
    }

}
