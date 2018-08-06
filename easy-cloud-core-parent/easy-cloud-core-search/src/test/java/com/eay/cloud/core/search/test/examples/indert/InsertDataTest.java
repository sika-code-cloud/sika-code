package com.eay.cloud.core.search.test.examples.indert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.search.EcCoreSearchApplication;
import com.eay.cloud.core.search.test.examples.MyLog;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 单条/批量插入数据
 * @Title: insertdata
 * @Description:
 * @Author tudou
 * @Date 2018/7/29 15:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class InsertDataTest {
    @Autowired
    private TransportClient client;
    @Test
    public void insertLog(){
        MyLog myLog = getMyLog();
//        client.prepareIndex("mylog","log").setSource(JSONObject.toJSON(myLog).toString()).get();
        client.prepareIndex("mylog","log").setSource(JSON.toJSONString(myLog),XContentType.JSON).execute();

    }


    @Test
    public void bulkInsertLog(){
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for(int i = 0; i< 10; i++){
            bulkRequest.add(client.prepareIndex("mylog","log").setId(i+"").setSource(JSON.toJSONString(getMyLog()),XContentType.JSON));
        }
        ActionFuture<BulkResponse> result = bulkRequest.execute();
        System.out.println("ok");
    }


    private MyLog getMyLog() {
        String message = "ERROR StatusLogger Log4j2 could not find a logging implementation. Please add " +
                "log4j-core to the classpath. Using SimpleLogger to log to the console...";
        return new MyLog("192.168.10.10","myhost","/var/log/mylog",
                EcDateFormatUtils.format(new Date(),EcDateFormatUtils.FORMAT_YYYY_MM_DD_HH_MI_SS_SSS),"ERROR",
                message,message.length());
    }
}
