package com.myapp.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.bbs.dao.UserMapper;
import com.myapp.bbs.model.Login;
import com.myapp.bbs.model.User;


@Service
public class LoginService {

	@Autowired
	private UserMapper userMapper;
	
	// 인증하기 메서드 : 실패할 경우 login 객체에 error메시지 입력
	public void authenticate(Login login) {
		// 이메일 검색으로 유저찾기
		User user = userMapper.selectByEmail(login.getEmail());
		
		if (user == null) {
			login.setError("이메일이 존재하지 않습니다.");
		} else {
			if (!user.getPassword().equals(login.getPassword())) {
				login.setError("패스워드가 틀립니다.");
			} else {
				login.setError(null);	// 에러없음(이메일 인증됨)
			}
		}
	}

	// 유저 찾기 메서드 : 이메일로 유저 찾기
	public User findUserByEmail(String email) {
		User user = userMapper.selectByEmail(email);
		return user;
	}

}
