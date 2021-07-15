package com.site.service;

import com.site.dto.UserDTO;

public interface UserService {

	int insertNewMember(UserDTO userDto);

	UserDTO loginCheck(String user_id, String user_pw);

	int joinIdCheck(String user_id);

}
