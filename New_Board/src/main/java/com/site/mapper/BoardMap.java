package com.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;
import com.site.dto.GraphDTO;

@Mapper
public interface BoardMap {

// 게시판 CRUD
	BoardDTO selectBoard(String bid);

	int insertBoard(BoardDTO boardDto);

	int updateBoard(BoardDTO boardDto);

	int deleteBoard(String bid);

	int insertBoardReply(BoardDTO boardDto);
	
	void insertBoardReplyPlus(BoardDTO boardDto);
	
//  검색에 맞는 게시판 리스트	
	List<BoardDTO> selectBoardList(int startrow, int endrow,String search,String category);
	
// 조회수 증가
	void selectUpHit(String bid);
	
// 페이징을 위한 Mapper
	
	int listCount(String search,String category);

	BoardDTO fileDownInfo(String bid);

	List<GraphDTO> graphList();


	
	
	
}
