package com.easy.cloud.core.reptile.engine.pojo.dto;

import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.geccocrawler.gecco.GeccoEngine;

/**
 * @author daiqi
 * @create 2018-06-12 15:27
 */
public class EcReptileEngineBeanClassDTO {
    private EcReptileDynamicBeanDTO reptileDynamicBeanDTO;
    private GeccoEngine geccoEngine;

    public EcReptileEngineBeanClassDTO() {

    }

    public EcReptileEngineBeanClassDTO(EcReptileDynamicBeanDTO reptileDynamicBeanDTO, GeccoEngine geccoEngine) {
        this.reptileDynamicBeanDTO = reptileDynamicBeanDTO;
        this.geccoEngine = geccoEngine;
    }

    public GeccoEngine getGeccoEngine() {
        return geccoEngine;
    }

    public void setGeccoEngine(GeccoEngine geccoEngine) {
        this.geccoEngine = geccoEngine;
    }

    public EcReptileDynamicBeanDTO getReptileDynamicBeanDTO() {
        return reptileDynamicBeanDTO;
    }

    public void setReptileDynamicBeanDTO(EcReptileDynamicBeanDTO reptileDynamicBeanDTO) {
        this.reptileDynamicBeanDTO = reptileDynamicBeanDTO;
    }
}
