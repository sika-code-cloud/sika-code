package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.authority.utils.EcAuthorityUtils;
import com.easy.cloud.core.basic.constant.EcBaseConstant;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.operator.sysfilterconfig.dao.SysFilterConfigDAO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.entity.SysFilterConfigEntity;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import com.easy.cloud.core.operator.sysfilterconfig.service.EcSysFilterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用jdbc处理过滤器配置信息
 *
 * @author daiqi
 * @date 2018-06-25 16:36:55
 */
@Service
@ConditionalOnProperty(value = "ec.oauth.filter.config.jdbc")
@ConditionalOnMissingBean(name = "ecSysFilterConfigService")
public class EcSysFilterConfigJdbcServiceImpl extends EcBaseService implements EcSysFilterConfigService {
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
        List<SysFilterConfigDTO> sysFilterConfigs = listByAvailable();
        EcAssert.verifyListEmpty(sysFilterConfigs, "sysFilterConfig");
        for (SysFilterConfigDTO sysFilterConfigDTO : sysFilterConfigs) {
            String urlPattern = sysFilterConfigDTO.getUrlPattern();
            String filterNameFromDTO = sysFilterConfigDTO.getFilterName();
            String filterNameFromMap = filterChainDefinitionMap.get(urlPattern);
            if (EcStringUtils.isNotEmpty(filterNameFromMap)) {
                filterNameFromDTO = filterNameFromMap + EcStringConstant.EcSymbol.COMMA + sysFilterConfigDTO.getFilterName();
            }
            filterChainDefinitionMap.put(urlPattern, filterNameFromDTO);
        }
        return filterChainDefinitionMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcAssert.verifyObjNull(sysFilterConfigDTO, "sysFilterConfigDTO");
        EcAssert.verifyStrEmpty(sysFilterConfigDTO.getFilterName(), "filterName");
        EcAssert.verifyStrEmpty(sysFilterConfigDTO.getUrlPattern(), "urlPattern");
        EcAssert.verifyObjNull(sysFilterConfigDTO.getPriorityLevel(), "priorityLevel");
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
    public List<SysFilterConfigDTO> listByAvailable() {
        SysFilterConfigQuery filterConfigQuery = new SysFilterConfigQuery();
        filterConfigQuery.setAvailable(EcBaseConstant.EcAvailableEnum.YES.type());
        return listByQuery(filterConfigQuery);
    }

    @Override
    public EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery) {
        EcAssert.verifyObjNull(filterConfigQuery, "filterConfigQuery");
        boolean deleteFlag = EcBaseUtils.isNotNull(filterConfigQuery.getId());
        if (deleteFlag) {
           int deleteCount = sysFilterConfigDAO.deleteByQuery(filterConfigQuery);
            EcBaseServiceResult.newInstanceOfSucResult(deleteCount);
        }
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    /**
     * 根据查询条件获取列表数据
     */
    public List<SysFilterConfigDTO> listByQuery(SysFilterConfigQuery filterConfigQuery) {
        if (EcBaseUtils.isNull(filterConfigQuery)) {
            filterConfigQuery = new SysFilterConfigQuery();
        }
        List<SysFilterConfigEntity> sysFilterConfigEntities = sysFilterConfigDAO.listByQuery(filterConfigQuery);
        if (EcCollectionsUtils.isEmpty(sysFilterConfigEntities)) {
            return new ArrayList<>();
        }
        return EcJSONUtils.parseArray(sysFilterConfigEntities, SysFilterConfigDTO.class);
    }

}
