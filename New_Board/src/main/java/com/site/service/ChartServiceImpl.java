package com.site.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.GraphDTO;
import com.site.mapper.BoardMap;

@Service
public class ChartServiceImpl implements ChartService {

	GraphDTO  graphDTO;
	
	List<GraphDTO> glist;
	
	@Autowired
	BoardMap boardMapper;
	
	/*
	 * 그래프 리스트 가져오기
	 */
	@Override
	public List<GraphDTO> graphList() {
		
		glist = new ArrayList<GraphDTO>();
		
		glist = boardMapper.graphList();
		
		return glist;
	}


	
	
}
