package com.easy.cloud.core.basic.service;

import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.proxy.impl.EcLogServiceProxy;

/**
 * 
 * <p>
 * 服务基础类
 * </p>
 *
 * <pre>
 *  说明：所有服务类可以继承此类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:24:24
 */
@EcLogAnnotation(level = EcLogLevelEnum.INFO, proxyClass = EcLogServiceProxy.class, type = EcLogTypeEnum.SERVICE)
public class EcBaseService {

}
