package com.easy.cloud.core.search.config;

import com.easy.cloud.core.common.string.constant.EcStringConstant;
import org.apache.http.HttpHost;
import org.apache.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import static org.apache.commons.lang.StringUtils.split;
import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.apache.commons.lang.StringUtils.substringBeforeLast;

/**
 * @Title: EcElasticsearchRESTfulConfig
 * @Description:
 * @Author tudou
 * @Date 2018/8/6 11:30
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
public class EcElasticsearchRESTfulConfig {
    private static final Logger logger = Logger.getLogger(EcElasticsearchRESTfulConfig.class);
    @Autowired
    private EcElasticSearchProperties elasticSearchProperties;
    @Bean
    public RestHighLevelClient getRestHighLevelClient(){
        String[] nodes = split(elasticSearchProperties.getClusterNodes(), EcStringConstant.EcSymbol.COMMA);
        HttpHost[] hosts = new HttpHost[nodes.length];
        for (int i =0; i < hosts.length; i++) {
            String clusterNode = nodes[i];
            String hostName = substringBeforeLast(clusterNode, EcStringConstant.EcSymbol.COLON);
            String port = substringAfterLast(clusterNode, EcStringConstant.EcSymbol.COLON);
            Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
            Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
            logger.info("adding transport node : " + clusterNode);
            hosts[i] = new HttpHost(hostName, Integer.parseInt(port), "http");
        }
        return new RestHighLevelClient(RestClient.builder(hosts));
    }
}
