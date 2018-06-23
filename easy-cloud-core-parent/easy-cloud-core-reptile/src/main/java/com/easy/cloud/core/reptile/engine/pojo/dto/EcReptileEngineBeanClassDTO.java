package com.easy.cloud.core.reptile.engine.pojo.dto;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.JavassistDynamicBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-06-12 15:27
 */
public class EcReptileEngineBeanClassDTO {
    private EcReptileDynamicBeanDTO reptileDynamicBeanDTO;
    private GeccoEngine geccoEngine;
    private List<JavassistDynamicBean> javassistDynamicBeans;
    private Map<String, Class<?>> ruleBeanClazzs;

    {
        javassistDynamicBeans = new ArrayList<>();
        ruleBeanClazzs = new LinkedHashMap<>();
    }

    private EcReptileEngineBeanClassDTO() {

    }

    private static class SingletonHolder {
        private final static EcReptileEngineBeanClassDTO instance = new EcReptileEngineBeanClassDTO();
    }

    public static EcReptileEngineBeanClassDTO getInstance() {
        return SingletonHolder.instance;
    }

    public GeccoEngine getGeccoEngine() {
        return geccoEngine;
    }

    public void setGeccoEngine(GeccoEngine geccoEngine) {
        this.geccoEngine = geccoEngine;
    }

    public EcReptileDynamicBeanDTO getReptileDynamicBeanDTO() {
        return reptileDynamicBeanDTO;
    }

    public void setReptileDynamicBeanDTO(EcReptileDynamicBeanDTO reptileDynamicBeanDTO) {
        this.reptileDynamicBeanDTO = reptileDynamicBeanDTO;
    }

    public List<JavassistDynamicBean> getJavassistDynamicBeans() {
        return javassistDynamicBeans;
    }

    public void setJavassistDynamicBeans(List<JavassistDynamicBean> javassistDynamicBeans) {
        this.javassistDynamicBeans = javassistDynamicBeans;
    }

    public Map<String, Class<?>> getRuleBeanClazzs() {
        return ruleBeanClazzs;
    }

    public void setRuleBeanClazzs(Map<String, Class<?>> ruleBeanClazzs) {
        this.ruleBeanClazzs = ruleBeanClazzs;
    }

    public void clearJavassistDynamicBeans() {
        this.javassistDynamicBeans.clear();
    }

    public void addJavassistDynamicBean(JavassistDynamicBean javassistDynamicBean) {
        this.javassistDynamicBeans.add(javassistDynamicBean);
    }

    public void putRuleBeanClazz(Class<?> ruleBeanClazz) {
        this.ruleBeanClazzs.put(ruleBeanClazz.getName(), ruleBeanClazz);
    }

    public Class<?> removeRuleBeanClazz(String className) {
        return this.ruleBeanClazzs.remove(className);
    }

    public Class<?> getRuleBeanClazz(String className) {
        return this.ruleBeanClazzs.get(className);
    }

    public void clearRuleBeanClazzs() {
        this.ruleBeanClazzs.clear();
    }
}
