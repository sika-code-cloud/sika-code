package com.easy.cloud.core.search.core;

import com.easy.cloud.core.search.core.query.builder.EcESQueryBuilderConstructor;
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
	 * @return elasticsearch client
	 */
	Client getClient();

	/**
	 *
	 * @return elasticsearch indicesAdminClient
	 */
	public IndicesAdminClient getIndicesAdminClient();

	/**
	 *
	 * @return elasticsearch adminClient
	 */
	public AdminClient getAdminClient();

	/**
	 * Create an index for given indexName
	 *
	 * @param indexName
	 */
	boolean createIndex(String indexName);

	/**
	 * Create an index for given indexName and Settings
	 *
	 * @param indexName
	 * @param settings
	 */
	boolean createIndex(String indexName, Object settings);

	/**
	 * Create an index for given class and Settings
	 *
	 * @param clazz
	 */
	<T> boolean createIndex(Class<T> clazz);

	/**
	 * 创建索引模板
	 * @param templateName
	 * @param indexPatterns
	 * @param settings
	 * @param mappings
	 * @return
	 */
	boolean createIndexTemplate(String templateName, List<String> indexPatterns, Object settings, Object mappings);

	/**
	 * getIndexTemplate
	 * @param templateName
	 * @return
	 */
	List<IndexTemplateMetaData> getIndexTemplate(String templateName) throws IOException;

	/**
	 * delete IndexTemplate
	 * @param templateName
	 * @return
	 */
	boolean deleteIndexTemplate(String templateName);

	/**
	 * Create mapping for a class
	 *
	 * @param clazz
	 * @param <T>
	 */
	<T> boolean putMapping(Class<T> clazz);

	/**
	 * Create mapping for a given indexName and type
	 *
	 * @param indexName
	 * @param type
	 * @param mappings
	 */
	boolean putMapping(String indexName, String type, Object mappings);

	/**
	 * Get mapping for a class
	 *
	 * @param clazz
	 * @param <T>
	 */
	<T> Map getMapping(Class<T> clazz) throws Exception;

	/**
	 * Get mapping for a given indexName and type
	 *
	 * @param indexName
	 * @param type
	 */
	Map getMapping(String indexName, String type) throws Exception;

	/**
	 * Get all type and mapping for a given indexName
	 * @param indexName
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getAllMapping(String indexName) throws Exception;

	/**
	 * Get settings for a given indexName
	 *
	 * @param indexName
	 */
	Map<String, String> getSetting(String indexName);

	/**
	 * Get settings for a given class
	 *
	 * @param clazz
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
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	<T> boolean deleteIndex(Class<T> clazz);

	/**
	 * Deletes an index for given indexName
	 *
	 * @param indexName
	 * @return
	 */
	boolean deleteIndex(String indexName);

	/**
	 * check if index is exists
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	<T> boolean indexExists(Class<T> clazz);

	/**
	 * check if index is exists for given IndexName
	 *
	 * @param indexName
	 * @return
	 */
	boolean indexExists(String indexName);

	/**
	 * check if type is exists in an index
	 *
	 * @param index
	 * @param type
	 * @return
	 */
	boolean typeExists(String index, String type);

	/**
	 * refresh the index
	 *
	 * @param indexName
	 *
	 */
	void refresh(String indexName);

	/**
	 * refresh the index
	 *
	 * @param clazz
	 *
	 */
	<T> void refresh(Class<T> clazz);


	/**
	 * Clears the search contexts associated with specified scroll ids.
	 *
	 * @param scrollId
	 *
	 */
	<T> void clearScroll(String scrollId);

	/**
	 *
	 * @param aliasAction
	 * @return
	 */
	Boolean addAlias(IndicesAliasesRequest.AliasActions aliasAction);

	/**
	 * get all the alias pointing to specified index
	 *
	 * @param indexName
	 * @return
	 */
	List<AliasMetaData> queryForAlias(String indexName);

	/**
	 * removeAlias
	 * @param indexName
	 * @param aliasNmae
	 * @return
	 */
	Boolean removeAlias(String indexName, String aliasNmae);

	/**
	 * Delete the one object with provided id
	 *
	 * @param indexName
	 * @param type
	 * @param id
	 * @return documentId of the document deleted
	 */
	String delete(String indexName, String type, String id);

	/**
	 * Delete the one object with provided id
	 *
	 * @param clazz
	 * @param id
	 * @return documentId of the document deleted
	 */
	<T> String delete(Class<T> clazz, String id);

	/**
	 *
	 * @param index
	 * @param type
	 * @return
	 */
	SearchRequestBuilder getSearchRequestBuilder(String index, String type);

	/**
	 *
	 * @param searchRequestBuilder
	 * @return
	 */
	SearchResponse scrollsearch(SearchRequestBuilder searchRequestBuilder);

}
