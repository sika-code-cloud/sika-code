package com.easy.cloud.user.base.pojo.dto;

import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;

/**
 * 用户数据传输对象 --- 为了实现字段复用 直接继承了UserEntity
 * @author daiqi
 * @date 2018年1月8日 下午6:29:01
 */
public class UserDTO extends DqBaseDTO{
	private String userName;
	private String password;
	private Integer sex;
	private String phoneNumber;
	private String email;
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
	
	
}
