package com.easy.cloud.core.search.annotation;

import com.easy.cloud.core.search.config.EcElasticSearchConfig;
import com.easy.cloud.core.search.config.EcElasticSearchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Title: AnnotationTest
 * @Description:
 * @Author tudou
 * @Date 2018/7/20 20:02
 */
@Component
@ComponentScan
@ComponentScan("config")
@ComponentScan(basePackageClasses = EcElasticSearchConfig.class)
@ComponentScan(basePackageClasses = {EcElasticSearchConfig.class,EcElasticSearchProperties.class})
@ComponentScan(basePackages = "config")
@ComponentScan(basePackages ={"config","config2"})
public class AnnotationTest {
    @Autowired(required = false)
    public EcElasticSearchConfig ecElasticSearchConfig;
    @Autowired
    public AnnotationTest(EcElasticSearchConfig ecElasticSearchConfig){}
//    @Autowired
//    public void setEcElasticSearchConfig(EcElasticSearchProperties ecElasticSearchConfig) {
//        this.ecElasticSearchConfig = ecElasticSearchConfig;
//    }
}
