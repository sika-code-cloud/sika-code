package com.easy.cloud.core.search.generator;

public interface EcGenerator {
    boolean indexGenerator(String indexName);

    boolean indexGenerator(String indexName, String type);

    <T> boolean indexGenerator(Class<T> clazz);

    boolean indexTemplateGenerator(String indexTemplateName);

    <T> boolean indexTemplateGenerator(Class<T> clazz);
}
