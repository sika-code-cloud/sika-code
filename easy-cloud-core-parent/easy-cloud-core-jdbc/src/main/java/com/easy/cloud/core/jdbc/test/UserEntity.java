package com.easy.cloud.core.jdbc.test;

import javax.persistence.Entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：用户持久化类
 * 
 * @author THINK
 * @date 2018-05-03 19:48:40
 */
@Entity
public class UserEntity extends EcBaseEntity {
	/** 用户名 */
	private String userName;
	/** 用户昵称 */
	private String nickName;
	/** 用户名字 */
	private String name;
	/** 用户邮箱 */
	private String email;
	/** 电话 */
	private String phoneNumber;
	/** 微信号 */
	private String wechatNumber;
	private String userType;
	/** 微信头像 */
	private String headImg;
	/** openid */
	private String openId;
	/** 用户密码 */
	private String password;
	/** 性别 */
	private Integer sex;
	/** 是否冻结 1冻结 0 未冻结  */
	private Integer isFrozen;
	/** 是否管理员 1 是 0 否 */
	private Integer isManager;
	/** 是否关注公众号 1 是 0 否 */
	private Integer isSubscribe;
		
	public UserEntity() {
		super();
	}
	public UserEntity(Long id , String userName, String password, Integer status) {
		super();
		super.setId(id);
		this.userName = userName;
		this.password = password;
		super.setIsDeleted(status);
	}

	public UserEntity(String userName, String password, Integer status) {
		super();
		this.userName = userName;
		this.password = password;
		super.setIsDeleted(status);
	}

	/** 获取用户名 */
	public String getUserName() {
		return this.userName;
	}

	/** 设置用户名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 构建用户名 */
	public UserEntity buildUserName(String userName) {
		this.userName = userName;
		return this;
	}

	/** 获取用户昵称 */
	public String getNickName() {
		return this.nickName;
	}

	/** 设置用户昵称 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/** 构建用户昵称 */
	public UserEntity buildNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	/** 获取用户名字 */
	public String getName() {
		return this.name;
	}

	/** 设置用户名字 */
	public void setName(String name) {
		this.name = name;
	}

	/** 构建用户名字 */
	public UserEntity buildName(String name) {
		this.name = name;
		return this;
	}

	/** 获取用户邮箱 */
	public String getEmail() {
		return this.email;
	}

	/** 设置用户邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 构建用户邮箱 */
	public UserEntity buildEmail(String email) {
		this.email = email;
		return this;
	}

	/** 获取电话 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/** 设置电话 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** 构建电话 */
	public UserEntity buildPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	/** 获取微信号 */
	public String getWechatNumber() {
		return this.wechatNumber;
	}

	/** 设置微信号 */
	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	/** 构建微信号 */
	public UserEntity buildWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
		return this;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UserEntity buildUserType(String userType) {
		this.userType = userType;
		return this;
	}

	/** 获取微信头像 */
	public String getHeadImg() {
		return this.headImg;
	}

	/** 设置微信头像 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/** 构建微信头像 */
	public UserEntity buildHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	/** 获取openid */
	public String getOpenId() {
		return this.openId;
	}

	/** 设置openid */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/** 构建openid */
	public UserEntity buildOpenId(String openId) {
		this.openId = openId;
		return this;
	}

	/** 获取用户密码 */
	public String getPassword() {
		return this.password;
	}

	/** 设置用户密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 构建用户密码 */
	public UserEntity buildPassword(String password) {
		this.password = password;
		return this;
	}

	/** 获取性别 */
	public Integer getSex() {
		return this.sex;
	}

	/** 设置性别 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** 构建性别 */
	public UserEntity buildSex(Integer sex) {
		this.sex = sex;
		return this;
	}

	/** 获取是否冻结 1冻结 0 未冻结  */
	public Integer getIsFrozen() {
		return this.isFrozen;
	}

	/** 设置是否冻结 1冻结 0 未冻结  */
	public void setIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
	}

	/** 构建是否冻结 1冻结 0 未冻结  */
	public UserEntity buildIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
		return this;
	}

	/** 获取是否管理员 1 是 0 否 */
	public Integer getIsManager() {
		return this.isManager;
	}

	/** 设置是否管理员 1 是 0 否 */
	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	/** 构建是否管理员 1 是 0 否 */
	public UserEntity buildIsManager(Integer isManager) {
		this.isManager = isManager;
		return this;
	}

	/** 获取是否关注公众号 1 是 0 否 */
	public Integer getIsSubscribe() {
		return this.isSubscribe;
	}

	/** 设置是否关注公众号 1 是 0 否 */
	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	/** 构建是否关注公众号 1 是 0 否 */
	public UserEntity buildIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
		return this;
	}

}
