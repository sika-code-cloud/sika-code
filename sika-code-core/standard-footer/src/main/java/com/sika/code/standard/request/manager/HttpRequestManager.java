package com.sika.code.standard.request.manager;

import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 请求工具类
 *
 * @author daiqi
 * @create 2019-07-05 13:22
 */
public class HttpRequestManager {

    public static final String HTTP_HEADER_NAME = "request.httpHeaders";


    /**  
     * <p>
     * 将httpHeads对象放入Local对象中
     * </p>
     *   
     * @param httpHeaders
     * @return void
     * @author daiqi 
     * @date 2019/7/5 13:49
     */  
    public static void addHttpHeadersToLocal(HttpHeaders httpHeaders) {
        ThreadLocalManager.setThreadLocalAndInheritable(HTTP_HEADER_NAME, httpHeaders);
    }

    /**  
     * <p>
     * 从local中获取HttpHeads
     * 此方法不管是当前线程还是子线程都能获取到放入Local中的HttpHeads对象
     * </p>
     *   
     * @param
     * @return org.springframework.http.HttpHeaders
     * @author daiqi 
     * @date 2019/7/5 13:50
     */  
    public static HttpHeaders getHttpHeadersFromLocal() {
        return (HttpHeaders) ThreadLocalManager.getThreadLocalAndInheritable(HTTP_HEADER_NAME);
    }

    /**
     * <p>
     * 将httpServletRequest请求头的内容转换为HttpHeaders
     * </p>
     *
     * @param httpServletRequest
     * @return HttpHeaders
     * @author daiqi
     * @date 2019/1/9 9:39
     */
    public static HttpHeaders convertRequestHeader(HttpServletRequest httpServletRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                if (httpHeaders.containsKey(name)) {
                    continue;
                }
                Enumeration<String> values = httpServletRequest.getHeaders(name);
                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    httpHeaders.add(name, value);
                }
            }
        }
        return httpHeaders;
    }
}
