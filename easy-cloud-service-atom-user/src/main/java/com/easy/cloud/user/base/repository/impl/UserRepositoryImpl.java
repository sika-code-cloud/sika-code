package com.easy.cloud.user.base.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dq.easy.cloud.model.basic.repository.DqBaseRepository;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.jdbc.handler.DqJdbcTemplateHandler;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.base.repository.inf.UserRepository;

@Repository(value="userRepository")
public class UserRepositoryImpl extends DqBaseRepository implements UserRepository{

	@Override
	public UserEntity findUserById(Long id) {
		return DqJdbcTemplateHandler.findOne(UserEntity.class, id);
	}

	@Override
	public UserEntity saveUserInfo(UserEntity userEntity) {
		return save(userEntity);
	}
	/**
	 * 
	 * <p>根据条件获取用户信息</p>
	 *
	 * @param userQuery
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月8日 下午8:01:36
	 */
	private UserDTO findUserByCodition(UserQuery userQuery){
		StringBuilder sql = DqStringUtils.newStringBuilderDefault();
		List<Object> params = new ArrayList<>();
//		实体对应的表名
		String entityTableName = DqBaseUtils.getTableNameByEntityClass(UserEntity.class);
//		公共条件前缀
		String commonConditionPrefix = DqStringUtils.getDefaultStringBuilder().append(" and ").append(entityTableName).append(ORIGIN_STR).toString();
		
		sql.append("select ");
		sql.append(getBaseResultSql(UserEntity.class));
		sql.append(", user_name userName, password");
		sql.append(", phone_number phoneNumber, email, sex");
		sql.append(" from ");
		sql.append(entityTableName);
		sql.append(" where ");
		sql.append(" version >= 0");
		if(DqBaseUtils.isNotNull(userQuery.getUserId())){
			sql.append(commonConditionPrefix).append("id = ?");
			params.add(userQuery.getUserId());
		}
		if(DqStringUtils.isNotEmpty(userQuery.getUserName())){
			sql.append(commonConditionPrefix).append("user_name = ?");
			params.add(userQuery.getUserName());
		}
		if(DqStringUtils.isNotEmpty(userQuery.getPassword())){
			sql.append(commonConditionPrefix).append("password = ?");
			params.add(userQuery.getPassword());
		}
		if(DqStringUtils.isNotEmpty(userQuery.getEmail())){
			sql.append(commonConditionPrefix).append("email = ?");
			params.add(userQuery.getEmail());
		}
		if(DqStringUtils.isNotEmpty(userQuery.getPhoneNumber())){
			sql.append(commonConditionPrefix).append("phone_number = ?");
			params.add(userQuery.getPhoneNumber());
		}
		sql.append(" limi 1");
		return DqJdbcTemplateHandler.findTObj(sql, params, UserDTO.class);
	}
	
	@Override
	public UserDTO findUserByPhoneNumberAndPassword(UserQuery userQuery) {
		return findUserByCodition(userQuery);
	}

	@Override
	public UserDTO findUserByEmailAndPassword(UserQuery userQuery) {
		return findUserByCodition(userQuery);
	}
	
	
}
