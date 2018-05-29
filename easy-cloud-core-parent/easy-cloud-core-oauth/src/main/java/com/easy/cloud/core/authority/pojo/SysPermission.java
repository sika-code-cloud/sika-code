package com.easy.cloud.core.authority.pojo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean available;
    private String name;
    private Long parentId;
    private String parentIds;
    private String permission;
    private String resourceType;
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "id=" + id +
        ", available=" + available +
        ", name=" + name +
        ", parentId=" + parentId +
        ", parentIds=" + parentIds +
        ", permission=" + permission +
        ", resourceType=" + resourceType +
        ", url=" + url +
        "}";
    }
}
