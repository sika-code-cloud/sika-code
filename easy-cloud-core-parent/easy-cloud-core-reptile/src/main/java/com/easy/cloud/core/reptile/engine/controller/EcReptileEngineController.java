package com.easy.cloud.core.reptile.engine.controller;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineDTO;
import com.easy.cloud.core.reptile.engine.pojo.query.EcReptileEngineQuery;
import com.easy.cloud.core.reptile.engine.service.EcReptileEngineService;
import com.easy.cloud.core.reptile.engine.service.impl.EcReptileEngineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：控制转发类
 *
 * @author THINK
 * @date 2018-06-11 10:59:59
 */
@RestController(value = "ecReptileEngineController")
@RequestMapping(value = "reptileEngine")
public class EcReptileEngineController extends EcBaseController {
    @Autowired
    private EcReptileEngineService reptileEngineService;

    /**  
     * <p>
     * 加载爬虫引擎到缓存
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     * @author daiqi  
     * @date 2018/6/11 14:59  
     * @param reptileEngineQuery
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult  
     */  
    @RequestMapping(value = "loadReptileEngine")
    public EcBaseServiceResult loadReptileEngine(@RequestBody EcReptileEngineQuery reptileEngineQuery) {
        return reptileEngineService.loadReptileEngine(reptileEngineQuery);
    }
}
