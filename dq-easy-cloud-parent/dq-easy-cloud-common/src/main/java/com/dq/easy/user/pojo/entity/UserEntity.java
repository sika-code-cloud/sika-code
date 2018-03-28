package com.dq.easy.user.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 描述：用户持久化类
 * @author THINK
 * @date 2018-03-28 10:07:37
 */
@Table(name = "easy_user_info")
@Entity
public class UserEntity extends DqBaseBO{
	
	/** 创建日期 */
	@Column(name = "create_date")
	public Date createDate;
	/** 用户名 */
	@Column(name = "user_name")
	public String userName;
	/** 用户昵称 */
	@Column(name = "nick_name")
	public String nickName;
	/** 用户名字 */
	@Column(name = "name")
	public String name;
	/** 用户邮箱 */
	@Column(name = "email")
	public String email;
	/** 电话 */
	@Column(name = "phone_number")
	public String phoneNumber;
	/** 微信号 */
	@Column(name = "wechat_number")
	public String wechatNumber;
	/**  */
	@Column(name = "user_type")
	public String userType;
	/** 微信头像 */
	@Column(name = "head_img")
	public String headImg;
	/** openid */
	@Column(name = "open_id")
	public String openId;
	/** 用户密码 */
	@Column(name = "password")
	public String password;
	/** 1男 2 女  3未知 */
	@Column(name = "sex")
	public Integer sex;
	/** 是否冻结 1冻结 0 未冻结  */
	@Column(name = "is_frozen")
	public Integer isFrozen;
	/** 是否管理员 1 是 0 否 */
	@Column(name = "is_manager")
	public Integer isManager;
	/** 是否关注公众号 1 是 0 否 */
	@Column(name = "is_subscribe")
	public Integer isSubscribe;
		
	
	/** 获取创建日期 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 设置创建日期 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 构建创建日期 */
	public UserEntity buildCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
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

	/** 获取 */
	public String getUserType() {
		return this.userType;
	}

	/** 设置 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/** 构建 */
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

	/** 获取1男 2 女  3未知 */
	public Integer getSex() {
		return this.sex;
	}

	/** 设置1男 2 女  3未知 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** 构建1男 2 女  3未知 */
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
