package com.easy.cloud.core.common.thread.predestroy;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.common.thread.factory.EcExecutors;

/**
 * 
 * <p>
 * 线程在容器停止前需要做的处理
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月12日 下午4:52:30
 */
@Component
public class EcThreadPreDestroy {
	
	@PreDestroy
	public void preDestroyThread() {
		EcExecutors.shutDownAllExecutorService();
	}
}
