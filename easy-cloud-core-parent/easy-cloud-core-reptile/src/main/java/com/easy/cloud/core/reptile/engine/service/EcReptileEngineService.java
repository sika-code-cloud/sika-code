package com.easy.cloud.core.reptile.engine.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.engine.pojo.query.EcReptileEngineQuery;
import com.geccocrawler.gecco.GeccoEngine;

/**
 * 描述：服务接口
 *
 * @author THINK
 * @date 2018-06-11 10:59:59
 */
public interface EcReptileEngineService {
  /**  
   * <p>
   * 加载爬虫引擎
   * </p>
   * <pre>
   *     所需参数示例及其说明
   *     参数名称 : 示例值 : 说明 : 是否必须
   * </pre>
   * @author daiqi  
   * @date 2018/6/11 16:53
   * @param reptileEngineQuery  
   * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult  
   */  
    EcBaseServiceResult loadReptileEngine(EcReptileEngineQuery reptileEngineQuery);

    /**
     * <p>
     * 加载爬虫引擎核心接口
     * </p>
     * @author daiqi
     * @date 2018/6/11 14:59
     * @param reptileEngineQuery
     * @return com.eccocrawler.gecco.GeccoEngine
     */
    GeccoEngine loadReptileEngineByCore(EcReptileEngineQuery reptileEngineQuery);
}
