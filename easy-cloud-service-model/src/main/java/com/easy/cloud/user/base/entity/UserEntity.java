package com.easy.cloud.user.base.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dq.easy.cloud.model.basic.entity.DqBaseEntity;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;

@Entity
@Table(name = "easy_user_info")
public class UserEntity extends DqBaseEntity{
	private String userName;
	private String password;
	private Integer sex;
	
	
	public UserEntity() {
		super();
	}
	public UserEntity(String userName, String password, int sex) {
		super();
		this.userName = userName;
		this.password = password;
		this.sex = sex;
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
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return DqJSONUtils.parseObject(this, String.class);
	}
}
