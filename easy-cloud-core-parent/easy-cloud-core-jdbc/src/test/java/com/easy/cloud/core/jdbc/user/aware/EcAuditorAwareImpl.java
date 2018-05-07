package com.easy.cloud.core.jdbc.user.aware;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.jdbc.audit.aware.EcAuditorAware;

@Component
public class EcAuditorAwareImpl implements EcAuditorAware<Long>{

	@Override
	public Long getCurrentAuditor() {
		
		return 22233332l;
	}

}
