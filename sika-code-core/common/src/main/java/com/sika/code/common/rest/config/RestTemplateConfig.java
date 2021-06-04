package com.sika.code.common.rest.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.sika.code.common.rest.interceptor.RestTemplateInterceptor;
import com.sika.code.common.rest.properties.RestTemplateProperties;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * <p>
 * RestTemplateConfig配置
 * </p>
 * <pre>
 * RestTemplate高并发下异常与配置说明
 * 1、java.manager.ConcurrentModificationException
 * 2、java.net.SocketTimeoutException Connection timed out
 * </pre>
 *
 * @author daiqi
 * @date 2018/11/29 10:56
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @ConditionalOnMissingBean
    public RestTemplateInterceptor restTemplateInterceptor() {
        return new RestTemplateInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplateProperties restTemplateProperties() {
        return new RestTemplateProperties();
    }


    /**
     * 此restTemplate使用ribbon作为负载均衡器，处理https请求有一些问题
     */
    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean(name = "restTemplate")
    public RestTemplate restTemplate(RestTemplateInterceptor restTemplateInterceptor) {
        RestTemplate restTemplate = createRestTemplate(false);
        return addInterceptor(restTemplate, restTemplateInterceptor);
    }

    /**
     * 此restTemplate没有使用ribbon作为负载均衡器，使用httpClient进行配置用来处理https请求
     */
    @Bean
    @ConditionalOnMissingBean(name = "restTemplateForHttps")
    public RestTemplate restTemplateForHttps() {
        return createRestTemplate(true);
    }

    /**
     * <p>
     * 初始化restTemplate的参数
     * </p>
     *
     * @return org.springframework.web.client.RestTemplate
     * @author daiqi
     * @date 2019/3/27 14:42
     */
    public RestTemplate createRestTemplate(boolean userCustomerFactory) {
        RestTemplate restTemplate = new RestTemplate();
        if (userCustomerFactory) {
            restTemplate.setRequestFactory(clientHttpRequestFactory());
        }
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        buildHttpMessageConverterList(restTemplate);
        return restTemplate;
    }

    public RestTemplate addInterceptor(RestTemplate restTemplate, ClientHttpRequestInterceptor clientHttpRequestInterceptor) {
        restTemplate.setInterceptors(Collections.singletonList(clientHttpRequestInterceptor));
        return restTemplate;
    }

    /**
     * restTemplate连接工厂配置
     */
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        RestTemplateProperties restTemplateProperties = restTemplateProperties();
        // 连接池配置方式
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager();
        // 总连接数
        pollingConnectionManager.setMaxTotal(restTemplateProperties.getMaxTotal());
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(restTemplateProperties.getDefaultMaxPerRoute());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启, 设置为1
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(1, true));
        HttpClient httpClient = httpClientBuilder.build();

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(restTemplateProperties.getConnectTimeout());
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(restTemplateProperties.getReadTimeout());
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(restTemplateProperties.getConnectionRequestTimeout());
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        clientHttpRequestFactory.setBufferRequestBody(restTemplateProperties.getBufferRequestBody());
        return clientHttpRequestFactory;
    }


    /**
     * 构建httpMessageConverterList中的数据
     * 1: 使用默认响应的编码为utf-8
     * 2: 使用fastJson作为转换器
     */
    private void buildHttpMessageConverterList(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> httpMessageConverterList = restTemplate.getMessageConverters();
        // 1: 替换ISO-8859-1为utf-8
        Iterator<HttpMessageConverter<?>> iterator = httpMessageConverterList.iterator();
        if (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            //原有的String是ISO-8859-1编码 去掉
            if (converter instanceof StringHttpMessageConverter) {
                iterator.remove();
            }
        }
        httpMessageConverterList.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        // 2: 替换转换器为fastJson
        addFastJsonHttpMessageConverter(httpMessageConverterList);
    }

    /**
     * 将fastJsonHttp转化器设置到httpMessageConverterList中
     */
    private void addFastJsonHttpMessageConverter(List<HttpMessageConverter<?>> httpMessageConverterList) {
        // 2: 替换转换器为fastJson
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        // 设置支持的请求类型
        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        httpMessageConverterList.add(0, fastJsonHttpMessageConverter);
    }
}
