package com.easy.cloud.core.reptile.dynamicbean.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileDataDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.query.EcReptileDynamicBeanQuery;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-06-07 17:20:59
 */
public interface EcReptileDynamicBeanService {
	
	EcBaseServiceResult saveReptileDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO);
	
	EcBaseServiceResult updateReptileDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO);
	
	/**
	 * 
	 * <p>
	 * 数据爬取
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param reptileDataDTO
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月8日 下午5:02:19
	 */
	EcBaseServiceResult reptileData(EcReptileDataDTO reptileDataDTO);
	/**
	 * 
	 * <p>
	 * 将规则class注册到爬虫引擎
	 * </p>
	 *
	 * @param dynamicBeanQuery
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午9:05:11
	 */
	EcBaseServiceResult registerToGeccoEngine(EcReptileDynamicBeanQuery dynamicBeanQuery);
}
