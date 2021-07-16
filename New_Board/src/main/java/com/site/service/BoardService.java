package com.site.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;

public interface BoardService {

	int insertBoard(BoardDTO boardDto, MultipartFile file); // 게시글 작성

	Map<String, Object> selectBoard(String bid, String page, String category, String search,HttpServletRequest request, HttpServletResponse response);

	Map<String, Object> selectBoardList(String page, String category, String search); // 게시물 전체 리스트

	Map<String, Object> updateBoard_view(String bid);

	int updateBoard(BoardDTO boardDto, MultipartFile file);

	int deleteBoard(String bid);

	int insertBoardReply(BoardDTO boardDto, MultipartFile file);

	void fileDown(String bid, HttpServletResponse response) throws Exception;

	

	
	

}
