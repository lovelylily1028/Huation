package com.site.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.site.dto.jpa.BoardDTO;
import com.site.entity.Board;
import com.site.repository.BoardRepository;

@Service
public class JpaService {

	Map<String, Object> map;
	
	private BoardRepository boardRepository;
	
	public JpaService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional
	public Long savePost(BoardDTO boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}

	@Transactional
	public Map<String, Object> getBoardList() {
		map = new HashMap<String, Object>();
		
		List<Board> boards = boardRepository.findAll();
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		for(Board board : boards){
			BoardDTO boardDto = BoardDTO.builder()
					.id(board.getId())
					.title(board.getTitle())
					.content(board.getTitle())
					.writer(board.getWriter())
					.createdDate(board.getCreateDate())
					.build();
			
			list.add(boardDto);
		}
		
		map.put("list",list);
		return map;
	}


}
