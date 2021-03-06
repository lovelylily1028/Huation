package com.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.dto.UserDTO;
import com.site.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	UserDTO userDto;

	/**
	 * 로그인 페이지
	 */
	@RequestMapping("/user/loginPage")
	public String login() {	return "/user/loginPage"; }
	

	/**
	 * 로그인 확인
	 * 
	 * @param request
	 * @param user_id 클라이언트가 입력한 아이디
	 * @param user_pw 클라이언트가 입력한 비밀번호
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/loginCheck")
	public String loginAccess(HttpServletRequest request, String user_id, String user_pw, Model model) {

		userDto = new UserDTO();
		userDto = userService.loginCheck(user_id, user_pw);

		if (userDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user_id", userDto.getUser_id());
			session.setAttribute("user_name", userDto.getUser_name());
			session.setAttribute("loginId", userDto.getUser_id());
			
			String dest = (String)session.getAttribute("dest"); // 로그인 전에 접속하려던 곳
			String redirect = (dest == null) ? "/board/list" : dest;
			
			model.addAttribute("msg", userDto.getUser_name() + "님 환영합니다.");
			model.addAttribute("location", redirect);
			
		} else {
			model.addAttribute("msg", "로그인에 실패하였습니다.");
			model.addAttribute("location", "/user/loginPage");
		}
		
		return "util/message";

	}

	/**
	 * 로그아웃
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logoutAccess(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("location", "/user/loginPage");
		return "util/message";
	}

}
