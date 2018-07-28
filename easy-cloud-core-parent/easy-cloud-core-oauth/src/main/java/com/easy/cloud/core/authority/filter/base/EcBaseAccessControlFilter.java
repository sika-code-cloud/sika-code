package com.easy.cloud.core.authority.filter.base;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author daiqi
 * @create 2018-06-27 14:15
 */
public abstract class EcBaseAccessControlFilter extends AccessControlFilter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
