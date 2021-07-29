package com.site.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.GraphDTO;
import com.site.mapper.ChartMap;

@Service
public class ChartServiceImpl implements ChartService {

	GraphDTO  graphDTO;
	
	List<GraphDTO> glist;
	
	@Autowired
	ChartMap chartMapper;
	

	/*
	 * 일반게시판 막대그래프 리스트 가져오기
	 */
	@Override
	public List<GraphDTO> barList(String start,String end,String btDay){
		
		glist = new ArrayList<GraphDTO>();
		
		glist = chartMapper.barList(start,end,btDay);
		
		return glist;
	}
	
	/*
	 * AJAX 게시판 선그래프 리스트 가져오기
	 */
	@Override
	public List<GraphDTO> lineList(String start,String end,String btDay){
		
		glist = new ArrayList<GraphDTO>();
		
		glist = chartMapper.lineList(start,end,btDay);
		
		return glist;
	}
	


	
	
}
