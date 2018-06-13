package com.easy.cloud.core.reptile.dynamicbean.pojo.bo;

import com.easy.cloud.core.basic.pojo.bo.EcBaseBO;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;

/**
 * <p>
 * 逻辑处理类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月9日 下午3:03:03
 */
public class EcReptileDynamicBeanBO extends EcBaseBO {
    private EcReptileDynamicBeanDTO reptileDynamicBeanDTO;

    public EcReptileDynamicBeanBO(EcReptileDynamicBeanDTO reptileDynamicBeanDTO) {
        this.reptileDynamicBeanDTO = reptileDynamicBeanDTO;
    }

    /**
     * 初始化EcReptileDynamicBeanService.saveReptileDynamicBean方法的数据
     */
    public void initSaveReptileDynamicBeanData() {
        this.reptileDynamicBeanDTO.setMatchUrls(EcJSONUtils.toJSONString(this.reptileDynamicBeanDTO.getMatchUrlList()));
        this.reptileDynamicBeanDTO.setPipelineNames(EcJSONUtils.toJSONString(this.reptileDynamicBeanDTO.getPipelineNameList()));
        buildBeanClassPackageName();
    }

    /**
     * 校验EcReptileDynamicBeanService.saveReptileDynamicBean方法的数据
     */
    public void verifySaveReptileDynamicBeanData() {
        EcAssert.verifyStrEmpty(reptileDynamicBeanDTO.getPipelineNames(), "pipelineNames");
    }

    /**
     * 初始化EcReptileDynamicBeanService.updateReptileDynamicBean方法的数据
     */
    public void initUpdateReptileDynamicBeanData() {
        this.reptileDynamicBeanDTO.setPipelineNames(EcJSONUtils.toJSONString(this.reptileDynamicBeanDTO.getPipelineNameList()));
        buildBeanClassPackageName();
    }

    /**
     * 校验EcReptileDynamicBeanService.updateReptileDynamicBean方法的数据
     */
    public void verifyUpdateReptileDynamicBeanData() {
        EcAssert.verifyObjNull(reptileDynamicBeanDTO.getId(), "动态爬虫Bean的id");
    }

    public EcReptileDynamicBeanDTO getReptileDynamicBeanDTO() {
        return reptileDynamicBeanDTO;
    }

    private void buildBeanClassPackageName() {
        if (EcStringUtils.isEmpty(this.reptileDynamicBeanDTO.getBeanClassPackageName())) {
            this.reptileDynamicBeanDTO.setBeanClassPackageName("com.geccocrawler.gecco.dynamic");
        }
    }

}
