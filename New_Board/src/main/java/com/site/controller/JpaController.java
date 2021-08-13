package com.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/H2_jpa")
public class JpaController {

	@RequestMapping("/list")
	public String list() {
		return "/jpa/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "/jpa/add";
	}
	
	@RequestMapping("/view")
	public String view() {
		return "/jpa/view";
	}

}
