package com.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.CommentDTO;

@Mapper
public interface CommentMap {

	int insertComment(CommentDTO commentDto);

	List<CommentDTO> selectCommentList(String bid);

	int deleteComment(String bid, String cid);

	int updateComment(CommentDTO commentDto);

	int insertReply(CommentDTO commentDto);

	int insertReplyPlus(CommentDTO commentDto);

	
}
