package com.sika.code.common.util;


import com.sika.code.basic.constant.BaseConstant;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.string.constant.StringConstant;
import com.sika.code.common.string.util.StringUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * XML工具类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午12:37:00
 */
public class XMLUtil {
    /**
     * <p>
     * 解析xml字符串并转化为Map
     * </p>
     *
     * @param content : String : 解析的内容
     * @return Map<String, Object>
     * @author daiqi 创建时间 2018年2月23日 下午12:47:17
     */
    public static Map<String, Object> getMapFromXmlStr(String content) {
        if (StringUtil.isEmpty(content)) {
            return null;
        }
        try {
            InputStream in = new ByteArrayInputStream(content.getBytes(BaseConstant.Charset.UTF_8));
            return getMapFromInputStream(in, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * <p>
     * 解析xml流并转化为Map
     * </p>
     *
     * @param inputStream : InputStream : 解析的xml流
     * @return Map<String, Object>
     * @author daiqi 创建时间 2018年2月23日 下午12:47:17
     */
    public static Map<String, Object> getMapFromInputStream(InputStream inputStream) {
        if (BaseUtil.isNull(inputStream)) {
            return null;
        }
        try {
            return getMapFromInputStream(inputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * <p>
     * 将xml流中的信息放入map中
     * </p>
     *
     * @param inputStream : InputStream : xml输入流
     * @param paramsMap   : Map<String, Object> : xml结果容器
     * @return Map<String, Object>
     * @throws IOException
     * @author daiqi 创建时间 2018年2月23日 下午12:49:06
     */
    public static Map<String, Object> getMapFromInputStream(InputStream inputStream, Map<String, Object> paramsMap)
            throws IOException {
        if (null == paramsMap) {
            paramsMap = new HashMap<>();
        }
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(inputStream);
            Element root = doc.getRootElement();
            List<Element> list = root.getChildren();
            Iterator<Element> it = list.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String k = e.getName();
                Object v = StringConstant.Symbol.EMPTY;
                List<Element> children = e.getChildren();
                if (children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildren(children);
                }
                paramsMap.put(k, v);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return paramsMap;
    }

    /**
     * <p>
     * 获取子结点的xml
     * </p>
     *
     * @param children : List<Element> : 子节点元素
     * @return String : 拼接好的xml : 字符串
     * @author daiqi 创建时间 2018年2月23日 下午12:51:44
     */
    public static String getChildrenText(List<Element> children) {
        StringBuilder sb = StringUtil.newStringBuilder();
        if (!children.isEmpty()) {
            Iterator<Element> it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<Element> list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * 获取子结点的xml
     * </p>
     *
     * @param children : List<Element> : 子节点集合
     * @return Map<String, Object> : map
     * @author daiqi 创建时间 2018年2月23日 下午12:54:22
     */
    public static Map<String, Object> getChildren(List<Element> children) {
        Map<String, Object> retMap = new HashMap<>();
        if (!children.isEmpty()) {
            Iterator<Element> it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<Element> list = e.getChildren();
                if (!list.isEmpty()) {
                    retMap.put(name, getChildren(list));
                } else {
                    retMap.put(name, value);
                }
            }
        }
        return retMap;
    }

    /**
     * <p>
     * 将请求参数转换为xml格式的string
     * </p>
     *
     * @param parameters : Map<String, Object> : 请求参数
     * @return String : xml格式的string
     * @author daiqi 创建时间 2018年2月23日 下午12:52:45
     */
    public static String getXmlStrFromMap(Map<String, Object> parameters) {
        StringBuilder sb = StringUtil.newStringBuilder();
        sb.append("<xml>");
        for (String key : parameters.keySet()) {
            if (StringUtil.equalsIgnoreCase("attach", key) || StringUtil.equalsIgnoreCase("body", key)
                    || StringUtil.equalsIgnoreCase("sign", key)) {
                sb.append("<" + key + ">" + "<![CDATA[" + parameters.get(key) + "]]></" + key + ">");
            } else {
                sb.append("<" + key + ">" + parameters.get(key) + "</" + key + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

}
