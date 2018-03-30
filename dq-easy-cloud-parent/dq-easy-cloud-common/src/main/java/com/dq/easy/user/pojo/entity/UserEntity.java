package com.dq.easy.user.pojo.entity;

import javax.persistence.Column;
import java.util.Date;
import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;

/**
 * 描述：用户持久化类
 * @author THINK
 * @date 2018-03-29 11:31:09
 */
public class UserEntity extends DqBaseEntity{
	
	/** 登陆名 */
	@Column(name = "LOGIN_NAME")
	public String loginName;
	/** 密码 */
	@Column(name = "USER_PASSWORD")
	public String userPassword;
	/** 邮箱 */
	@Column(name = "USER_EMAIL")
	public String userEmail;
	/** 用户唯一编码 */
	@Column(name = "USER_CODE")
	public String userCode;
	/** 用户昵称 */
	@Column(name = "USER_NICKNAME")
	public String userNickname;
	/** 用户等级 */
	@Column(name = "USER_RANK")
	public String userRank;
	/** 用户类型 见码表100 */
	@Column(name = "USER_TYPE")
	public Integer userType;
	/** 中文姓名 */
	@Column(name = "USER_NAME_CN")
	public String userNameCn;
	/** 英文姓名 */
	@Column(name = "USER_NAME_EN")
	public String userNameEn;
	/** 头像URL */
	@Column(name = "USER_HEADURL")
	public String userHeadurl;
	/** 性别 */
	@Column(name = "USER_GENDER")
	public String userGender;
	/** 电话 */
	@Column(name = "USER_PHONE")
	public String userPhone;
	/** 所属国家 */
	@Column(name = "USER_LOCATION")
	public Integer userLocation;
	/** 注册省 */
	@Column(name = "USER_LOCATION_PROVINCE")
	public String userLocationProvince;
	/** 注册城市 */
	@Column(name = "USER_LOCATION_CITY")
	public String userLocationCity;
	/** 注册地址 */
	@Column(name = "USER_LOCATION_ADDRESS")
	public String userLocationAddress;
	/** 注册邮编 */
	@Column(name = "USER_LOCATION_ZIPCODE")
	public String userLocationZipcode;
	/** 支付币种 */
	@Column(name = "PAY_CURRENCY")
	public Integer payCurrency;
	/** 用户状态 */
	@Column(name = "USER_STATUS")
	public Integer userStatus;
	/** 是否自动登陆 0 ：否 1：是 */
	@Column(name = "USER_IS_AUTO_LOGIN")
	public Integer userIsAutoLogin;
	/** 验证时间 */
	@Column(name = "CHECK_TIME")
	public Date checkTime;
	/** 最后登陆时间 */
	@Column(name = "LASTLOGIN_TIME")
	public Date lastloginTime;
	/** 账户编号 */
	@Column(name = "ACCOUNT_CODE")
	public String accountCode;
	/** 实体ID */
	@Column(name = "ENTITY_ID")
	public Integer entityId;
	/** 组织机构ID */
	@Column(name = "GROUP_ID")
	public Integer groupId;
	/** UUID */
	@Column(name = "USER_UUID")
	public String userUuid;
	/** 用户锁定 */
	@Column(name = "IS_ACTIVE")
	public Integer isActive;
	@Column(name = "DEL_FLAG")
	public Integer delFlag;
	@Column(name = "CREATE_DATE_TIME")
	public Date createDateTime;
	@Column(name = "CREATER")
	public Integer creater;
	@Column(name = "UPDATE_DATE_TIME")
	public Date updateDateTime;
	@Column(name = "UPDATER")
	public Integer updater;
		
	/** 获取登陆名 */
	public String getLoginName() {
		return this.loginName;
	}

	/** 设置登陆名 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/** 构建登陆名 */
	public UserEntity buildLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}

	/** 获取密码 */
	public String getUserPassword() {
		return this.userPassword;
	}

	/** 设置密码 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/** 构建密码 */
	public UserEntity buildUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	/** 获取邮箱 */
	public String getUserEmail() {
		return this.userEmail;
	}

	/** 设置邮箱 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/** 构建邮箱 */
	public UserEntity buildUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}

	/** 获取用户唯一编码 */
	public String getUserCode() {
		return this.userCode;
	}

	/** 设置用户唯一编码 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/** 构建用户唯一编码 */
	public UserEntity buildUserCode(String userCode) {
		this.userCode = userCode;
		return this;
	}

	/** 获取用户昵称 */
	public String getUserNickname() {
		return this.userNickname;
	}

	/** 设置用户昵称 */
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	/** 构建用户昵称 */
	public UserEntity buildUserNickname(String userNickname) {
		this.userNickname = userNickname;
		return this;
	}

	/** 获取用户等级 */
	public String getUserRank() {
		return this.userRank;
	}

	/** 设置用户等级 */
	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}

	/** 构建用户等级 */
	public UserEntity buildUserRank(String userRank) {
		this.userRank = userRank;
		return this;
	}

	/** 获取用户类型 见码表100 */
	public Integer getUserType() {
		return this.userType;
	}

	/** 设置用户类型 见码表100 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/** 构建用户类型 见码表100 */
	public UserEntity buildUserType(Integer userType) {
		this.userType = userType;
		return this;
	}

	/** 获取中文姓名 */
	public String getUserNameCn() {
		return this.userNameCn;
	}

	/** 设置中文姓名 */
	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	/** 构建中文姓名 */
	public UserEntity buildUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
		return this;
	}

	/** 获取英文姓名 */
	public String getUserNameEn() {
		return this.userNameEn;
	}

	/** 设置英文姓名 */
	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	/** 构建英文姓名 */
	public UserEntity buildUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
		return this;
	}

	/** 获取头像URL */
	public String getUserHeadurl() {
		return this.userHeadurl;
	}

	/** 设置头像URL */
	public void setUserHeadurl(String userHeadurl) {
		this.userHeadurl = userHeadurl;
	}

	/** 构建头像URL */
	public UserEntity buildUserHeadurl(String userHeadurl) {
		this.userHeadurl = userHeadurl;
		return this;
	}

	/** 获取性别 */
	public String getUserGender() {
		return this.userGender;
	}

	/** 设置性别 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/** 构建性别 */
	public UserEntity buildUserGender(String userGender) {
		this.userGender = userGender;
		return this;
	}

	/** 获取电话 */
	public String getUserPhone() {
		return this.userPhone;
	}

	/** 设置电话 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/** 构建电话 */
	public UserEntity buildUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}

	/** 获取所属国家 */
	public Integer getUserLocation() {
		return this.userLocation;
	}

	/** 设置所属国家 */
	public void setUserLocation(Integer userLocation) {
		this.userLocation = userLocation;
	}

	/** 构建所属国家 */
	public UserEntity buildUserLocation(Integer userLocation) {
		this.userLocation = userLocation;
		return this;
	}

	/** 获取注册省 */
	public String getUserLocationProvince() {
		return this.userLocationProvince;
	}

	/** 设置注册省 */
	public void setUserLocationProvince(String userLocationProvince) {
		this.userLocationProvince = userLocationProvince;
	}

	/** 构建注册省 */
	public UserEntity buildUserLocationProvince(String userLocationProvince) {
		this.userLocationProvince = userLocationProvince;
		return this;
	}

	/** 获取注册城市 */
	public String getUserLocationCity() {
		return this.userLocationCity;
	}

	/** 设置注册城市 */
	public void setUserLocationCity(String userLocationCity) {
		this.userLocationCity = userLocationCity;
	}

	/** 构建注册城市 */
	public UserEntity buildUserLocationCity(String userLocationCity) {
		this.userLocationCity = userLocationCity;
		return this;
	}

	/** 获取注册地址 */
	public String getUserLocationAddress() {
		return this.userLocationAddress;
	}

	/** 设置注册地址 */
	public void setUserLocationAddress(String userLocationAddress) {
		this.userLocationAddress = userLocationAddress;
	}

	/** 构建注册地址 */
	public UserEntity buildUserLocationAddress(String userLocationAddress) {
		this.userLocationAddress = userLocationAddress;
		return this;
	}

	/** 获取注册邮编 */
	public String getUserLocationZipcode() {
		return this.userLocationZipcode;
	}

	/** 设置注册邮编 */
	public void setUserLocationZipcode(String userLocationZipcode) {
		this.userLocationZipcode = userLocationZipcode;
	}

	/** 构建注册邮编 */
	public UserEntity buildUserLocationZipcode(String userLocationZipcode) {
		this.userLocationZipcode = userLocationZipcode;
		return this;
	}

	/** 获取支付币种 */
	public Integer getPayCurrency() {
		return this.payCurrency;
	}

	/** 设置支付币种 */
	public void setPayCurrency(Integer payCurrency) {
		this.payCurrency = payCurrency;
	}

	/** 构建支付币种 */
	public UserEntity buildPayCurrency(Integer payCurrency) {
		this.payCurrency = payCurrency;
		return this;
	}

	/** 获取用户状态 */
	public Integer getUserStatus() {
		return this.userStatus;
	}

	/** 设置用户状态 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	/** 构建用户状态 */
	public UserEntity buildUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
		return this;
	}

	/** 获取是否自动登陆 0 ：否 1：是 */
	public Integer getUserIsAutoLogin() {
		return this.userIsAutoLogin;
	}

	/** 设置是否自动登陆 0 ：否 1：是 */
	public void setUserIsAutoLogin(Integer userIsAutoLogin) {
		this.userIsAutoLogin = userIsAutoLogin;
	}

	/** 构建是否自动登陆 0 ：否 1：是 */
	public UserEntity buildUserIsAutoLogin(Integer userIsAutoLogin) {
		this.userIsAutoLogin = userIsAutoLogin;
		return this;
	}

	/** 获取验证时间 */
	public Date getCheckTime() {
		return this.checkTime;
	}

	/** 设置验证时间 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/** 构建验证时间 */
	public UserEntity buildCheckTime(Date checkTime) {
		this.checkTime = checkTime;
		return this;
	}

	/** 获取最后登陆时间 */
	public Date getLastloginTime() {
		return this.lastloginTime;
	}

	/** 设置最后登陆时间 */
	public void setLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	/** 构建最后登陆时间 */
	public UserEntity buildLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
		return this;
	}

	/** 获取账户编号 */
	public String getAccountCode() {
		return this.accountCode;
	}

	/** 设置账户编号 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	/** 构建账户编号 */
	public UserEntity buildAccountCode(String accountCode) {
		this.accountCode = accountCode;
		return this;
	}

	/** 获取实体ID */
	public Integer getEntityId() {
		return this.entityId;
	}

	/** 设置实体ID */
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/** 构建实体ID */
	public UserEntity buildEntityId(Integer entityId) {
		this.entityId = entityId;
		return this;
	}

	/** 获取组织机构ID */
	public Integer getGroupId() {
		return this.groupId;
	}

	/** 设置组织机构ID */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/** 构建组织机构ID */
	public UserEntity buildGroupId(Integer groupId) {
		this.groupId = groupId;
		return this;
	}

	/** 获取UUID */
	public String getUserUuid() {
		return this.userUuid;
	}

	/** 设置UUID */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/** 构建UUID */
	public UserEntity buildUserUuid(String userUuid) {
		this.userUuid = userUuid;
		return this;
	}

	/** 获取用户锁定 */
	public Integer getIsActive() {
		return this.isActive;
	}

	/** 设置用户锁定 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	/** 构建用户锁定 */
	public UserEntity buildIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public UserEntity buildDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public UserEntity buildCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
		return this;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public UserEntity buildCreater(Integer creater) {
		this.creater = creater;
		return this;
	}

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public UserEntity buildUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
		return this;
	}

	public Integer getUpdater() {
		return this.updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public UserEntity buildUpdater(Integer updater) {
		this.updater = updater;
		return this;
	}

}
