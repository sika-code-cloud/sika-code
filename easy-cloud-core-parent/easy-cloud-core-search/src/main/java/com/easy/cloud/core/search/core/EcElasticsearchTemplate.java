package com.easy.cloud.core.search.core;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.easy.cloud.core.search.annotation.Document;
import com.easy.cloud.core.search.annotation.Mapping;
import com.easy.cloud.core.search.annotation.Setting;
import com.easy.cloud.core.search.core.query.builder.EcESQueryBuilderConstructor;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesRequest;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.cluster.metadata.IndexTemplateMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.elasticsearch.client.Requests.indicesExistsRequest;
import static org.elasticsearch.client.Requests.refreshRequest;

/**
 * @Title: EcElasticsearchTemplate
 * @Description:
 * @Author tudou
 * @Date 2018/8/2 9:56
 */
@Component
public class EcElasticsearchTemplate implements EcElasticsearchOperations {
    private static final Logger logger = LoggerFactory.getLogger(EcElasticsearchTemplate.class);

    @Autowired
    private TransportClient client;

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public AdminClient getAdminClient() {
        return client.admin();
    }

    @Override
    public IndicesAdminClient getIndicesAdminClient() {
        return getAdminClient().indices();
    }

    @Override
    public boolean createIndex(String indexName) {
        Assert.notNull(indexName, "No index defined for Query");
        return getIndicesAdminClient().create(Requests.createIndexRequest(indexName)).actionGet().isAcknowledged();
    }

    @Override
    public boolean createIndex(String indexName, Object settings) {
        CreateIndexRequestBuilder createIndexRequestBuilder = getIndicesAdminClient().prepareCreate(indexName);
        if (settings instanceof String) {
            createIndexRequestBuilder.setSettings(String.valueOf(settings), XContentType.JSON);
        } else if (settings instanceof Map) {
            createIndexRequestBuilder.setSettings((Map) settings);
        } else if (settings instanceof XContentBuilder) {
            createIndexRequestBuilder.setSettings((XContentBuilder) settings);
        }
        return createIndexRequestBuilder.execute().actionGet().isAcknowledged();
    }

    @Override
    public <T> boolean createIndex(Class<T> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        if (clazz.isAnnotationPresent(Setting.class)) {
            String settingPath = clazz.getAnnotation(Setting.class).settingPath();
            if (isNotBlank(settingPath)) {
                String settings = readFileFromClasspath(settingPath);
                if (isNotBlank(settings)) {
                    return createIndex(document.indexName(), settings);
                }
            } else {
                logger.info("settingPath in @Setting has to be defined. Using default instead.");
            }
        }
        return createIndex(document.indexName());
    }

    @Override
    public boolean createIndexTemplate(String templateName, List<String> indexPatterns, Object settings, Object mappings) {
        PutIndexTemplateRequest request = new PutIndexTemplateRequest(templateName).patterns(indexPatterns);
        putTemplateSetting(request, settings);
        putTemplateMapping(request, mappings);
        return client.admin().indices().putTemplate(request).actionGet().isAcknowledged();
    }

    @Override
    public boolean deleteIndexTemplate(String templateName) {
        return getIndicesAdminClient().prepareDeleteTemplate(templateName).execute().actionGet().isAcknowledged();
    }

    @Override
    public List<IndexTemplateMetaData> getIndexTemplate(String templateName) {
        GetIndexTemplatesRequest request = new GetIndexTemplatesRequest(templateName);
        return getIndicesAdminClient().getTemplates(request).actionGet().getIndexTemplates();
    }

    /**
     * 向PutIndexTemplateRequest中添加setting
     *
     * @param request
     * @param settings
     */
    private void putTemplateSetting(PutIndexTemplateRequest request, Object settings) {
        if (settings == null) {
            return;
        }
        if (settings instanceof String) {
            request.settings(String.valueOf(settings), XContentType.JSON);
        } else if (settings instanceof Map) {
            request.settings((Map) settings);
        } else if (settings instanceof Settings) {
            request.settings((Settings) settings);
        } else if (settings instanceof Settings.Builder) {
            request.settings((Settings.Builder) settings);
        } else {
            return;
        }
    }

    /**
     * 向PutIndexTemplateRequest中添加mapping
     *
     * @param request
     * @param mappings
     */
    private void putTemplateMapping(PutIndexTemplateRequest request, Object mappings) {
        if (mappings == null) {
            return;
        }
        if (mappings instanceof String) {
            request.mapping("_doc", (String) mappings, XContentType.JSON);
        } else if (mappings instanceof Map) {
            request.mapping("_doc", (Map) mappings);
        } else if (mappings instanceof XContentBuilder) {
            request.mapping("_doc", (XContentBuilder) mappings);
        } else {
            return;
        }
    }

    @Override
    public <T> boolean putMapping(Class<T> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        if (clazz.isAnnotationPresent(Mapping.class)) {
            String mappingPath = clazz.getAnnotation(Mapping.class).mappingPath();
            if (isNotBlank(mappingPath)) {
                String mappings = readFileFromClasspath(mappingPath);
                if (isNotBlank(mappings)) {
                    return putMapping(document.indexName(), document.type(), mappings);
                }
            } else {
                logger.info("mappingPath in @Mapping has to be defined. Building mappings using @Field");
            }
        }
        return false;
    }

    @Override
    public boolean putMapping(String indexName, String type, Object mappings) {
        Assert.notNull(indexName, "No index defined for putMapping()");
        Assert.notNull(type, "No type defined for putMapping()");
        PutMappingRequestBuilder requestBuilder = getIndicesAdminClient().preparePutMapping(indexName).setType(type);
        if (mappings instanceof String) {
            requestBuilder.setSource(String.valueOf(mappings), XContentType.JSON);
        } else if (mappings instanceof Map) {
            requestBuilder.setSource((Map) mappings);
        } else if (mappings instanceof XContentBuilder) {
            requestBuilder.setSource((XContentBuilder) mappings);
        }
        return requestBuilder.execute().actionGet().isAcknowledged();
    }

    @Override
    public <T> Map<String, Object> getMapping(Class<T> clazz) throws Exception {
        Document document = clazz.getAnnotation(Document.class);
        return getMapping(document.indexName(), document.type());
    }

    @Override
    public Map<String, Object> getMapping(String indexName, String type) throws Exception {
        Assert.notNull(indexName, "No index defined for putMapping()");
        Assert.notNull(type, "No type defined for putMapping()");
        Map mappings = null;
        try {
            mappings = getIndicesAdminClient().getMappings(new GetMappingsRequest().indices(indexName).types(type))
                    .actionGet().getMappings().get(indexName).get(type).getSourceAsMap();
        } catch (Exception e) {
            throw new Exception("Error while getting mapping for indexName : " + indexName + " type : " + type + " " + e.getMessage());
        }
        return mappings;
    }

    @Override
    public Map<String, Object> getAllMapping(String indexName) throws Exception {
        Assert.notNull(indexName, "No index defined for putMapping()");
        Map<String, Object> map = new HashMap<>();
        try {
            ImmutableOpenMap<String, MappingMetaData> immutableOpenMap = getIndicesAdminClient().getMappings(new GetMappingsRequest().indices(indexName))
                    .actionGet().getMappings().get(indexName);
            Iterator<ObjectObjectCursor<String, MappingMetaData>> iterator = immutableOpenMap.iterator();
            while (iterator.hasNext()) {
                ObjectObjectCursor<String, MappingMetaData> objectObjectCursor = iterator.next();
                map.put(objectObjectCursor.key, objectObjectCursor.value.getSourceAsMap());
            }
        } catch (Exception e) {
            throw new Exception("Error while getting mapping for indexName : " + indexName + e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, String> getSetting(String indexName) {
        Assert.notNull(indexName, "No index defined for getSetting()");
        Settings settings = client.admin().indices().getSettings(new GetSettingsRequest()).actionGet()
                .getIndexToSettings().get(indexName);
        return getSettingMap(settings);
    }

    public static Map<String, String> getSettingMap(Settings settings) {
        return settings.keySet().stream().collect(Collectors.toMap((key) -> key, (key) -> settings.get(key)));
    }

    @Override
    public Map<String, Map<String, String>> getAllSetting() {
        Map<String, Map<String, String>> map = new HashMap<>();
        ImmutableOpenMap<String, Settings> indexToSettings = getIndicesAdminClient().getSettings(new GetSettingsRequest()).actionGet().getIndexToSettings();
        Iterator<ObjectObjectCursor<String, Settings>> iterator = indexToSettings.iterator();
        while (iterator.hasNext()) {
            ObjectObjectCursor<String, Settings> objectObjectCursor = iterator.next();
            map.put(objectObjectCursor.key, getSettingMap(objectObjectCursor.value));
        }
        return map;
    }

    @Override
    public <T> Map<String, String> getSetting(Class<T> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        return getSetting(document.indexName());
    }

    @Override
    public <T> boolean indexExists(Class<T> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        return indexExists(document.indexName());
    }

    @Override
    public boolean indexExists(String indexName) {
        return getIndicesAdminClient().exists(indicesExistsRequest(indexName)).actionGet().isExists();
    }

    @Override
    public boolean typeExists(String indexName, String type) {
        return getAdminClient().cluster().prepareState().execute().actionGet().getState().metaData().index(indexName)
                .getMappings().containsKey(type);
    }

    @Override
    public <T> boolean deleteIndex(Class<T> clazz) {
        Document document = getDocument(clazz);
        return deleteIndex(document.indexName());
    }

    @Override
    public boolean deleteIndex(String indexName) {
        Assert.notNull(indexName, "No index defined for delete operation");
        if (indexExists(indexName)) {
            return getIndicesAdminClient().delete(new DeleteIndexRequest(indexName)).actionGet().isAcknowledged();
        }
        return false;
    }

    @Override
    public void refresh(String indexName) {
        Assert.notNull(indexName, "No index defined for refresh()");
        getIndicesAdminClient().refresh(refreshRequest(indexName)).actionGet();
    }

    @Override
    public <T> void refresh(Class<T> clazz) {
        refresh(getDocument(clazz).indexName());
    }

    @Override
    public <T> void clearScroll(String scrollId) {
        client.prepareClearScroll().addScrollId(scrollId).execute().actionGet();
    }

    @Override
    public Boolean addAlias(IndicesAliasesRequest.AliasActions aliasAction) {
        Assert.notNull(aliasAction, "No aliasAction defined");
//        final IndicesAliasesRequest.AliasActions aliasAction = IndicesAliasesRequest.AliasActions.add().alias(query.getAliasName()).index(query.getIndexName());
//        if (query.getFilterBuilder() != null) {
//            aliasAction.filter(query.getFilterBuilder());
//        } else if (query.getFilter() != null) {
//            aliasAction.filter(query.getFilter());
//        } else if (isNotBlank(query.getRouting())) {
//            aliasAction.routing(query.getRouting());
//        } else if (isNotBlank(query.getSearchRouting())) {
//            aliasAction.searchRouting(query.getSearchRouting());
//        } else if (isNotBlank(query.getIndexRouting())) {
//            aliasAction.indexRouting(query.getIndexRouting());
//        }
        return getIndicesAdminClient().prepareAliases().addAliasAction(aliasAction).execute().actionGet().isAcknowledged();
    }

    @Override
    public List<AliasMetaData> queryForAlias(String indexName) {
        return getIndicesAdminClient().getAliases(new GetAliasesRequest().indices(indexName)).actionGet().getAliases()
                .get(indexName);
    }

    @Override
    public Boolean removeAlias(String indexName, String aliasNmae) {
        Assert.notNull(indexName, "No index defined for Alias");
        Assert.notNull(aliasNmae, "No alias defined");
        return getIndicesAdminClient().prepareAliases().removeAlias(indexName, aliasNmae).execute()
                .actionGet().isAcknowledged();
    }

    @Override
    public String delete(String indexName, String type, String id) {
        return client.prepareDelete(indexName, type, id).execute().actionGet().getId();
    }

    @Override
    public <T> String delete(Class<T> clazz, String id) {
        Document document = getDocument(clazz);
        return delete(document.indexName(), document.type(), id);
    }

    @Override
    public SearchResponse scrollsearch(String index, String type, EcESQueryBuilderConstructor constructor) {
        return scrollsearch(getSearchRequestBuilder(index, type), constructor);
    }

    @Override
    public SearchResponse scrollsearch(String index, String type, EcESQueryBuilderConstructor constructor, AggregationBuilder agg) {
        return scrollsearch(getSearchRequestBuilder(index, type), constructor, agg);
    }

    @Override
    public SearchResponse scrollsearch(SearchRequestBuilder searchRequestBuilder, EcESQueryBuilderConstructor constructor) {
        return null;
    }

    @Override
    public SearchResponse scrollsearch(SearchRequestBuilder searchRequestBuilder, EcESQueryBuilderConstructor constructor, AggregationBuilder agg) {
        return null;
    }

    /**
     * 构造SearchRequestBuilder
     *
     * @param index
     * @param type
     * @return
     */
    private SearchRequestBuilder getSearchRequestBuilder(String index, String type) {
        return client.prepareSearch(index.split(",")).setTypes(type.split(","));
    }

    private <T> Document getDocument(Class<T> clazz) {
        Assert.isTrue(clazz.isAnnotationPresent(Document.class), "Unable to identify index name. " + clazz.getSimpleName()
                + " is not a Document. Make sure the document class is annotated with @Document(indexName=\"foo\")");
        return clazz.getAnnotation(Document.class);
    }

    public static String readFileFromClasspath(String url) {
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(url);
            InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            String lineSeparator = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(lineSeparator);
            }
        } catch (Exception e) {
            logger.debug(String.format("Failed to load file from url: %s: %s", url, e.getMessage()));
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.debug(String.format("Unable to close buffered reader.. %s", e.getMessage()));
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void wirteFileFromClasspath(String url, String text) {
        File file = new File(url);
        wirteFileFromClasspath(file, text);
    }

    public static void wirteFileFromClasspath(File file, String text) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            wirteFileFromClasspath(fileWriter, text);
        } catch (IOException e) {
            System.out.println(String.format("Failed to load file from url: %s: %s", file.getPath(), e.getMessage()));
            logger.debug(String.format("Failed to load file from url: %s: %s", file.getPath(), e.getMessage()));
        }
    }

    public static void wirteFileFromClasspath(FileWriter fileWriter, String text) {
        // 构建指定文件
        BufferedWriter out = null;
        try {
//            FileWriter fileWriter = new FileWriter(url);
            out = new BufferedWriter(fileWriter);
            out.write(text, 0, text.length());
            out.flush();
        } catch (Exception e) {
            System.out.println(String.format("Failed to load file from url: %s: %s", fileWriter, e.getMessage()));
            logger.debug(String.format("Failed to write file : %s", e.getMessage()));
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println(String.format("Unable to close buffered write.. %s", e.getMessage()));
                    logger.debug(String.format("Unable to close buffered write.. %s", e.getMessage()));
                }
            }
        }
    }
}
