package com.easy.cloud.core.search.core.index;

import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: EcElasticIndex
 * @Description:
 * @Author tudou
 * @Date 2018/7/2 17:57
 */
public class EcElasticIndex {

    private static final Logger logger = Logger.getLogger(EcElasticIndex.class);

    @Autowired
    TransportClient client;

    public void test(){
        IndexRequestBuilder indexRequestBuilder = client.prepareIndex("","");
    }

}
