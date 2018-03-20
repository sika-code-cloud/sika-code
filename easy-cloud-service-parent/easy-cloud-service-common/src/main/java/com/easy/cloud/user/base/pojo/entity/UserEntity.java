package com.easy.cloud.user.base.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.dq.easy.cloud.model.basic.pojo.entity.DqBaseEntity;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.easy.cloud.user.base.constant.UserConstant;

@Entity
@Table(name = "easy_user_info")
public class UserEntity extends DqBaseEntity {
	private String userName;
	private String password;
	private Integer sex;
	private String phoneNumber;
	private String email;
	
	@PrePersist
	public void userPrepersist(){
		if(DqStringUtils.isEmpty(this.userName)){
			this.userName = DqStringUtils.EMPTY;
		}
		if(DqBaseUtils.isNull(this.sex)){
			this.sex = UserConstant.SEX_MAN;
		}
		if(DqStringUtils.isEmpty(this.password)){
			this.password = DqStringUtils.EMPTY;
		}
		if(DqStringUtils.isEmpty(this.phoneNumber)){
//			this.phoneNumber = DqStringUtils.EMPTY;
		}
		if(DqStringUtils.isEmpty(this.email)){
//			this.email = DqStringUtils.EMPTY;
		}
	}
	
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return DqJSONUtils.parseObject(this, String.class);
	}
}
