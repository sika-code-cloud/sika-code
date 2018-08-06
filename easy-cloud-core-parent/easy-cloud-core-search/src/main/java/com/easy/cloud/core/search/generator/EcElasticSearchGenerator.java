package com.easy.cloud.core.search.generator;

/**
 * @Title: EcElasticSearchGenerator
 * @Description:
 * @Author tudou
 * @Date 2018/8/5 22:58
 */
public class EcElasticSearchGenerator implements EcGenerator{

    @Override
    public boolean indexGenerator(String indexName) {
        return false;
    }

    @Override
    public boolean indexGenerator(String indexName, String type) {
        return false;
    }

    @Override
    public <T> boolean indexGenerator(Class<T> clazz) {
        return false;
    }

    @Override
    public boolean indexTemplateGenerator(String indexTemplateName) {
        return false;
    }

    @Override
    public <T> boolean indexTemplateGenerator(Class<T> clazz) {
        return false;
    }
}
