package com.dq.easy.cloud.user.repository.impl;

import org.springframework.stereotype.Repository;

import com.dq.easy.cloud.model.basic.repository.DqBaseRepository;
import com.dq.easy.cloud.user.repository.inf.UserRepository;

@Repository(value="userRepository")
public class UserRepositoryImpl extends DqBaseRepository implements UserRepository{

}
