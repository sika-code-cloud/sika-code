package com.easy.cloud.core.jdbc.test;

import com.easy.cloud.core.jdbc.test.dao.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
	@Resource
	private UserDAO userDAO;

	@Transactional
	public UserEntity saveUserEntity(UserEntity userEntity) {
		System.out.println(userDAO.save(userEntity));
		return userEntity;
	}

	@Transactional
	public UserEntity updateUserEntity(UserEntity userEntity) {
		userDAO.update(userEntity);
		return userEntity;
	}
	@Transactional
	public List<UserEntity> saveBatch(List<UserEntity> userEntitys) {
		userDAO.saveBatch(userEntitys);
		return userEntitys;
	}

	public List<UserEntity> listPage(int start, int pageSize) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("start", start);
		maps.put("pageSize", pageSize);
		return userDAO.listPage(maps);
	}

	public UserEntity findById(Long id) {
		return userDAO.findById(id);
	}
}
