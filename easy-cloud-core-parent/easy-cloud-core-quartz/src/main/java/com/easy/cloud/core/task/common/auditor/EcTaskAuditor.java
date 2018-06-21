package com.easy.cloud.core.task.common.auditor;

import com.easy.cloud.core.jdbc.audit.aware.EcAuditorAware;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-06-21 20:45
 */
@Component
public class EcTaskAuditor implements EcAuditorAware{
    @Override
    public Object getCurrentAuditor() {
        return -1L;
    }
}
