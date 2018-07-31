package com.easy.cloud.core.operator.sysfilterconfig.pojo.query;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author THINK
 * @date 2018-06-25 16:36:55
 */
public class SysFilterConfigQuery extends EcBaseQuery {
    /**
     * url的匹配模式
     */
    private String urlPattern;
    /**
     * 过滤器的名称
     */
    private String filterName;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }
}
