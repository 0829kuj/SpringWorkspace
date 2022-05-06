package com.myapp.maybeCafe.dao;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.maybeCafe.model.UserVO;

@Mapper
public interface UserMapper {
	public void save(UserVO user);	// 새 유저 저장
}
