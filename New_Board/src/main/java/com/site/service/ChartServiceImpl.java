package com.site.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.GraphDTO;
import com.site.mapper.ChartMap;

@Service
public class ChartServiceImpl implements ChartService {

	public Logger log = LoggerFactory.getLogger(getClass());
	
	GraphDTO  graphDTO;
	
	List<GraphDTO> glist;
	
	@Autowired
	ChartMap chartMapper;
	
	/*
	 * 일반게시판 막대그래프 리스트 가져오기
	 */
	@Override
	public List<GraphDTO> barList(String start,String end,String tomorrow,String btDay){

				
		glist = new ArrayList<GraphDTO>();
		
		java.sql.Date startT = java.sql.Date.valueOf(start);
		java.sql.Date endT = java.sql.Date.valueOf(end);
		java.sql.Date tomorrowT = java.sql.Date.valueOf(tomorrow);

			
		log.info("[	BOARD Query START DAY : {} ]", startT);
		log.info("[	BOARD  Query END DAY : {} ]", endT);
		log.info("[	BOARD  Query TOMORROW : {} ]", tomorrowT);
			
		glist = chartMapper.barList(startT,endT,tomorrowT,btDay);
		
		return glist;
	}
	
	/*
	 * AJAX 게시판 선그래프 리스트 가져오기
	 */
	@Override
	public List<GraphDTO> lineList(String start,String end,String tomorrow,String btDay){
		
		glist = new ArrayList<GraphDTO>();
		
		java.sql.Date startT = java.sql.Date.valueOf(start);
		java.sql.Date endT = java.sql.Date.valueOf(end);
		java.sql.Date tomorrowT = java.sql.Date.valueOf(tomorrow);
		
		
		log.info("[	Ajax_BOARD Query START DAY : {} ]", startT);
		log.info("[	Ajax_BOARD  Query END DAY : {} ]", endT);
		log.info("[	Ajax_BOARD  Query TOMORROW : {} ]", tomorrowT);
		
		glist = chartMapper.lineList(startT,endT,tomorrowT,btDay);
		
		return glist;
	}
	


	
	
}
