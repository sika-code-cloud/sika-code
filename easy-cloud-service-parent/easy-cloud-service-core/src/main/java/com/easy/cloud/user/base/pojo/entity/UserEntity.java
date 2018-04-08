package com.easy.cloud.user.base.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.user.base.constant.UserConstant;

@Entity
@Table(name = "easy_user_info")
public class UserEntity extends EcBaseEntity {
	private String userName;
	private String password;
	private Integer sex;
	private String phoneNumber;
	private String email;
	
	@PrePersist
	public void userPrepersist(){
		if(EcStringUtils.isEmpty(this.userName)){
			this.userName = EcStringUtils.EMPTY;
		}
		if(EcBaseUtils.isNull(this.sex)){
			this.sex = UserConstant.SEX_MAN;
		}
		if(EcStringUtils.isEmpty(this.password)){
			this.password = EcStringUtils.EMPTY;
		}
		if(EcStringUtils.isEmpty(this.phoneNumber)){
//			this.phoneNumber = EcStringUtils.EMPTY;
		}
		if(EcStringUtils.isEmpty(this.email)){
//			this.email = EcStringUtils.EMPTY;
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
		return EcJSONUtils.parseObject(this, String.class);
	}
}
