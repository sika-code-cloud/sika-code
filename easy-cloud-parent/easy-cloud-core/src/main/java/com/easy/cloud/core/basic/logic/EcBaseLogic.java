package com.easy.cloud.core.basic.logic;

import com.easy.cloud.core.common.log.annotation.EcLog;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.proxy.impl.EcLogLogicProxy;

/**
 * 
 * <p>
 * 业务逻辑基础类
 * </p>
 *
 * <pre>
 *  说明：所有业务逻辑类可以继承此类
 *  约定：
 *  命名规范：以logic结尾
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:24:24
 */
@EcLog(logLevel = EcLogLevelEnum.INFO, logProxyClass = EcLogLogicProxy.class, logType = EcLogTypeEnum.LOGIC)
public class EcBaseLogic {

}
