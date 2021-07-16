package com.site.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.service.ChartService;

@Controller
public class ChartController {
	
	Map<String, Object> map;
	
	@Autowired
	ChartService chartService;

	/*
	 * 막대 그래프 페이지
	 */
	@RequestMapping("/graph")
	public String graphBoardAccess(HttpSession session, Model model) {
		
		 map = chartService.graphList();
		 
		 model.addAttribute("map",map);
		
		return "graph/graph";
	}

	/*
	 * AJAX  연결하기
	 */
	@RequestMapping("/dograph")
	public Map<String,Object> dograph(HttpSession session, Model model) {
		
		map = chartService.graphList();
		
		return map;
	}
	
	
	
	
	
}
