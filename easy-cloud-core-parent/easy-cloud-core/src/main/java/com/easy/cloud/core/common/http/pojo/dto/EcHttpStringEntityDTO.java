package com.easy.cloud.core.common.http.pojo.dto;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import com.easy.cloud.core.common.http.utils.EcUriVariables;

/**
 * 请求实体，包含请求头，内容类型，编码类型等
 *
 * @author egan
 *         <pre>
*               email egzosn@gmail.com
*               date 2017/12/20
*           </pre>
 */
public class EcHttpStringEntityDTO extends StringEntity {
    /**
     * 请求头
     */
    private List<Header> headers;
    /**
     * 是否为空的请求实体
     */
    private boolean empty = false;


    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }


    public void requestIsEmpty(Map<String, Object> request) {
        if (null == request || request.isEmpty()){
            this.empty = true;
        }
    }
    public void requestIsEmpty(String request) {
        if (null == request || request.isEmpty()){
            this.empty = true;
        }

    }


    /**
     * 构造器
     *
     * @param request 请求体
     * @param headers 请求头
     *
     * @throws UnsupportedEncodingException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(Map<String, Object> request, Header... headers) throws UnsupportedEncodingException {
        this(EcUriVariables.getParameters(request), headers);
        requestIsEmpty(request);

    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param headers 请求头
     *
     * @throws UnsupportedEncodingException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(Map<String, Object> request, Map<String, String> headers) throws UnsupportedEncodingException {
        this(EcUriVariables.getParameters(request), headers);
        requestIsEmpty(request);

    }

    /**
     * 构造器
     *
     * @param request     请求体
     * @param contentType 内容类型
     */
    public EcHttpStringEntityDTO(Map<String, Object> request, ContentType contentType) {
        super(EcUriVariables.getParameters(request), contentType);
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param charset 字符类型
     */
    public EcHttpStringEntityDTO(Map<String, Object> request, String charset) {
        super(EcUriVariables.getParameters(request), charset);
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param charset 字符类型
     */
    public EcHttpStringEntityDTO(Map<String, Object> request, Charset charset) {
        super(EcUriVariables.getParameters(request), charset);
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request 请求体
     *
     * @throws UnsupportedEncodingException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(Map<String, Object> request) throws UnsupportedEncodingException {
        super(EcUriVariables.getParameters(request));
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request     请求体
     * @param contentType 内容类型
     *
     * @throws UnsupportedCharsetException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(String request, ContentType contentType) throws UnsupportedCharsetException {
        super(request, contentType);
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param charset 字符类型
     *
     * @throws UnsupportedCharsetException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(String request, String charset) throws UnsupportedCharsetException {
        super(request, charset);
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param charset 字符类型
     */
    public EcHttpStringEntityDTO(String request, Charset charset) {
        super(request, charset);
        requestIsEmpty(request);
    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param headers 请求头
     *
     * @throws UnsupportedEncodingException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(String request, Header... headers) throws UnsupportedEncodingException {
        super(request);
        requestIsEmpty(request);
        if (null == headers) {
            this.headers = Arrays.asList(headers);
        }
    }

    /**
     * 构造器
     *
     * @param request 请求体
     * @param headers 请求头
     *
     * @throws UnsupportedEncodingException 不支持默认的HTTP字符集
     */
    public EcHttpStringEntityDTO(String request, Map<String, String> headers) throws UnsupportedEncodingException {
        super(request);
        requestIsEmpty(request);
        this.headers = new ArrayList<>();
        for (String key : headers.keySet()) {
            this.headers.add(new BasicHeader(key, headers.get(key)));
        }
    }

    /**
     * 获取请求头集
     *
     * @return 请求头集
     */
    public List<Header> getHeaders() {
        return headers;
    }

    /**
     * 添加请求头
     *
     * @param header 请求头
     */
    public void addHeader(Header header) {
        if (null == this.headers) {
            this.headers = new ArrayList<>();
        }
        this.headers.add(header);
    }

    /**
     * 设置请求头集
     *
     * @param headers 请求头集
     */
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    /**
     * 设置请求头集
     *
     * @param headers 请求头集
     */
    public void setHeaders(Map<String, String> headers) {
        for (String key : headers.keySet()) {
            addHeader(new BasicHeader(key, headers.get(key)));
        }
    }


}
