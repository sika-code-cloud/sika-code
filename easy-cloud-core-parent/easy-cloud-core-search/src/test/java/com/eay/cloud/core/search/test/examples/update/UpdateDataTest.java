package com.eay.cloud.core.search.test.examples.update;

import com.alibaba.fastjson.JSON;
import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.search.EcCoreSearchApplication;
import com.eay.cloud.core.search.test.examples.MyLog;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Title: UpdateDataTest
 * @Description:
 * @Author tudou
 * @Date 2018/7/29 21:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class UpdateDataTest {
    @Autowired
    private TransportClient client;

    @Test
    public void updateData() {
        UpdateResponse result = client.prepareUpdate("mylog", "log", "0")
                .setDoc(JSON.toJSONString(getMyLog()), XContentType.JSON)
                .get()/*.execute()*/;
        System.out.println("ok");
    }

    @Test
    public void bulkUpdateData() {
        BulkRequestBuilder bluk = client.prepareBulk();
        for (int i = 0; i < 10; i++) {
            bluk.add(client.prepareUpdate("mylog", "log", i + "").setDoc(JSON.toJSONString(getMyLog()), XContentType.JSON));
        }
        ActionFuture<BulkResponse> result = bluk.execute();
        System.out.println("ok");

    }

    public MyLog getMyLog() {
        String message = "INFO StatusLogger Log4j2 could not find a logging implementation. Please add " +
                "log4j-core to the classpath. Using SimpleLogger to log to the console...";
        return new MyLog("192.168.10.10", "myhost", "/var/log/mylog",
                EcDateFormatUtils.format(new Date(), EcDateFormatUtils.FORMAT_YYYY_MM_DD_HH_MI_SS_SSS), "INFO",
                message, message.length());
    }
}
