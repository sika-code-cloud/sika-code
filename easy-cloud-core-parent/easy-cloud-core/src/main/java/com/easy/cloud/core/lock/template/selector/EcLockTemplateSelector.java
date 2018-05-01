package com.easy.cloud.core.lock.template.selector;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTemplateTypeEnum;
import com.easy.cloud.core.lock.template.EcLockTemplate;

/**
 * 
 * <p>
 * 分布式锁模板对象选择器
 * </p>
 *
 * @author daiqi 创建时间 2018年4月12日 上午11:29:04
 */
@Component
public class EcLockTemplateSelector {
	@Resource(name = "lockTemplateRedission")
	private EcLockTemplate lockTemplateRedission;
	@Resource(name = "lockTemplateJedis")
	private EcLockTemplate lockTemplateJedis;

	/**
	 * 
	 * <p>
	 * 根据类型选择锁模版对象
	 * </p>
	 *
	 * @param lockTemplateTypeEnum
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月13日 下午7:02:40
	 */
	public EcLockTemplate selectLockTemplateByType(EcLockTemplateTypeEnum lockTemplateTypeEnum) {
		if (EcBaseUtils.isNull(lockTemplateTypeEnum)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		if (EcLockTemplateTypeEnum.isRedission(lockTemplateTypeEnum)) {
			return lockTemplateRedission;
		} else if (EcLockTemplateTypeEnum.isJedis(lockTemplateTypeEnum)) {
			return lockTemplateJedis;
		}
		return null;
	}
}
