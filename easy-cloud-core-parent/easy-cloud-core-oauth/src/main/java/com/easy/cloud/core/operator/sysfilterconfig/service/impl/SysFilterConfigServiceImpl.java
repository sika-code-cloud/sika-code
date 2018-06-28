package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.authority.utils.EcAuthorityUtils;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.operator.sysfilterconfig.dao.SysFilterConfigDAO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.entity.SysFilterConfigEntity;
import com.easy.cloud.core.operator.sysfilterconfig.service.SysFilterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：服务实现类
 *
 * @author daiqi
 * @date 2018-06-25 16:36:55
 */
@Service(value = "sysFilterConfigService")
public class SysFilterConfigServiceImpl extends EcBaseService implements SysFilterConfigService {
    /**
     * 数据处理接口
     */
    @Autowired
    private SysFilterConfigDAO sysFilterConfigDAO;

    /**
     * 初始化权限
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = EcMapUtils.newLinkedHashMap();
        List<SysFilterConfigDTO> sysFilterConfigs = listAll();
        EcAssert.verifyListEmpty(sysFilterConfigs, "sysFilterConfig");
        for (SysFilterConfigDTO sysFilterConfigDTO : sysFilterConfigs) {
            filterChainDefinitionMap.put(sysFilterConfigDTO.getUrlPattern(), sysFilterConfigDTO.getFilterName());
        }
        return filterChainDefinitionMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcAssert.verifyObjNull(sysFilterConfigDTO, "sysFilterConfigDTO");
        EcAssert.verifyStrEmpty(sysFilterConfigDTO.getFilterName(), "filterName");
        EcAssert.verifyStrEmpty(sysFilterConfigDTO.getUrlPattern(), "urlPattern");
        EcAssert.verifyObjNull(sysFilterConfigDTO.getOrderNum(), "orderNum");
        logger.info("系统支持的过滤名称列表：" + EcAuthorityUtils.getSupportFilterNames());
        SysFilterConfigEntity sysFilterConfigEntity = EcJSONUtils.parseObject(sysFilterConfigDTO, SysFilterConfigEntity.class);
        sysFilterConfigDAO.save(sysFilterConfigEntity);
        // 更新缓存的过滤链
        return EcBaseServiceResult.newInstanceOfSucResult(sysFilterConfigEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcAssert.verifyObjNull(sysFilterConfigDTO.getId(), "id");
        logger.info("系统支持的过滤名称列表：" + EcAuthorityUtils.getSupportFilterNames());
        SysFilterConfigEntity sysFilterConfigEntity = sysFilterConfigDAO.findById(sysFilterConfigDTO.getId());
        sysFilterConfigEntity = EcBaseUtils.copeFromObjToTargetObj(sysFilterConfigDTO, sysFilterConfigEntity);
        sysFilterConfigDAO.update(sysFilterConfigEntity);
        // 更新缓存的过滤链
        return EcBaseServiceResult.newInstanceOfSucResult(sysFilterConfigEntity);
    }

    @Override
    public List<SysFilterConfigDTO> listAll() {
        List<SysFilterConfigEntity> sysFilterConfigEntities = sysFilterConfigDAO.listAll();
        if (EcCollectionsUtils.isEmpty(sysFilterConfigEntities)) {
            return new ArrayList<>();
        }
        return EcJSONUtils.parseArray(sysFilterConfigEntities, SysFilterConfigDTO.class);
    }

}
