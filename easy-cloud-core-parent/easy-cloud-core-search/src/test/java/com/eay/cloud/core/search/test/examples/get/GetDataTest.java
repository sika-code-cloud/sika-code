package com.eay.cloud.core.search.test.examples.get;

import com.alibaba.fastjson.JSON;
import com.easy.cloud.core.search.EcCoreSearchApplication;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Title: GetDataTest
 * @Description:
 * @Author tudou
 * @Date 2018/7/29 17:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class GetDataTest {
    @Autowired
    private TransportClient client;

    @Test
    public void get(){
        Map<String, Object> result = client.prepareGet("mylog","log","0").get().getSource();
        System.out.println(JSON.toJSONString(result));
    }

/*    @Test
    public void bulkGet(){
        BulkRequestBuilder bulk = client.prepareBulk();
        for(int i =0; i< 10; i++){

        bulk.add(client.prepareGet("mylog","log","0").request());
        }
        System.out.println(JSON.toJSONString(result));
    }*/
}
