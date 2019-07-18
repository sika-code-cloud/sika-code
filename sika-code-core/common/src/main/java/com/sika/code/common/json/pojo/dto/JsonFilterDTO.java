package com.sika.code.common.json.pojo.dto;

/**
 * json过滤器数据传输对象
 *
 * @author daiqi
 * @date 2018年2月28日 下午8:16:47
 */
public class JsonFilterDTO {
    /**
     * 过滤设置得键
     */
    private String key;
    /**
     * json过滤得目标对象class
     */
    private Class<?> targetObjClass;
    /**
     * json过滤得目标对象的属性名
     */
    private String targetObjPropertyName;
    /**
     * 过滤得属性class
     */
    private Class<?> filterPropertyClass;

    public JsonFilterDTO(String key, Class<?> targetObjClass, String targetObjPropertyName,
                         Class<?> filterPropertyClass) {
        super();
        this.key = key;
        this.targetObjClass = targetObjClass;
        this.targetObjPropertyName = targetObjPropertyName;
        this.filterPropertyClass = filterPropertyClass;
    }

    public JsonFilterDTO(String key, Class<?> targetObjClass, String targetObjPropertyName) {
        super();
        this.key = key;
        this.targetObjClass = targetObjClass;
        this.targetObjPropertyName = targetObjPropertyName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class<?> getTargetObjClass() {
        return targetObjClass;
    }

    public void setTargetObjClass(Class<?> targetObjClass) {
        this.targetObjClass = targetObjClass;
    }

    public String getTargetObjPropertyName() {
        return targetObjPropertyName;
    }

    public void setTargetObjPropertyName(String targetObjPropertyName) {
        this.targetObjPropertyName = targetObjPropertyName;
    }

    public Class<?> getFilterPropertyClass() {
        return filterPropertyClass;
    }

    public void setFilterPropertyClass(Class<?> filterPropertyClass) {
        this.filterPropertyClass = filterPropertyClass;
    }


}
