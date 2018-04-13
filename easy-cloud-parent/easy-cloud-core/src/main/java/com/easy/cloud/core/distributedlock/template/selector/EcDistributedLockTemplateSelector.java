package com.easy.cloud.core.distributedlock.template.selector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTemplateTypeEnum;
import com.easy.cloud.core.distributedlock.template.EcDistributedLockTemplate;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 分布式锁模板对象选择器
 * </p>
 *
 * @author daiqi 创建时间 2018年4月12日 上午11:29:04
 */
@Component
public class EcDistributedLockTemplateSelector {
	@Autowired
	private EcDistributedLockTemplate redissionTemplate;

	/**
	 * 
	 * <p>
	 * 根据类型选择锁模版对象
	 * </p>
	 *
	 * @param distributedLockTemplateTypeEnum
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月13日 下午7:02:40
	 */
	public EcDistributedLockTemplate selectLockTemplateByType(EcDistributedLockTemplateTypeEnum distributedLockTemplateTypeEnum) {
		if (EcBaseUtils.isNull(distributedLockTemplateTypeEnum)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		if (EcDistributedLockTemplateTypeEnum.isRedission(distributedLockTemplateTypeEnum)) {
			return redissionTemplate;
		}
		return null;
	}
}
