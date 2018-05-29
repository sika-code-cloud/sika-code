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
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer uid;
    private String name;
    private String password;
    private String salt;
    private Integer state;
    private String username;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        "uid=" + uid +
        ", name=" + name +
        ", password=" + password +
        ", salt=" + salt +
        ", state=" + state +
        ", username=" + username +
        "}";
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
    
    public List<SysRole> getRoleList() {
    	return null;
    }
}
