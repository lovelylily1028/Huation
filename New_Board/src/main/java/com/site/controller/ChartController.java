package com.site.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping("/chart")
	public String graphBoardAccess(HttpSession session, Model model) {
		
		return "chart/chart";
	}

	/*
	 * 일반게시판 일일 게시물 등록수 조회 (막대그래프)
	 */
	@ResponseBody
	@RequestMapping("/dobar")
	public List<GraphDTO> dobar(HttpSession session, Model model,@RequestParam @Nullable String start,@RequestParam @Nullable String end) {
		 
		return chartService.barList(start,end);
	}
	
	/*
	 * AJAX 게시판 일일 게시물 등록수 조회 (선그래프)
	 */
	@ResponseBody
	@RequestMapping("/doline")
	public List<GraphDTO> doline(HttpSession session, Model model) {
		
		return chartService.lineList();
	}
	
	
	
	
	
}
