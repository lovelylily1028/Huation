package com.site.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.dto.GraphDTO;
import com.site.service.ChartService;

@Controller
public class ChartController {
	
	Map<String, Object> map;
	
	GraphDTO  graphDTO;
	
	List<GraphDTO> glist;
	
	@Autowired
	ChartService chartService;

	
	/*
	 * 막대 그래프 페이지 연결
	 */
	@RequestMapping("/graph")
	public String graphBoardAccess(HttpSession session, Model model) {
		
		return "graph/graph";
	}

	/*
	 * AJAX  연결하기
	 */
	@ResponseBody
	@RequestMapping("/dograph")
	public List<GraphDTO> dograph(HttpSession session, Model model) {
		 
		return chartService.graphList();
	}
	
	
	
	
	
}
