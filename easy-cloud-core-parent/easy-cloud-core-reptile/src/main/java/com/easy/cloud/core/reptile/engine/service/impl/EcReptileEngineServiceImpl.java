package com.easy.cloud.core.reptile.engine.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.reptile.engine.dao.EcReptileEngineDAO;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineDTO;
import com.easy.cloud.core.reptile.engine.pojo.entity.EcReptileEngineEntity;
import com.easy.cloud.core.reptile.engine.pojo.query.EcReptileEngineQuery;
import com.easy.cloud.core.reptile.engine.service.EcReptileEngineService;
import com.easy.cloud.core.reptile.engine.utils.EcReptileEngineUtils;
import com.geccocrawler.gecco.GeccoEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：服务实现类
 *
 * @author THINK
 * @date 2018-06-11 10:59:59
 */
@Service(value = "ecReptileEngineService")
public class EcReptileEngineServiceImpl extends EcBaseService implements EcReptileEngineService {
    /**
     * 数据处理接口
     */
    @Autowired
    private EcReptileEngineDAO ecReptileEngineDAO;

    @Override
    public EcBaseServiceResult loadReptileEngine(EcReptileEngineQuery reptileEngineQuery) {
        loadReptileEngineByCore(reptileEngineQuery);
        return EcBaseServiceResult.newInstanceOfSucResult(reptileEngineQuery);
    }

    @Override
    public GeccoEngine loadReptileEngineByCore(EcReptileEngineQuery reptileEngineQuery) {
        EcReptileEngineEntity reptileEngineEntity = ecReptileEngineDAO.queryGeccoEngineByNo(reptileEngineQuery);
        // 校验
        EcAssert.verifyDataNotExistent(reptileEngineEntity);
        EcReptileEngineDTO reptileEngineDTO = EcJSONUtils.parseObject(reptileEngineEntity, EcReptileEngineDTO.class);
        return EcReptileEngineUtils.loadReptileEngine(reptileEngineDTO);
    }
}
