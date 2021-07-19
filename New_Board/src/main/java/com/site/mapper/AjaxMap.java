package com.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.AjaxBoardDTO;
import com.site.dto.AjaxCommentDTO;

@Mapper
public interface AjaxMap {

	List<AjaxBoardDTO> list(int startrow, int endrow);

	AjaxBoardDTO view(String code);

	void HitUp(String code);

	List<AjaxCommentDTO> cmtList(String code);

	int add(AjaxBoardDTO ajaxDto);

	int addReply(AjaxBoardDTO ajaxDto);

	void addReplyPlus(AjaxBoardDTO ajaxDto); // step,indent 추가를 위한 mapper

	int edit(AjaxBoardDTO ajaxDto);

	int delete(String code);

	int addCmt(AjaxCommentDTO commentDto);

	int editCmt(AjaxCommentDTO commentDto);

	int deleteCmt(String code, String ccode);
	
	int listCount();


	


	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
