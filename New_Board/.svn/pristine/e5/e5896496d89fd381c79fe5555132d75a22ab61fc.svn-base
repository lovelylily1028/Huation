package com.site.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;
import com.site.service.AjaxService;

@Controller
public class AjaxController {

	Map<String, Object> map;
	Map<String, Object> cmap;

	@Autowired
	AjaxService Service;

	int i = 0;
	String result = "";

	/*
	 * AJAX 리스트 페이지 
	 */
	@RequestMapping("/alist")
	public String ajaxAccess() {

		return "/ajax/alist";
	}

	/*
	 * 리스트 페이지 데이터 
	 */
	@ResponseBody
	@RequestMapping("/doList")
	public Map<String,Object> doAjax(HttpSession session, Model model, @RequestParam @Nullable String category,
			@RequestParam @Nullable String page, @RequestParam @Nullable String search) {

		map = Service.list(page, category, search);

		return map;
	}

	/*
	 * 상세페이지 호출
	 */
	@RequestMapping("/aview")
	public String aview(@RequestParam @Nullable String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String category, @RequestParam @Nullable String search, Model model) {
		
		return "/ajax/aview";
	}
	
	/*
	 * 상세페이지 호출후 데이터 불러오기
	 */
	@ResponseBody
	@RequestMapping("/doView")
	public Map<String,Object> doView(@RequestParam @Nullable String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String category, @RequestParam @Nullable String search, Model model,HttpServletRequest request, HttpServletResponse response) {
		
		map = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		String login_id = (String)session.getAttribute("user_id");
		
		map = Service.view(bid, page, category, search);
		map.put("login_id", login_id);

		return map;

	}

	/*
	 *  수정된 값 ,db 전달
	 */
	@ResponseBody
	@RequestMapping(value ="/doEdit", method = RequestMethod.POST)
	public  Map<String,Object> doEdit(BoardDTO boardDto, MultipartFile file, Model model, String rmt,
			@RequestParam @Nullable String page, @RequestParam @Nullable String search,
			@RequestParam @Nullable String category) throws UnsupportedEncodingException {
		
		map = new HashMap<String,Object>();
		
		i = Service.add(boardDto, file, rmt);
		
		if(i == 1) {
			map.put("result","true");
		}else {
			map.put("result", "false");
		}
		
		return map;
	}

	/*
	 * 게시글 삭제 
	 */
	@ResponseBody
	@RequestMapping("/del")
	public Map<String,Object> delete(@RequestParam String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String search, @RequestParam @Nullable String category, Model model)
			throws UnsupportedEncodingException {

		i = Service.delete(bid);
		
		if(i == 1) {
			map.put("result","true");
		}else {
			map.put("result", "false");
		}

		return map;
	}
	
	
	/*
	 * 상세페이지 댓글 리스트 불러오기
	 */
	@ResponseBody
	@RequestMapping("/doCmt")
	public Map<String,Object> doCmt(@RequestParam @Nullable String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String category, @RequestParam @Nullable String search, Model model,HttpServletRequest request) {
		
		map = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		String login_id = (String)session.getAttribute("user_id");
		map.put("login_id", login_id);
		
		map = Service.cmtList(bid);
		
		return map;
		
	}
	
	/**
	 * 댓글 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/delCmt", method = RequestMethod.POST)
	public Map<String, Object> cmtDelete(@RequestParam String bid, @RequestParam String cid, Model model)
			throws UnsupportedEncodingException {
		
		i = Service.deleteCmt(bid, cid);
		
		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		
		return map;
	}
	
	/**
	 * 댓글 등록 및 수정
	 */
	@ResponseBody
	@RequestMapping(value = "/doAddCmt", method = RequestMethod.POST)
	public Map<String, Object> cmtAdd(CommentDTO commentDto, Model model, @RequestParam @Nullable String bid,
			@RequestParam @Nullable String cid, String crmt) throws UnsupportedEncodingException {
		
		i = Service.addCmt(commentDto, crmt);
		
		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		
		return map;
	}
	
	
	
	
	/*
	 * 새글 및 답글 작성페이지 내용 호출
	 */
	@ResponseBody
	@RequestMapping("/doAddView")
	public Map<String,Object> doAddview(HttpSession session, Model model, @RequestParam @Nullable String bid,
			@RequestParam @Nullable String page, @RequestParam @Nullable String search,
			@RequestParam @Nullable String category,HttpServletRequest request) {
		
		session = request.getSession();
		String login_id = (String)session.getAttribute("user_id");
		map.put("login_id", login_id);
		
		map = Service.edit_view(bid);
		
		return map;
	}
	
	@RequestMapping("/aAdd")
	public String aAdd(HttpSession session, Model model, @RequestParam @Nullable String bid,
			@RequestParam @Nullable String page, @RequestParam @Nullable String search,
			@RequestParam @Nullable String category,@RequestParam @Nullable String rmt) {
		
		return "/ajax/aadd";
	}

	
	/**
	 * 새글 , 답글 ,수정글 db 업로드 받아오는 rmt(request:요청) 값에 따라 db 처리를 다르게 함. ajaxService
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Map<String,Object> ajaxadd(BoardDTO boardDto, MultipartFile file, Model model, String rmt,
			@RequestParam @Nullable String page, @RequestParam @Nullable String search,
			@RequestParam @Nullable String category) throws UnsupportedEncodingException {
		
		i = Service.add(boardDto, file, rmt);
		
		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		
		return map;
	}

}//