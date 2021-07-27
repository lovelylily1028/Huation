package com.site.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	
	/**
	 * 관리자 페이지 연결
	 * @return
	 */
	@RequestMapping("/admin/admin")
	public String adminPage() {	return "/admin/admin";}
	
	
	
	
	
	
	
	
}
