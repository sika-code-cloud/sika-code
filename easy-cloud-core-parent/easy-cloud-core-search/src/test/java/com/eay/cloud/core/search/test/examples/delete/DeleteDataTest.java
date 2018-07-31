package com.eay.cloud.core.search.test.examples.delete;

import com.easy.cloud.core.search.EcCoreSearchApplication;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.elasticsearch.client.Requests.indicesExistsRequest;

/**
 * 单条/批量删除数据
 *
 * @Title: DeleteDataTest
 * @Description:
 * @Author tudou
 * @Date 2018/7/29 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class DeleteDataTest {
    @Autowired
    private TransportClient client;

    @Test
    public void delData() {
        ActionFuture<DeleteResponse> result = client.prepareDelete("mylog", "log", "0").execute();
        System.out.println("ok");
    }

    @Test
    public void bulkDelData() {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (int i = 0; i < 10; i++) {
            bulkRequestBuilder.add(client.prepareDelete("mylog", "log", i + ""));
        }
        ActionFuture<BulkResponse> result = bulkRequestBuilder.execute();
        System.out.println("ok");
    }
}
