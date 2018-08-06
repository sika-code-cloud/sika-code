package com.eay.cloud.core.search.test;

import com.easy.cloud.core.search.EcCoreSearchApplication;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateAction;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequest;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequestBuilder;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.support.AbstractClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

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
    @Autowired
    private TransportClient client;
    @Test
    public void eTemplate(){
//        elasticsearchTemplate.
    }
    @Test
    public void testClient(){
//        ElasticsearchTemplate
        elasticsearchTemplate.createIndex("test");
    }
    @Test
    public void testTemp(){
//        https://www.elastic.co/guide/en/elasticsearch/reference/current/dynamic-templates.html
//        https://www.elastic.co/guide/cn/elasticsearch/guide/cn/index-templates.html
//        https://blog.csdn.net/zhanlanmg/article/details/50352338
        Map<String, Object> source = new HashMap<>();
        source.put("number_of_shards", 5);
        Map<String, Object> mappingSource = new HashMap<>();
        Map<String, Object> field = new HashMap<>();
//        mappingSource.put("message","String");
//        mappingSource.put("num","int");

        PutIndexTemplateAction putIndexTemplateAction = PutIndexTemplateAction.INSTANCE;
//        PutIndexTemplateRequestBuilder putIndexTemplateRequestBuilder  = putIndexTemplateAction.newRequestBuilder(client);
        PutIndexTemplateRequestBuilder putIndexTemplateRequestBuilder  = new PutIndexTemplateRequestBuilder(client, putIndexTemplateAction, "test")
                .setOrder(1)
                .setSettings(source)
                .addMapping("default", mappingSource)
                .setPatterns(Collections.singletonList("*-log"))
                ;
//        putIndexTemplateRequestBuilder.set(1);
        PutIndexTemplateResponse actionFuture = putIndexTemplateRequestBuilder.execute().actionGet();
        System.out.println("ok");
    }

    @Test
    public void testTemp2(){
        Map<String, Object> source = new HashMap<>();
        source.put("number_of_shards", 5);
        Map<String, Object> mappingSource = new HashMap<>();
        List<Object> dynamic_templates = new ArrayList<>();
        Map<String, Object> integers = new HashMap<>();
        Map<String, Object> integers2 = new HashMap<>();
        Map<String, Object> mapping = new HashMap<>();
        integers.put("match_mapping_type","long");
        mapping.put("type", "integer");
        integers.put("mapping",mapping);
        integers2.put("mapping",integers);
        dynamic_templates.add(integers2);
        mappingSource.put("dynamic_templates", dynamic_templates);
        PutIndexTemplateRequest request = new PutIndexTemplateRequest("test2")
                .order(1)
                .settings(source)
                .mapping("_doc", mappingSource)
                .patterns(Collections.singletonList("*-log"))
                ;
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        PutIndexTemplateResponse actionFuture = indicesAdminClient.putTemplate(request).actionGet();
        System.out.println("ok");
    }

    @Test
    public void xcontentBuilder() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("user")
                .field("type","keyword")
                .endObject()
                .startObject("postDate")
                .field("type","date")
                .endObject()
                .startObject("message")
                .field("type","keyword")
                .endObject()
                .startObject("address")
                .field("type","keyword")
                .endObject()
                .startObject("车牌号")
                .field("type","keyword")
                .endObject()
                .endObject()
                .endObject()
                ;
        PutIndexTemplateRequest request = new PutIndexTemplateRequest("test2")
                .patterns(Collections.singletonList("121312"))
                .mapping("3123213123",builder)
                ;
        PutIndexTemplateResponse actionFuture = client.admin().indices().putTemplate(request).actionGet();
//        System.out.println(builder.string());
    }

    public void testttt(){
//        XContentBuilder mapBuilder = XContentFactory.jsonBuilder();
//        mapBuilder.startObject()
//                .startObject(TypeName)
//                .startObject("_all").field("enabled", false).endObject()
//                .startObject("properties")
//                .startObject(IDFieldName).field("type", "long").endObject()
//                .startObject(SeqNumFieldName).field("type", "long").endObject()
//                .startObject(IMSIFieldName).field("type", "string").field("index", "not_analyzed").endObject()
//                .startObject(IMEIFieldName).field("type", "string").field("index", "not_analyzed").endObject()
//                .startObject(DeviceIDFieldName).field("type", "string").field("index", "not_analyzed").endObject()
//                .startObject(OwnAreaFieldName).field("type", "string").field("index", "not_analyzed").endObject()
//                .startObject(TeleOperFieldName).field("type", "string").field("index", "not_analyzed").endObject()
//                .startObject(TimeFieldName).field("type", "date").field("store", "yes").endObject()
//                .endObject()
//                .endObject()
//                .endObject();
//
//        PutMappingRequest putMappingRequest = Requests
//                .putMappingRequest(IndexName).type(TypeName)
//                .source(mapBuilder);
//        client.admin().indices().putMapping(putMappingRequest).actionGet();
    }
}
