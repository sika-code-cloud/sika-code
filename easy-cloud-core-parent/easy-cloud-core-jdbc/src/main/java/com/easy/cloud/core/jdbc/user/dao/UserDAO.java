package com.easy.cloud.core.jdbc.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.user.entity.UserEntity;

public interface UserDAO{
	UserEntity findById(@Param(value = "id") Long id);

	int listCount(@Param("maps") Map<String, Object> paramMap);

	List<UserEntity> listPage(@Param("maps") Map<String, Object> paramMap);

	void save(UserEntity userEntity);

	int update(UserEntity userEntity);

}
