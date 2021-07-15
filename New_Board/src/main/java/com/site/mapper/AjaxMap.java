package com.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;

@Mapper
public interface AjaxMap {

	List<BoardDTO> list(int startrow, int endrow, String search, String category);

	BoardDTO view(String bid);

	void HitUp(String bid);

	List<CommentDTO> cmtList(String bid);

	int add(BoardDTO boardDto);

	int addReply(BoardDTO boardDto);

	void addReplyPlus(BoardDTO boardDto); // step,indent 추가를 위한 mapper

	int edit(BoardDTO boardDto);

	int delete(String bid);

	int addCmt(CommentDTO commentDto);

	int editCmt(CommentDTO commentDto);

	int deleteCmt(String bid, String cid);


	


	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
