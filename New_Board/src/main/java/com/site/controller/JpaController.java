package com.site.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.dto.jpa.BoardDTO;
import com.site.service.jpa.JpaService;

@Controller
@RequestMapping("/H2_jpa")
public class JpaController {

	Map<String, Object> map;
	
	private JpaService jpaService;
	
	public JpaController(JpaService jpaService) {
		this.jpaService = jpaService;
	}
	
	/**
	 * 리스트 페이지 연결
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		
		//List<BoardDTO> boardDtoList = jpaService.getBoardList();
		
		map = jpaService.getBoardList();
		
		model.addAttribute("map",map);
		
		return "/jpa/list";
	}
	
	/**
	 * 새글 작성 페이지
	 * @return
	 */
	@RequestMapping("/add")
	public String addView() {
		return "/jpa/add";
	}
	
	
	/**
	 * 새글 작성 DB 업로드 
	 * @param boardDto
	 * @return
	 */
	@PostMapping("/post")
	public String write(BoardDTO boardDto) {
		jpaService.savePost(boardDto);
		return "redirect:/H2_jpa/list";
	}
	
	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") Long id, Model model) {
		BoardDTO boardDto = jpaService.getPost(id);
		
		model.addAttribute("boardDto", boardDto);
		return "/jpa/view";
		
	}
	
	/**
	 * 상세보기 페이지
	 * @return
	 */
	@RequestMapping("/view")
	public String view() {
		return "/jpa/view";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//
