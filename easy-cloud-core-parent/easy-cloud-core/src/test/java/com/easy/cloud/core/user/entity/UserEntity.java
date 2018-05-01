package com.easy.cloud.core.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;

//@Entity
//@Table(name="test_user")
public class UserEntity extends EcBaseEntity{
	private String userName;
	private String password;
	private int status;
	
	
	public UserEntity() {
		super();
	}
	public UserEntity(String userName, String password, int status) {
		super();
		this.userName = userName;
		this.password = password;
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserEntity [getUserName()=" + getUserName() + ", getPassword()=" + getPassword() + ", getStatus()="
				+ getStatus() + ", getId()=" + getId() + ", getCreateDate()=" + getCreateDate() + ", getUpdateDate()="
				+ getUpdateDate() + ", getVersion()=" + getVersion() + ", getCreateBy()=" + getCreateBy()
				+ ", getUpdateBy()=" + getUpdateBy() + "]";
	}
	
	
}
