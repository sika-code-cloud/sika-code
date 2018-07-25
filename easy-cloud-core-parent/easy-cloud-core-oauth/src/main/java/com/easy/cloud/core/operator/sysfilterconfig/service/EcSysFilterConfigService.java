package com.easy.cloud.core.operator.sysfilterconfig.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：服务接口
 *
 * @author THINK
 * @date 2018-06-25 16:36:55
 */
public interface EcSysFilterConfigService {

    /**
     * <p>
     * 保存过滤器配置信息
     * </p>
     *
     * @param sysFilterConfigDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/7/25 19:43
     */
    EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO);

    /**
     * <p>
     * 更新过滤器配置信息
     * </p>
     *
     * @param sysFilterConfigDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/7/25 19:43
     */
    EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO);

    /**
     * <p>
     * 加载过滤器链定义列表
     * </p>
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author daiqi
     * @date 2018/7/25 19:44
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * <p>
     * 删除过滤器链
     * </p>
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author daiqi
     * @date 2018/7/25 19:44
     */
    EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery);
}
