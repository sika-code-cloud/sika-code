package com.easy.cloud.database.mysql.interceptor;

import lombok.Data;

/**
 * <p>
 * 日志拦截器属性配置
 * </p>
 *
 * @author daiqi
 * @date 2019/5/24 23:54
 */
@Data
public class SqlLogInterceptorProperties {
    private static final Boolean OPENLOG = true;
    private Boolean fire = OPENLOG;

}
