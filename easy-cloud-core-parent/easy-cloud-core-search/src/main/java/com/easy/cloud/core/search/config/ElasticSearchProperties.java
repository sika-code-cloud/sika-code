package com.easy.cloud.core.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ElasticSearchProperties
 * @Description:
 * @Author tudou
 * @Date 2018/6/14 17:02
 * @Version 2.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
public class ElasticSearchProperties {
    private String clusterName;
    private String clusterNodes;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }
}
