package com.site.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.CommentDTO;
import com.site.mapper.CommentMap;

@Service
public class CommentServiceImpl implements CommentService {

	List<CommentDTO> clist = new ArrayList<CommentDTO>();
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	@Autowired
	CommentMap commentMapper;
	
	CommentDTO commentDto;
	
	int i =0;
	
	@Override
	public int insertComment(CommentDTO commentDto) {

		i = commentMapper.insertComment(commentDto);

		return i;
	}

	@Override
	public Map<String, Object> selectCommentList(String bid) {

		clist = commentMapper.selectCommentList(bid);
		
		map.put("clist", clist);

		return map;
	}

	@Override
	public int deleteComment(String bid, String cid) {

		i = commentMapper.deleteComment(bid,cid);
		
		return i;
	}

	@Override
	public int updateComment(CommentDTO commentDto) {

		i = commentMapper.updateComment(commentDto);
		
		return i;
	}

	@Override
	public int insertReply(CommentDTO commentDto) {

		i = commentMapper.insertReply(commentDto);
		
		return i;
	}

	
	
	
	
}
