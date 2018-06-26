package com.easy.cloud.core.base.auditor;

import com.easy.cloud.core.jdbc.audit.aware.EcAuditorAware;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-06-25 14:48
 */
@Component
public class EcAuthorityAuditor implements EcAuditorAware<Long>{
    @Override
    public Long getCurrentAuditor() {
        return -1L;
    }
}
