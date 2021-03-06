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

import com.site.dto.AjaxBoardDTO;
import com.site.dto.AjaxCommentDTO;
import com.site.service.AjaxService;

@Controller
public class AjaxController {

	Map<String, Object> map;
	Map<String, Object> cmap;

	@Autowired
	AjaxService Service;

	int i = 0;
	String result = "";

	/**
	 * JSP 페이지 연결
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/alist")
	public String ajaxAccess() {

		return "/ajax/alist";
	}

	/**
	 * 리스트 페이지의 내용을 가져온다.
	 * 
	 * @param session
	 * @param model
	 * @param page    현재 리스트 페이지
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/doList")
	public Map<String, Object> doAjax(HttpSession session, Model model, @RequestParam @Nullable String page) {

		map = Service.list(page);

		return map;
	}

	/**
	 * 상세페이지 연결
	 * 
	 * @param code  상세보기를 할 게시물 번호
	 * @param page  현재 게시물이 있던 리스트 페이지
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/aview")
	public String aview(@RequestParam @Nullable String code, @RequestParam @Nullable String page, Model model) {

		return "/ajax/aview";
	}

	/**
	 * 선택한 1개의 게시물의 상세내용을 가져온다.
	 * 
	 * @param code     상세보기를 할 게시물 번호
	 * @param page     현재 게시물이 있던 리스트 페이지
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/doView")
	public Map<String, Object> doView(@RequestParam @Nullable String code, @RequestParam @Nullable String page,
			Model model, HttpServletRequest request, HttpServletResponse response) {

		map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		String login_id = (String) session.getAttribute("user_id");

		map = Service.view(code, page);
		map.put("login_id", login_id);

		return map;

	}

	/**
	 * 수정된 내용을 DB에 업로드한다.
	 * 
	 * @param ajaxboardDto
	 * @param model
	 * @param rmt
	 * @param page         현재 게시물이 있던 리스트 페이지
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/doEdit", method = RequestMethod.POST)
	public Map<String, Object> doEdit(AjaxBoardDTO ajaxboardDto, Model model, String rmt,
			@RequestParam @Nullable String page) throws UnsupportedEncodingException {

		map = new HashMap<String, Object>();

		i = Service.add(ajaxboardDto, rmt);

		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}

		return map;
	}

	/**
	 * 게시물 삭제
	 * 
	 * @param code  삭제할 게시물 번호
	 * @param page  현재 게시물이 있던 리스트 페이지
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("/ajax/del")
	public Map<String, Object> delete(@RequestParam String code, @RequestParam @Nullable String page, Model model)
			throws UnsupportedEncodingException {

		i = Service.delete(code);

		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}

		return map;
	}

	/**
	 * 새글 및 답글 작성 페이지 연결
	 * 
	 * @param session
	 * @param model
	 * @param bid     답글 작성의 경우 bid가 포함되어있음
	 * @param page    답글일 경우 원글이 있던 리스트 페이지
	 * @param rmt     새글인 경우 add 답글인 경우 reply
	 * @return
	 */
	@RequestMapping("/ajax/aAdd")
	public String aAdd(HttpSession session, Model model, @RequestParam @Nullable String bid,
			@RequestParam @Nullable String page, @RequestParam @Nullable String rmt) {
		return "/ajax/aadd";
	}

	/**
	 * 작성 페이지 준비
	 * 
	 * @param session
	 * @param model
	 * @param code    답글의 경우 답글을 작성할 게시물 번호
	 * @param page    답글의 경우 원글이 있던 리스트 페이지
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/doAddView")
	public Map<String, Object> doAddview(HttpSession session, Model model, @RequestParam @Nullable String code,
			@RequestParam @Nullable String page, HttpServletRequest request) {

		session = request.getSession();
		String login_id = (String) session.getAttribute("user_id");
		map.put("login_id", login_id);

		map = Service.edit_view(code);

		return map;
	}

	/**
	 * 작성된 내용 DB 업로드
	 * 
	 * @param ajaxDto
	 * @param file
	 * @param model
	 * @param rmt     새글인 경우 add 답글인 경우 reply
	 * @param page    답글의 경우 원글이 있던 리스트 페이지
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("/ajax/doAdd")
	public Map<String, Object> ajaxadd(AjaxBoardDTO ajaxDto, MultipartFile file, Model model, String rmt,
			@RequestParam @Nullable String page) throws UnsupportedEncodingException {

		i = Service.add(ajaxDto, rmt);

		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}

		return map;
	}

	/**
	 * 선택한 게시물의 댓글 리스트 가져오기
	 * 
	 * @param code    댓글 리스트를 불러올 게시물 번호
	 * @param page    게시물이 있던 리스트 페이지
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/doCmt")
	public Map<String, Object> doCmt(@RequestParam @Nullable String code, @RequestParam @Nullable String page,
			Model model, HttpServletRequest request) {

		map = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		String login_id = (String) session.getAttribute("user_id");
		map.put("login_id", login_id);

		map = Service.cmtList(code);

		return map;

	}

	/**
	 * 댓글 삭제
	 * 
	 * @param code  삭제할 댓글이 있는 게시물 번호
	 * @param ccode 댓글의 고유 번호
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/delCmt", method = RequestMethod.POST)
	public Map<String, Object> cmtDelete(@RequestParam String code, @RequestParam String ccode, Model model)
			throws UnsupportedEncodingException {

		i = Service.deleteCmt(code, ccode);

		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}

		return map;
	}

	/**
	 * 댓글 등록 및 수정
	 * 
	 * @param ajaxcommentDto
	 * @param model
	 * @param code           댓글을 작성/수정할 게시물 번호
	 * @param cid            수정할 댓글의 고유번호
	 * @param crmt           새로운댓글인 경우(add)/수정인 경우(edit)
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/doAddCmt", method = RequestMethod.POST)
	public Map<String, Object> cmtAdd(AjaxCommentDTO ajaxcommentDto, Model model, @RequestParam @Nullable String code,
			@RequestParam @Nullable String cid, String crmt) throws UnsupportedEncodingException {

		i = Service.addCmt(ajaxcommentDto, crmt);

		if (i == 1) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}

		return map;
	}

}//