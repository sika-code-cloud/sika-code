package com.easy.cloud.core.authority.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean available;
    private String description;
    private String role;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", available=" + available +
        ", description=" + description +
        ", role=" + role +
        "}";
    }
    
    public List<SysPermission> getPermissions() {
    	return null;
    }
}
