package com.eay.cloud.core.search.test.examples.index;

import com.easy.cloud.core.search.EcCoreSearchApplication;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;

import static org.elasticsearch.client.Requests.indicesExistsRequest;

/**
 * 以日志为例创建索引
 * @Title: EcIndexTest
 * @Description:
 * @Author tudou
 * @Date 2018/7/3 15:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class EcIndexTest {
    @Autowired
    private TransportClient client;
String index = "   {\n" +
        "    \"settings\":{\n" +
        "        \"index\":{\n" +
        "            \"number_of_shards\":\"5\",\n" +
        "            \"number_of_replicas\":\"1\"\n" +
        "        }\n" +
        "    },\n" +
        "    \"mappings\":{\n" +
        "        \"openstack_log\":{\n" +
        "            \"_all\":{\n" +
        "                \"enabled\":false\n" +
        "            },\n" +
        "            \"properties\":{\n" +
        "                \"hostname\":{\n" +
        "                    \"type\":\"keyword\"\n" +
        "                },\n" +
        "                \"hostip\":{\n" +
        "                    \"type\":\"keyword\"\n" +
        "                },\n" +
        "                \"log_package\":{\n" +
        "                    \"index\":false,\n" +
        "                    \"type\":\"keyword\",\n" +
        "                    \"doc_values\":false\n" +
        "                },\n" +
        "                \"log_date\":{\n" +
        "                    \"format\":\"yyyy-MM-dd HH:mm:ss.SSS\",\n" +
        "                    \"type\":\"date\"\n" +
        "                },\n" +
        "                \"log_level\":{\n" +
        "                    \"type\":\"keyword\"\n" +
        "                },\n" +
        "                \"message\":{\n" +
        "                    \"type\":\"text\"\n" +
        "                },\n" +
        "                \"size\":{\n" +
        "                    \"index\":false,\n" +
        "                    \"type\":\"long\",\n" +
        "                    \"doc_values\":false\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    },\n" +
        "    \"aliases\":[]\n" +
        "    }";
   /*
   {
    "settings":{
        "index":{
            "number_of_shards":"5",
            "number_of_replicas":"1"
        }
    },
    "mappings":{
        "openstack_log":{
            "_all":{
                "enabled":false
            },
            "properties":{
                "hostname":{
                    "type":"keyword"
                },
                "hostip":{
                    "type":"keyword"
                },
                "log_package":{
                    "index":false,
                    "type":"keyword",
                    "doc_values":false
                },
                "log_date":{
                    "format":"yyyy-MM-dd HH:mm:ss.SSS",
                    "type":"date"
                },
                "log_level":{
                    "type":"keyword"
                },
                "message":{
                    "type":"text"
                },
                "size":{
                    "index":false,
                    "type":"long",
                    "doc_values":false
                }
            }
        }
    },
    "aliases":[]
    }
    */


    /**
     * 判断索引是否存在
     * @param indexName
     */
    @Test
    public void indexExists() {
        String indexName = "mylog";
        boolean tag =  client.admin().indices().exists(indicesExistsRequest(indexName)).actionGet().isExists();
        System.out.println(tag);
    }

    /**
     * 判断索引中的type是否存在
     */
    @Test
    public void typeExists() {
        String index = "mylog";
        String type = "log";

        boolean tag =   client.admin().cluster().prepareState().execute().actionGet().getState().metaData().index(index)
                .getMappings().containsKey(type);
        System.out.println(tag);
    }

    /**
     * 创建索引
     * @throws IOException
     */
   @Test
   public void insertIndex() throws IOException {
       XContentBuilder mapping = logMapping();
       XContentBuilder seting = logSetting();
       CreateIndexRequestBuilder cib =client.admin()
               .indices().prepareCreate("mylog")
               .setSettings(seting)
               .addMapping("log",mapping)
               ;

       ActionFuture actionFuture = client.admin().indices().create(cib.request());
       System.out.println("ok");
   }

    @Test
    public void delIndex() throws IOException {
        DeleteIndexRequestBuilder delete =client.admin()
                .indices().prepareDelete("mylog")
                ;
        ActionFuture actionFuture = client.admin().indices().delete(delete.request());
        System.out.println("ok");
    }


    /**
     * "settings":{
     *         "index":{
     *             "number_of_shards":"5",
     *             "number_of_replicas":"1"
     *         }
     *     }
     * @return
     * @throws IOException
     */
    private XContentBuilder logSetting() throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("index")
                .field("number_of_shards","5")
                .field("number_of_replicas","1")
                .endObject()
                .endObject()
                ;

        return xContentBuilder;
    }

    public XContentBuilder logMapping() throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("_all")
                .field("enabled",false)
                .endObject()
                .startObject("properties")
                .startObject("hostname")
                .field("type", "keyword")
                .endObject()
                .startObject("hostip")
                .field("type", "keyword")
                .endObject()
                .startObject("log_package")
                .field("type", "keyword")
                .endObject()
                .startObject("log_date")
                .field("format", "yyyy-MM-dd HH:mm:ss.SSS")
                .field("type", "date")
                .endObject()
                .startObject("log_level")
                .field("type", "keyword")
                .endObject()
                .startObject("message")
                .field("type", "text")
                .endObject()
                .startObject("size")
                .field("type", "long")
                .endObject()
                .endObject()
                .endObject()
        ;
//        xContentBuilder.
        return xContentBuilder;
    }


}
