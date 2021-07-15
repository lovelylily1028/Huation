package com.site.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.UserDTO;
import com.site.mapper.UserMap;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMap userMapper;
	
	UserDTO userDto;
	
	int result;
	
	// 회원가입
	@Override
	public int insertNewMember(UserDTO userDto) {
		
		int i = userMapper.insertNewMember(userDto);
		
		return i;
	}

	// 로그인
	@Override
	public UserDTO loginCheck(String user_id, String user_pw) {
	
		userDto = userMapper.loginCheck(user_id,user_pw);
		
		return userDto;
	}

	// 아이디 중복 체크
	@Override
	public int joinIdCheck(String user_id) {
		
		// 쿼리 결과값 담아줌
		result = userMapper.joinIdCheck(user_id); 
		
		return result;
	}

}
