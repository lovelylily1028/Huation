package com.site.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.GraphDTO;
import com.site.mapper.BoardMap;

@Service
public class ChartServiceImpl implements ChartService {

	GraphDTO  graphDTO;
	
	List<GraphDTO> glist;
	
	Map<String, Object> map;

	@Autowired
	BoardMap boardMapper;
	
	/*
	 * 그래프 리스트 가져오기
	 */
	@Override
	public Map<String, Object> graphList() {
		
		map = new HashMap<String,Object>();
		
		glist = new ArrayList<GraphDTO>();
		
		glist = boardMapper.graphList();
		
		map.put("glist", glist);
		
		return map;
	}


	
	
}
