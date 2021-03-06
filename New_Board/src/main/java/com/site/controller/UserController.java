package com.site.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.dto.UserDTO;
import com.site.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	UserDTO userDto;

	int result;

	/**
	 * 아이디 중복 체크
	 * 
	 * @param user_id 중복체크를 요청한 ID
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user/joinIdCheck")
	public Map<String, Object> joinIdCheck(@RequestParam @Nullable String user_id, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		result = 0;

		result = userService.joinIdCheck(user_id);

		map.put("result", result);

		return map;
	}

	/**
	 * 회원가입 신청
	 * 
	 * @param session
	 * @param userDto
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/joinCheck")
	public String joinAccess(HttpSession session, UserDTO userDto, Model model) {

		int i = userService.insertNewMember(userDto);

		if (i == 1) {
			model.addAttribute("msg", "회원가입이 완료되었습니다.");
		} else {
			model.addAttribute("msg", "회원가입에 실패하였습니다.");
		}

		model.addAttribute("location", "/");

		return "util/message";
	}

}
