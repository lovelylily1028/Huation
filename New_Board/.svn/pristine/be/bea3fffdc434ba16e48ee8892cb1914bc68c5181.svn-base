package com.site.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.AjaxBoardDTO;
import com.site.dto.AjaxCommentDTO;
import com.site.mapper.AjaxMap;

@Service
public class AjaxServiceImpl implements AjaxService {

	@Autowired
	AjaxMap mapper;

	@Autowired
	PageNumber2 pageNumber;

	List<AjaxBoardDTO> list;

	Map<String, Object> map;

	AjaxBoardDTO aDTO;
	
	AjaxCommentDTO ajaxcommentDTO;

	List<AjaxCommentDTO> clist = new ArrayList<AjaxCommentDTO>();

	int i = 0;

	@Override
	public Map<String, Object> list(String listPage) {

		list = new ArrayList<AjaxBoardDTO>();
		int page = 1;
		int limit = 10;

		if (listPage != null && listPage != "") {
			page = Integer.parseInt(listPage);
		}

		int startrow = (page - 1) * limit + 1; // 시작 게시글번호 1,11,21...
		int endrow = startrow + limit - 1; // 마지막 게시글번호 10,20,30...

		list = mapper.list(startrow, endrow);

		map = new HashMap<String, Object>();
		map = pageNumber.pageNumber(page, limit);

		map.put("list", list);

		return map;
	}

	
	  @Override 
	  public Map<String, Object> view(String code, String page) {
	  
	  // ajax의 경우 새로고침시 리스트 페이지로 돌아가기에 쿠키부분 생략 (조회수 증가)
		 
	  mapper.HitUp(code);
	  
	  // content 1개 가져오기 
		  
	  aDTO = mapper.view(code); 
	  map.put("aDto", aDTO);
	  
	  map.put("page", page);
	  
	  return map; 
	  
	  }
	  
	  @Override
	  public Map<String, Object> edit_view(String code) {
	  
		  aDTO = mapper.view(code);
		  map.put("ajaxDto", aDTO);
	  
	  return map;
	  }
	
	 
	/*
	 * 게시글 등록,수정,답글 DB처리
	 */
	@Override
	public int add(AjaxBoardDTO ajaxDto, String rmt) {

		switch (rmt) {
		case "add":
			i = mapper.add(ajaxDto);
			break;
		case "reply":
			i = mapper.addReply(ajaxDto);
			break;
		default:
			i = mapper.edit(ajaxDto);
			break;
		}

		return i;
	}

	/*
	 * 게시글 삭제
	 */
	@Override
	public int delete(String code) {

		i = mapper.delete(code);

		return i;
	}

	/*
	 * 댓글 리스트
	 */
	@Override
	public Map<String, Object> cmtList(String code) {

		clist = mapper.cmtList(code);

		map.put("clist", clist);

		return map;
	}

	/*
	 * 댓글 등록 및 수정
	 */
	@Override
	public int addCmt(AjaxCommentDTO ajaxcommentDto, String crmt) {

		switch (crmt) {
		case "add":
			i = mapper.addCmt(ajaxcommentDto);
			break;
		default:
			i = mapper.editCmt(ajaxcommentDto);
			break;
		}

		return i;
	}

	/*
	 * 댓글 삭제
	 */
	@Override
	public int deleteCmt(String code, String ccode) {

		i = mapper.deleteCmt(code, ccode);

		return i;
	}

	
	
	

}// implements
