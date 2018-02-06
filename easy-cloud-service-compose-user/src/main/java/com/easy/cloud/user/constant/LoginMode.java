package com.easy.cloud.user.constant;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant;

/**
 * 
 * <p>
 * 登录方式常量类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午11:26:15
 */
public class LoginMode extends DqBaseConstant{
	/** 登录方式---使用用户名和密码---1 */
	public static final int LOGIN_BY_USERNAME_AND_PASSWORD = 1;
	/** 登录方式---使用用户名和密码---2 */
	public static final int LOGIN_BY_EMAIL_AND_PASSWORD = 2;
	
	/**
	 * 
	 * <p>
	 * 登录方式是通过用户名和密码登录
	 * </p>
	 *
	 * @param loginMode : int : 登录方式
	 * @return boolean
	 * @author daiqi
	 * 创建时间    2018年2月6日 上午11:31:27
	 */
	public static boolean isLoginByUsernameAndPassword(int loginMode){
		if(loginMode == LOGIN_BY_USERNAME_AND_PASSWORD){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * 登录方式是通过邮箱和密码登录
	 * </p>
	 * 
	 * @param loginMode : int : 登录方式
	 * @return boolean
	 * @author daiqi
	 * 创建时间    2018年2月6日 上午11:31:27
	 */
	public static boolean isLoginByEmailAndPassword(int loginMode){
		if(loginMode == LOGIN_BY_EMAIL_AND_PASSWORD){
			return true;
		}
		return false;
	}
	
}
