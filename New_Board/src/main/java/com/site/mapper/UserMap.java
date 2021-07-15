package com.site.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.UserDTO;

@Mapper
public interface UserMap {

	int insertNewMember(UserDTO userDto);

	UserDTO loginCheck(String user_id, String user_pw);

	int joinIdCheck(String user_id);

	
	
	
}
