package com.easy.cloud.core.jdbc.user.aware;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.jdbc.auditor.aware.EcAuditorAware;

@Component
public class EcAuditorAwareImpl implements EcAuditorAware<Long>{

	@Override
	public Long getCurrentAuditor() {
		return 2222l;
	}

}
