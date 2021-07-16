package com.site.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;
import com.site.service.BoardService;
import com.site.service.CommentService;

@Controller
public class BoardController {

	Map<String, Object> map;
	Map<String, Object> cmap;

	@Autowired
	BoardService boardService;
	@Autowired
	CommentService commentService;

	int i = 0;

	/*
	 * 메인페이지 >> 로그인 페이지로 바로 연결
	 */
	@RequestMapping("/")
	public String mainAccess(HttpSession session, Model model) {
		return "user/loginPage";
	}

	/*
	 * 회원가입 페이지
	 */
	@RequestMapping("/joinPage")
	public String newUserJoin(HttpSession session, Model model) {
		return "/user/joinPage";
	}

	/*
	 * 채팅 페이지
	 */
	@RequestMapping("/chatt")
	public String chattBoardAccess(HttpSession session, Model model) {
		return "chatt/chatting";
	}

	/*
	 * 엑셀 업로드 페이지
	 */
	@RequestMapping("/board/excel")
	public String excelAccess(HttpSession session, Model model) {
		return "board/excel";
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/list")
	public String list(HttpSession session, Model model, @RequestParam @Nullable String category,
			@RequestParam @Nullable String page, @RequestParam @Nullable String search) {
		
		map = boardService.selectBoardList(page, category, search);
		
		model.addAttribute("map", map);
		
		return "/board/list";
	}
	
	@RequestMapping("view")
	public String NewView(@RequestParam @Nullable String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String category, @RequestParam @Nullable String search, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		map = boardService.selectBoard(bid, page, category, search,request,response);
		cmap = commentService.selectCommentList(bid);

		model.addAttribute("category", category);
		model.addAttribute("page", page);
		model.addAttribute("search", search);

		model.addAttribute("map", map);
		model.addAttribute("cmap", cmap);
		
		return "/board/view";
	}
	
	
	@RequestMapping("add")
	public String NewAdd(HttpSession session, Model model) {
		
		model.addAttribute("route", "add");
		
		return "/board/add";
	}

	/**
	 * 작성글 db 업로드
	 */
	@RequestMapping("/board/add")
	public String add(BoardDTO boardDto, MultipartFile file, Model model) {

		i = boardService.insertBoard(boardDto, file);

		if (i == 1) {
			model.addAttribute("msg", "작성이 완료되었습니다.");
		} else {
			model.addAttribute("msg", "작성에 실패하였습니다.");
		}

		model.addAttribute("location", "/list");

		return "util/message";
	}

	/**
	 * 게시글 수정db 업로드
	 */
	@RequestMapping("/board/edit")
	public String boardModify(BoardDTO boardDto, MultipartFile file, @RequestParam @Nullable String page,
			@RequestParam @Nullable String search, @RequestParam @Nullable String category, Model model)
			throws UnsupportedEncodingException {

		i = boardService.updateBoard(boardDto, file);

		if (i == 1) {
			model.addAttribute("msg", " 게시글 수정이 완료되었습니다.");
		} else {
			model.addAttribute("msg", " 게시글 수정에 실패하였습니다.");
		}

		model.addAttribute("location", "/list?bid=" + boardDto.getBid());

		return "util/message";
	}
	
	/*
	 * 일반게시판 답글 작성페이지로 보냄
	 */
	@RequestMapping("/board/reply_view")
	public String boardReply_view(@RequestParam @Nullable String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String search, @RequestParam @Nullable String category, Model model) {

		map = boardService.updateBoard_view(bid);

		model.addAttribute("map", map);
		model.addAttribute("route", "reply");

		return "/board/add";
	}

	/**
	 * 게시글 답글db 업로드
	 */
	@RequestMapping("/board/boardReply")
	public String boardReply(BoardDTO boardDto, @Nullable MultipartFile file, @RequestParam @Nullable String page,
			@RequestParam @Nullable String search, @RequestParam @Nullable String category, Model model)
			throws UnsupportedEncodingException {

		i = boardService.insertBoardReply(boardDto, file);

		if (i == 1) {
			model.addAttribute("msg", "답변글 게시가 완료되었습니다.");
		} else {
			model.addAttribute("msg", "답변글 게시에 실패하였습니다.");
		}
		model.addAttribute("location", "/view?bid=" + boardDto.getBid());

		return "util/message";
	}


	/**
	 * 게시글 삭제
	 */
	@RequestMapping("/board/delete")
	public String boardDelete(@RequestParam String bid, @RequestParam @Nullable String page,
			@RequestParam @Nullable String search, @RequestParam @Nullable String category, Model model)
			throws UnsupportedEncodingException {

		i = boardService.deleteBoard(bid);

		if (i == 1) {
			model.addAttribute("msg", " 해당 게시물이 삭제되었습니다.");
		} else {
			model.addAttribute("msg", " 게시물 삭제에 실패하였습니다.");
		}

		model.addAttribute("location", "/list");

		return "util/message";
	}

	
	/*
	 * 첨부파일 다운로드
	 */
	@RequestMapping("/fileDown")
	public void fileDown(@RequestParam String bid, HttpServletResponse response) throws Exception {

		boardService.fileDown(bid, response);
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	/**
	 * 게시글 댓글 add
	 */
	@RequestMapping("/board/CommentCheck")
	public String CommentInsert(CommentDTO commentDto, Model model) {

		i = commentService.insertComment(commentDto);

		if (i == 1) {
			model.addAttribute("msg", " 댓글 작성이 완료되었습니다.");
		} else {
			model.addAttribute("msg", " 댓글 작성에 실패하였습니다.");
		}
		model.addAttribute("location", "/view?bid=" + commentDto.getBid());

		return "util/message";
	}

	/**
	 * 댓글 수정
	 */
	@RequestMapping("/board/commentEdit")
	public String commentUpdate(@RequestParam String bid, @RequestParam String cid, CommentDTO commentDto, Model model)
			throws UnsupportedEncodingException {

		i = commentService.updateComment(commentDto);

		if (i == 1) {
			model.addAttribute("msg", " 해당 댓글이 수정되었습니다.");
		} else {
			model.addAttribute("msg", " 댓글 수정에 실패하였습니다.");
		}

		model.addAttribute("location", "/view?bid="+bid);

		return "util/message";
	}

	/**
	 * 댓글 삭제
	 */
	@RequestMapping("/commentDelete")
	public String commentDelete(@RequestParam String bid, @RequestParam String cid, Model model)
			throws UnsupportedEncodingException {

		i = commentService.deleteComment(bid, cid);

		if (i == 1) {
			model.addAttribute("msg", " 해당 댓글이 삭제되었습니다.");
		} else {
			model.addAttribute("msg", " 댓글 삭제에 실패하였습니다.");
		}
		model.addAttribute("location", "/view?bid="+bid);

		return "util/message";
	}

}//
