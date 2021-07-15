package com.site.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;
import com.site.mapper.AjaxMap;
import com.site.mapper.BoardMap;

@Service
public class AjaxServiceImpl implements AjaxService {

	@Autowired
	BoardMap boardMapper;

	@Autowired
	AjaxMap mapper;

	@Autowired
	PageNumber pageNumber;

	List<BoardDTO> list;

	Map<String, Object> map;

	BoardDTO boardDTO;
	CommentDTO commentDTO;

	List<CommentDTO> clist = new ArrayList<CommentDTO>();

	int i = 0;

	@Override
	public Map<String, Object> list(String listPage, String category, String search) {

		list = new ArrayList<BoardDTO>();
		int page = 1;
		int limit = 10;

		if (listPage != null && listPage != "") {
			page = Integer.parseInt(listPage);
		}

		int startrow = (page - 1) * limit + 1; // 시작 게시글번호 1,11,21...
		int endrow = startrow + limit - 1; // 마지막 게시글번호 10,20,30...

		list = mapper.list(startrow, endrow, search, category);

		map = new HashMap<String, Object>();
		map = pageNumber.pageNumber(page, limit, category, search);

		map.put("list", list);

		return map;
	}

	@Override
	public Map<String, Object> view(String bid, String page, String category, String search) {

		// ajax의 경우 새로고침시 리스트 페이지로 돌아가기에 쿠키부분 생략 (조회수 증가)
		mapper.HitUp(bid);

		// content 1개 가져오기
		boardDTO = mapper.view(bid);
		map.put("boardDto", boardDTO);
		map.put("category", category);
		map.put("search", search);
		map.put("page", page);

		return map;
	}

	@Override
	public Map<String, Object> edit_view(String bid) {

		boardDTO = mapper.view(bid);
		map.put("boardDto", boardDTO);

		return map;
	}

	/*
	 * 게시글 등록,수정,답글 DB처리
	 */
	@Override
	public int add(BoardDTO boardDto, MultipartFile file, String rmt) {

		// 원본파일 이름
		String fileName = file.getOriginalFilename();
		// 확장자명 가져오기
		String filenameExtension = FilenameUtils.getExtension(fileName).toLowerCase();

		// 확장자명으로 첨부파일이 비었는지 확인
		if (filenameExtension != "") {

			// 파일저장위치
			String fileUrl = "D:/Data/";
			File f = new File(fileUrl + fileName);
			
			// 파일업로드
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 파일이름저장
			boardDto.setFileName(fileName);
		} else {
			if (rmt.equals("add")|| rmt.equals("reply")) {
				// 작성페이지에서 파일이 없는 경우 빈값을 저장
				boardDto.setFileName("");

			} else {
				// 수정페이지에서 첨부파일이 없는 경우 기존 파일이름을 그대로 저장시키면됨.
				boardDto.setFileName(boardDto.getFileName());
			}
		}

		switch (rmt) {
		case "add":
			i = mapper.add(boardDto);
			break;
		case "reply":
			i = mapper.addReply(boardDto);
			mapper.addReplyPlus(boardDto);
			break;
		default:
			i = mapper.edit(boardDto);
			break;
		}

		return i;
	}

	/*
	 * 게시글 삭제
	 */
	@Override
	public int delete(String bid) {

		i = mapper.delete(bid);

		return i;
	}

	/*
	 * 댓글 리스트
	 */
	@Override
	public Map<String, Object> cmtList(String bid) {

		clist = mapper.cmtList(bid);

		map.put("clist", clist);

		return map;
	}

	/*
	 * 댓글 등록 및 수정
	 */
	@Override
	public int addCmt(CommentDTO commentDto, String crmt) {

		switch (crmt) {
		case "add":
			i = mapper.addCmt(commentDto);
			break;
		default:
			i = mapper.editCmt(commentDto);
			break;
		}

		return i;
	}

	/*
	 * 댓글 삭제
	 */
	@Override
	public int deleteCmt(String bid, String cid) {

		i = mapper.deleteCmt(bid, cid);

		return i;
	}
	
	
	
	
	

}// implements
