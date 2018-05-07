package com.easy.cloud.core.jdbc.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.jdbc.user.dao.UserDAO;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;

@Service
public class UserService {
	@Resource
	private UserDAO userDAO;
	@Transactional
	public UserEntity saveUserEntity(UserEntity userEntity){
		System.out.println(userDAO.save(userEntity));
		return userEntity;
	}
	@Transactional
	public UserEntity updateUserEntity(UserEntity userEntity){
		userDAO.update(userEntity);
		return userEntity;
	}
	public List<UserEntity> listPage(int start, int pageSize) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("start", start);
		maps.put("pageSize", pageSize);
		return userDAO.listPage(maps);
	}
}
