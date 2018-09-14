package com.easy.cloud.core.search.core;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.cluster.metadata.IndexTemplateMetaData;
import org.elasticsearch.search.aggregations.AggregationBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 借鉴了org.springframework.data.elasticsearch.core.ElasticsearchOperations
 *
 * @author Rizwan Idrees
 * @author Mohsin Husen
 * @author Kevin Leturc
 * @author tudou
 */
public interface EcElasticsearchOperations {

	/**
	 * @return org.elasticsearch.client.Client
	 */
	Client getClient();

	/**
	 *
	 * @return org.elasticsearch.client.IndicesAdminClient
	 */
	public IndicesAdminClient getIndicesAdminClient();

	/**
	 *
	 * @return org.elasticsearch.client.AdminClient
	 */
	public AdminClient getAdminClient();

	/**
	 * Create an index for given indexName
	 *
	 * @param indexName  索引名称
	 * @return boolean
	 */
	boolean createIndex(String indexName);

	/**
	 * Create an index for given indexName and Settings
	 *
	 * @param indexName  索引名称
	 * @param settings   settings
	 * @return boolean
	 */
	boolean createIndex(String indexName, Object settings);

	/**
	 * Create an index for given class and Settings
	 *
	 * @param clazz  index of clazz
	 * @return  boolean
	 */
	<T> boolean createIndex(Class<T> clazz);

	/**
	 * 创建索引模板
	 * @param templateName   模板名称
	 * @param indexPatterns  索引正则
	 * @param settings       settings
	 * @param mappings       mappings
	 * @return  boolean
	 */
	boolean createIndexTemplate(String templateName, List<String> indexPatterns, Object settings, Object mappings);

	/**
	 * getIndexTemplate
	 * @param templateName  模板名称
	 * @return java.util.List
	 */
	List<IndexTemplateMetaData> getIndexTemplate(String templateName) throws IOException;

	/**
	 * delete IndexTemplate
	 * @param templateName  模板名称
	 * @return  boolean
	 */
	boolean deleteIndexTemplate(String templateName);

	/**
	 * Create mapping for a class
	 *
	 * @param clazz  index of clazz
	 * @param <T>
	 */
	<T> boolean putMapping(Class<T> clazz);

	/**
	 * Create mapping for a given indexName and type
	 *
	 * @param indexName  索引名称
	 * @param type       索引类型
	 * @param mappings   mappings
	 */
	boolean putMapping(String indexName, String type, Object mappings);

	/**
	 * Get mapping for a class
	 *
	 * @param clazz  index of clazz
	 * @param <T>
	 */
	<T> Map getMapping(Class<T> clazz) throws Exception;

	/**
	 * Get mapping for a given indexName and type
	 *
	 * @param indexName  索引名称
	 * @param type   索引类型
	 */
	Map getMapping(String indexName, String type) throws Exception;

	/**
	 * Get all type and mapping for a given indexName
	 * @param indexName  索引名称
	 * @return java.util.Map
	 * @throws Exception
	 */
	Map<String, Object> getAllMapping(String indexName) throws Exception;

	/**
	 * Get settings for a given indexName
	 *
	 * @param indexName  索引名称
	 */
	Map<String, String> getSetting(String indexName);

	/**
	 * Get settings for a given class
	 *
	 * @param clazz  index of clazz
	 */
	<T> Map<String, String> getSetting(Class<T> clazz);

	/**
	 *
	 * @return
	 */
	Map<String, Map<String, String>> getAllSetting();

	/**
	 * Deletes an index for given entity
	 *
	 * @param clazz  index of clazz
	 * @param <T>
	 * @return  boolean
	 */
	<T> boolean deleteIndex(Class<T> clazz);

	/**
	 * Deletes an index for given indexName
	 *
	 * @param indexName  索引名称
	 * @return  boolean
	 */
	boolean deleteIndex(String indexName);

	/**
	 * check if index is exists
	 *
	 * @param clazz  index of clazz
	 * @param <T>
	 * @return  boolean
	 */
	<T> boolean indexExists(Class<T> clazz);

	/**
	 * check if index is exists for given IndexName
	 *
	 * @param indexName  索引名称
	 * @return  boolean
	 */
	boolean indexExists(String indexName);

	/**
	 * check if type is exists in an index
	 *
	 * @param indexName  索引名称
	 * @param type   索引类型
	 * @return  boolean
	 */
	boolean typeExists(String indexName, String type);

	/**
	 * refresh the indexName
	 *
	 * @param indexName  索引名称
	 */
	void refresh(String indexName);

	/**
	 * refresh the indexName
	 *
	 * @param clazz    index of clazz
	 */
	<T> void refresh(Class<T> clazz);


	/**
	 * Clears the search contexts associated with specified scroll ids.
	 *
	 * @param scrollId  scrollId
	 */
	<T> void clearScroll(String scrollId);

	/**
	 *
	 * @param aliasAction aliasAction
	 * @return java.lang.Boolean
	 */
	Boolean addAlias(IndicesAliasesRequest.AliasActions aliasAction);

	/**
	 * get all the alias pointing to specified index
	 *
	 * @param indexName  索引名称
	 * @return  java.util.List<org.elasticsearch.cluster.metadata.AliasMetaData>
	 */
	List<AliasMetaData> queryForAlias(String indexName);

	/**
	 * removeAlias
	 * @param indexName  索引名称
	 * @param aliasNmae  aliasName
	 * @return java.lang.Boolean
	 */
	Boolean removeAlias(String indexName, String aliasNmae);

	/**
	 * Delete the one object with provided id
	 *
	 * @param indexName  索引名称
	 * @param type       索引类型
	 * @param id         数据的id
	 * @return documentId of the document deleted
	 */
	String delete(String indexName, String type, String id);

	/**
	 * Delete the one object with provided id
	 *
	 * @param clazz  index of clazz
	 * @param id     数据的id
	 * @return documentId of the document deleted
	 */
	<T> String delete(Class<T> clazz, String id);

	/**
	 *
	 * @param indexName  索引名称
	 * @param type       索引类型
	 * @return org.elasticsearch.action.search.SearchRequestBuilder
	 */
	SearchRequestBuilder getSearchRequestBuilder(String indexName, String type);

	/**
	 * 根据searchRequestBuilder查询数据
	 * @param searchRequestBuilder  A search action request builder.
	 * @return org.elasticsearch.action.search.SearchResponse
	 */
	SearchResponse search(SearchRequestBuilder searchRequestBuilder);

}
