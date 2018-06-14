package com.eay.cloud.core.search.test;

import com.easy.cloud.core.search.EcCoreSearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: ClientTest
 * @Description:
 * @Author tudou
 * @Date 2018/6/13 16:38
 * @Version 2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class ClientTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testClient(){
        elasticsearchTemplate.createIndex("test");
    }
}
