package com.easy.cloud.core.search.config;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.apache.commons.lang.StringUtils.split;
import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.apache.commons.lang.StringUtils.substringBeforeLast;

/**
 * @Title: ElasticSearchConfig
 * @Description:
 * @Author tudou
 * @Date 2018/6/12 15:42
 * @Version 2.0
 */

@Configuration
//@ConfigurationProperties(prefix = "spring.data.elasticsearch")
public class ElasticSearchConfig {
    private static final Logger logger = Logger.getLogger(ElasticSearchConfig.class);
    static final String COLON = ":";
    static final String COMMA = ",";

//    @Value("${spring.data.elasticsearch.clusterName}")
//    @Value("${clusterName}")
    private String clusterName;
//    @Value("${spring.data.elasticsearch.clusterNodes}")
//    @Value("$clusterNodes}")
    private String clusterNodes;

    @Bean
    public Client getTransportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                //集群嗅探功能，可以动态添加新主机并删除旧主机
                .put("client.transport.sniff", true)
                //如果您使用与“elasticsearch”不同的名称，则必须设置群集名称
                .put("cluster.name", "sinorail").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        Assert.hasText(clusterNodes, "[Assertion failed] clusterNodes settings missing.");
        for (String clusterNode : split(clusterNodes, COMMA)) {
            String hostName = substringBeforeLast(clusterNode, COLON);
            String port = substringAfterLast(clusterNode, COLON);
            Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
            Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
            logger.info("adding transport node : " + clusterNode);
            client.addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));
        }
        return client;
    }
    @Value("${spring.data.elasticsearch.clusterName}")
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
    @Value("${spring.data.elasticsearch.clusterNodes}")
    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }
}





























