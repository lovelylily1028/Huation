package com.site.service;

import java.util.ArrayList;
import java.util.Date;
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
		
		java.sql.Date startT = java.sql.Date.valueOf(start);
		java.sql.Date endT = java.sql.Date.valueOf(end);

		System.out.println("serviceImpl Start : "+ startT);
		System.out.println("serviceImpl end : "+ endT);
		
		Date nowTime = new Date();
		
		System.out.println("NowJavaUtilDate: " + nowTime);
			
		glist = chartMapper.barList(startT,endT,btDay);
		
		return glist;
	}
	
	/*
	 * AJAX 게시판 선그래프 리스트 가져오기
	 */
	@Override
	public List<GraphDTO> lineList(String start,String end,String btDay){
		
		glist = new ArrayList<GraphDTO>();
		
		java.sql.Date startT = java.sql.Date.valueOf(start);
		java.sql.Date endT = java.sql.Date.valueOf(end);
		
		glist = chartMapper.lineList(startT,endT,btDay);
		
		return glist;
	}
	


	
	
}
