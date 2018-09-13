package com.easy.cloud.core.search.core;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/java-rest-high-document-bulk.html
 * @Title: EcElasticsearchRESTful
 * @Description:
 * @Author tudou
 * @Date 2018/8/6 10:16
 */
public class EcElasticsearchRESTful {
//    @Autowired
    private RestHighLevelClient client;

    public EcElasticsearchRESTful(RestHighLevelClient client) {
        this.client = client;
    }

    public boolean createIndex(String indexName, String type) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("twitter");

        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );

        request.mapping("tweet",
                "  {\n" +
                        "    \"tweet\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"message\": {\n" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }",
                XContentType.JSON);

        request.alias(
                new Alias("twitter_alias")
        );
        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.masterNodeTimeout("1m");
        request.waitForActiveShards(2);
        request.waitForActiveShards(ActiveShardCount.DEFAULT);
        CreateIndexResponse createIndexResponse = client.indices().create(request);
        return createIndexResponse.isAcknowledged();
    }

    public boolean existsIndex(String indexName, String type) throws IOException {
        try {
            GetIndexRequest request = new GetIndexRequest();
            request.indices(indexName);
            return client.indices().exists(request);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {

            }
        }
        return false;
    }

    public boolean deleteIndex(String indexName, String type) throws IOException {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest("posts");
            DeleteIndexResponse deleteIndexResponse = client.indices().delete(request);
        /*restHighLevelClient.indices().deleteAsync(request, new ActionListener<DeleteIndexResponse>() {
            @Override
            public void onResponse(DeleteIndexResponse deleteIndexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });*/
            return deleteIndexResponse.isAcknowledged();
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {

            }
        }
        return false;
    }

    public GetResponse get(String indexName, String type, String id) throws IOException {
        try {
            GetRequest getRequest = new GetRequest(
                    indexName,
                    type,
                    id);
            GetResponse getResponse = client.get(getRequest);
            return getResponse;
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {

            }
        }
        return null;
    }



    public boolean exists(String indexName, String type, String id) throws IOException {
        try {
            GetRequest getRequest = new GetRequest(
                    indexName,
                    type,
                    id);
            return client.exists(getRequest);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {

            }
        }
        return false;
    }

    public boolean delete(String indexName, String type, String id) throws IOException {
        try {
            DeleteRequest request = new DeleteRequest("posts", "doc", "1").version(2);
            DeleteResponse deleteResponse = client.delete(request);
            return deleteResponse.isFragment();
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {

            }
        }
        return false;
    }
    public UpdateResponse update(String indexName, String type, String id) throws IOException {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("updated", new Date());
            jsonMap.put("reason", "daily update");
            UpdateRequest request = new UpdateRequest("posts", "doc", "1")
                    .doc(jsonMap);
            UpdateResponse updateResponse = client.update(request);
            return updateResponse;
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {

            }
        }
        return null;
    }

    public void bulk() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts", "doc", "1")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts", "doc", "2")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts", "doc", "3")
                .source(XContentType.JSON,"field", "baz"));
        BulkResponse bulkResponse = client.bulk(request);
    }
}
