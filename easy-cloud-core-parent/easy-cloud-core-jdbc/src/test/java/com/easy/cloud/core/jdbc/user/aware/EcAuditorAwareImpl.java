package com.easy.cloud.core.jdbc.user.aware;

import com.easy.cloud.core.jdbc.audit.aware.EcAuditorAware;
import org.springframework.stereotype.Component;

@Component
public class EcAuditorAwareImpl implements EcAuditorAware<Long>{

	@Override
	public Long getCurrentAuditor() {
		
		return 22233332l;
	}

}
