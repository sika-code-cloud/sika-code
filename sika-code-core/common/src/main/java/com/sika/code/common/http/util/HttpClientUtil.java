package com.sika.code.common.http.util;

/**
 * User : LiuKe
 * Date : 2017/1/26
 * Time : 15:15
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private static String DEFAULT_CHARSET = "UTF-8";
    private static int DEFAULT_PORT = 80;

    public HttpClientUtil() {
    }

    public static String get(String url) {
        return get(url, DEFAULT_CHARSET);
    }

    public static String get(String url, String charSet) {
        String responseStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet e = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(e);

            try {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    responseStr = EntityUtils.toString(entity, charSet);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException var28) {
            log.error("Http get request error", var28);
        } catch (IOException var30) {
            log.error("Http get request error", var30);
        } finally {
            try {
                httpclient.close();
            } catch (IOException var26) {
                log.error("Httpclient close error", var26);
            }

        }

        return responseStr;
    }

    public static String post(String url, Map<String, String> paramterMap) throws Exception {
        return post(url, paramterMap, DEFAULT_CHARSET);
    }

    public static String post(String url, Map<String, String> paramterMap, String charSet) throws Exception {
        String responseStr = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        ArrayList params = new ArrayList();
        Set set = paramterMap.entrySet();
        Iterator e = set.iterator();

        while(e.hasNext()) {
            Map.Entry response = (Map.Entry)e.next();
            params.add(new BasicNameValuePair((String)response.getKey(), (String)response.getValue()));
        }

        try {
            UrlEncodedFormEntity e1 = new UrlEncodedFormEntity(params, charSet);
            httpPost.setEntity(e1);
            CloseableHttpResponse response1 = httpClient.execute(httpPost);

            try {
                HttpEntity entity = response1.getEntity();
                if(entity != null) {
                    responseStr = EntityUtils.toString(entity, charSet);
                }
            } finally {
                response1.close();
            }
        } catch (ClientProtocolException var32) {
            log.error("Http post request error", var32);
        } catch (UnsupportedEncodingException var33) {
            log.error("Http post request error", var33);
        } catch (IOException var34) {
            log.error("Http post request error", var34);
        } finally {
            try {
                httpClient.close();
            } catch (IOException var30) {
                log.error("Httpclient close error", var30);
            }

        }

        return responseStr;
    }

    public static String purge(String varnishIp, String url) {
        String responseStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpClientUtil.HttpPurge e = new HttpClientUtil.HttpPurge(url);
            RequestConfig requestConfig = RequestConfig.custom().setProxy(new HttpHost(varnishIp, DEFAULT_PORT)).build();
            e.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(e);

            try {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    responseStr = EntityUtils.toString(entity, DEFAULT_CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException var29) {
            log.error("Http purge request error", var29);
        } catch (IOException var31) {
            log.error("Http purge request error", var31);
        } finally {
            try {
                httpclient.close();
            } catch (IOException var27) {
                log.error("Httpclient close error", var27);
            }

        }

        return responseStr;
    }

    static class HttpPurge extends HttpRequestBase {
        public static final String METHOD_NAME = "PURGE";

        public HttpPurge() {
        }

        public HttpPurge(URI uri) {
            this.setURI(uri);
        }

        public HttpPurge(String uri) {
            this.setURI(URI.create(uri));
        }

        public String getMethod() {
            return "PURGE";
        }
    }
}
