package com.easy.cloud.core.reptile.common.auditor;

import com.easy.cloud.core.jdbc.audit.aware.EcAuditorAware;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-06-22 16:57
 */
@Component
public class EcReptileAuditor implements EcAuditorAware{
    @Override
    public Object getCurrentAuditor() {
        return -1L;
    }
}
