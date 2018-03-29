package com.dq.easy.user.pojo.vo;

import javax.persistence.Column;
import com.dq.easy.cloud.module.basic.pojo.vo.DqBaseVO;
import java.util.Date;

/**
 * 描述：用户视图类
 * @author THINK
 * @date 2018-03-28 13:52:10
 */
public class UserVO extends DqBaseVO{
	
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

	/** 获取用户名 */
	public String getUserName() {
		return this.userName;
	}

	/** 设置用户名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 获取用户昵称 */
	public String getNickName() {
		return this.nickName;
	}

	/** 设置用户昵称 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/** 获取用户名字 */
	public String getName() {
		return this.name;
	}

	/** 设置用户名字 */
	public void setName(String name) {
		this.name = name;
	}

	/** 获取用户邮箱 */
	public String getEmail() {
		return this.email;
	}

	/** 设置用户邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 获取电话 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/** 设置电话 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** 获取微信号 */
	public String getWechatNumber() {
		return this.wechatNumber;
	}

	/** 设置微信号 */
	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	/** 获取 */
	public String getUserType() {
		return this.userType;
	}

	/** 设置 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/** 获取微信头像 */
	public String getHeadImg() {
		return this.headImg;
	}

	/** 设置微信头像 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/** 获取openid */
	public String getOpenId() {
		return this.openId;
	}

	/** 设置openid */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/** 获取用户密码 */
	public String getPassword() {
		return this.password;
	}

	/** 设置用户密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 获取1男 2 女  3未知 */
	public Integer getSex() {
		return this.sex;
	}

	/** 设置1男 2 女  3未知 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** 获取是否冻结 1冻结 0 未冻结  */
	public Integer getIsFrozen() {
		return this.isFrozen;
	}

	/** 设置是否冻结 1冻结 0 未冻结  */
	public void setIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
	}

	/** 获取是否管理员 1 是 0 否 */
	public Integer getIsManager() {
		return this.isManager;
	}

	/** 设置是否管理员 1 是 0 否 */
	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	/** 获取是否关注公众号 1 是 0 否 */
	public Integer getIsSubscribe() {
		return this.isSubscribe;
	}

	/** 设置是否关注公众号 1 是 0 否 */
	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

}
