package com.easy.cloud.user.vo;

import com.dq.easy.cloud.model.basic.vo.DqBaseVo;

public class UserVo extends DqBaseVo{
	private String userName;
	private String password;
	private int status;
	
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
