package com.dq.easy.user.pojo.bo;

import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.user.pojo.dto.UserDTO;

/**
 * 描述：用户业务逻辑类
 * @author THINK
 * @date 2018-03-28 10:07:37
 */
public class UserBO extends DqBaseBO{
	
	/** 数据传输类 */
	private UserDTO userDTO;
		
	public UserBO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	/** 构建数据传输类 */
	public UserBO buildUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
		return this;
	}

}
