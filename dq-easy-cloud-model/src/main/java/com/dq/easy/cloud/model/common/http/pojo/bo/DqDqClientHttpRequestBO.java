package com.dq.easy.cloud.model.common.http.pojo.bo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.MethodType;
import com.dq.easy.cloud.model.basic.constant.DqBaseConstant.DqCharset;
import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;
import com.dq.easy.cloud.model.common.http.constant.DqHttpErrorCode;
import com.dq.easy.cloud.model.common.http.pojo.dto.DqHttpHeaderDTO;
import com.dq.easy.cloud.model.common.http.pojo.dto.DqHttpStringEntityDTO;
import com.dq.easy.cloud.model.common.http.utils.DqUriVariables;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.xml.utils.DqXMLUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;


/**
 * 一个HTTP请求的客户端
 * @author: egan
 *  <pre>
 * email egzosn@gmail.com
 * date 2017/3/4 17:56
 *  </pre>
 */
public class DqDqClientHttpRequestBO<T> extends HttpEntityEnclosingRequestBase implements  org.apache.http.client.ResponseHandler<T>{

    public static final ContentType APPLICATION_FORM_URLENCODED_UTF_8 = ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8);;


    /**
     * http请求方式 get pos
     */
    private MethodType methodType;
    /**
     *  响应类型
     */
    private Class<T> responseType;


    public DqDqClientHttpRequestBO<T> setResponseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    /**
     * 空构造
     */
    public DqDqClientHttpRequestBO() {
    }

    /**
     *  根据请求地址 请求方法，请求内容对象
     * @param uri 请求地址
     * @param method  请求方法
     * @param request 请求内容
     */
    public DqDqClientHttpRequestBO(URI uri, MethodType method, Object request) {
       this(uri, method);
        setParameters(request);
    }
    /**
     * 根据请求地址 请求方法
     * @param uri 请求地址
     * @param method  请求方法
     */
    public DqDqClientHttpRequestBO(URI uri, MethodType method) {
        this.setURI(uri);
        this.methodType = method;
    }

    /**
     * 根据请求地址
     * @param uri  请求地址
     */
    public DqDqClientHttpRequestBO(URI uri) {
        this.setURI(uri);
    }
    /**
     * 根据请求地址
     * @param uri  请求地址
     */
    public DqDqClientHttpRequestBO(String uri) {
        this.setURI(URI.create(uri));
    }
    /**
     * 根据请求地址 请求方法
     * @param uri 请求地址
     * @param method  请求方法
     */
    public DqDqClientHttpRequestBO(String uri, MethodType method) {
        this.setURI(URI.create(uri));
        this.methodType = method;
    }
    /**
     *  根据请求地址 请求方法，请求内容对象
     * @param uri 请求地址
     * @param method  请求方法
     * @param request 请求内容
     */
    public DqDqClientHttpRequestBO(String uri, MethodType method, Object request) {
        this(uri, method);
        setParameters(request);
    }

    /**
     * 设置请求方式
     *
     * @param method 请求方式
     * {@link com.egzosn.pay.common.bean.MethodType} 请求方式
     */
    public void setMethod(MethodType method) {
        this.methodType = method;
    }

    /**
     * 获取请求方式
     * @return 请求方式
     */
    @Override
    public String getMethod() {
        return methodType.name();
    }

    /**
     * 设置代理
     * @param httpProxy http代理配置信息
     * @return 当前HTTP请求的客户端
     */
    public DqDqClientHttpRequestBO setProxy(HttpHost httpProxy){
        if (httpProxy != null) {
            RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
            setConfig(config);
        }
        return this;
    }


    /**
     * 设置请求参数
     *
     * @param request 请求参数
     * @return 当前HTTP请求的客户端
     */
    public DqDqClientHttpRequestBO setParameters(Object request) {
        if (null == request){
            return this;
        }
        if (request instanceof DqHttpHeaderDTO){
            DqHttpHeaderDTO entity = (DqHttpHeaderDTO)request;
            if (null != entity.getHeaders() ){
                for (Header header : entity.getHeaders()){
                    addHeader(header);
                }
            }
        }else if (request instanceof DqHttpStringEntityDTO){
            DqHttpStringEntityDTO entity = (DqHttpStringEntityDTO)request;
            if (!entity.isEmpty()){
                setEntity(entity);
            }
            if (null != entity.getHeaders() ){
                for (Header header : entity.getHeaders()){
                    addHeader(header);
                }
            }
        } else if (request instanceof HttpEntity){
            setEntity((HttpEntity)request);
        } else if (request instanceof Map) {
            StringEntity entity = new StringEntity(DqUriVariables.getParameters((Map) request), APPLICATION_FORM_URLENCODED_UTF_8);
            setEntity(entity);
        } else if (request instanceof String) {
            StringEntity entity = new StringEntity((String) request,  APPLICATION_FORM_URLENCODED_UTF_8);
            setEntity(entity);
        } else {
            StringEntity entity = new StringEntity(DqJSONUtils.parseObject(request, String.class), ContentType.APPLICATION_JSON);
            setEntity(entity);
        }

        return this;

    }


    @Override
    public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        final StatusLine statusLine = response.getStatusLine();
        final HttpEntity entity = response.getEntity();

        String[] value = null;
        if (null == entity.getContentType()){
            value = new String[]{"application/x-www-form-urlencoded"};
        }else {
            value = entity.getContentType().getValue().split(";");
        }

        if (statusLine.getStatusCode() >= 300 && statusLine.getStatusCode() != 304) {
            if (isJson(value[0], "") || isXml(value[0], "") ){
                return toBean(entity, value);
            }

            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        if (null == responseType){
            responseType = (Class<T>) String.class;
        }


        return toBean(entity, value);

    }

    private T toBean(HttpEntity entity, String[] value) throws IOException {
        if (ContentType.APPLICATION_OCTET_STREAM.getMimeType().equals(value[0])){

            if (responseType.isAssignableFrom(InputStream.class)){
                return (T)entity.getContent();
            }
            if (responseType.isAssignableFrom(OutputStream.class)){
                try {
                    T t = responseType.newInstance();
                    entity.writeTo((OutputStream)t);
                    return t;
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                	throw new RuntimeException(e);
                }

            }
        }
        String charset = "UTF-8";
        if (null != value && 2 == charset.length()) {
            charset = value[1].substring(value[1].indexOf("=") + 1);
        }
        String result = EntityUtils.toString(entity, DqCharset.UTF_8);
        String first = result.substring(0, 1);
        if (responseType.isAssignableFrom(String.class) || isJson(value[0], first)) {
            return DqJSONUtils.parseObject(result, responseType);
        }

        if (isXml(value[0], first)){
            return DqJSONUtils.parseObject(DqXMLUtils.getMapFromXmlStr(result), responseType);
        }

        throw DqBaseBusinessException.newInstance(DqBaseErrorCode.TYPE_CONVERT_EXCEPTION);


    }

    /**
     * 检测响应类型是否为json
     * @param contentType 内容类型
     * @param textFirst 文本第一个字符
     * @return 布尔型， true为json内容类型
     */
    private boolean isJson(String contentType, String textFirst){
        return( ContentType.APPLICATION_JSON.getMimeType().equals(contentType) || "{[".indexOf(textFirst) >= 0 );
    }

    /**
     * 检测响应类型是否为xml
     * @param contentType 内容类型
     * @param textFirst 文本第一个字符
     * @return 布尔型， true为xml内容类型
     */
    private boolean isXml(String contentType, String textFirst){
        return( ContentType.APPLICATION_XML.getMimeType().equals(contentType) || "<".indexOf(textFirst) >= 0 );
    }


}
