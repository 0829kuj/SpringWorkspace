package com.myapp.maybeCafe.service;

import org.springframework.stereotype.Service;

import com.myapp.maybeCafe.dao.UserMapper;
import com.myapp.maybeCafe.model.UserVO;

@Service
public class UserServiceImpl implements UserService{

	private UserMapper uMapper;
	
	// 생성자 주입
	public UserServiceImpl(UserMapper userMapper) {
		this.uMapper = userMapper;
	}
	
	@Override
	public void save(UserVO user) {
		// 새 유저 저장(가입하기)
		uMapper.save(user);
	}

}
