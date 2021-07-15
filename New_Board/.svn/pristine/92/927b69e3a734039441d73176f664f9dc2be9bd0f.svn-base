package com.site.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;

public interface AjaxService {

	Map<String, Object> list(String page, String category, String search);

	Map<String, Object> view(String bid, String page, String category, String search);

	Map<String, Object> edit_view(String bid);

	int add(BoardDTO boardDto, MultipartFile file, String rmt);

	int delete(String bid);
	
	Map<String, Object> cmtList(String bid);

	int addCmt(CommentDTO commentDto, String crmt);

	int deleteCmt(String bid, String cid);



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//interface
