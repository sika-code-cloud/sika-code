package com.dq.easy.user.pojo.bo;

import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.user.pojo.dto.UserDTO;

/**
 * 描述：用户业务逻辑类
 * @author THINK
 * @date 2018-03-27 16:37:02
 */
public class UserBO extends DqBaseBO{
	/** 用户业务逻辑类数据传输类 */
	private UserDTO userDTO;

	/** 获取用户业务逻辑类数据传输类 */
	public UserDTO getUserDTO() {
		return this.userDTO;
	}

	/** 设置用户业务逻辑类数据传输类 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
