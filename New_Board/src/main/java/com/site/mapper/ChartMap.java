package com.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.GraphDTO;

@Mapper
public interface ChartMap {

	List<GraphDTO> barList(String start,String end);

	List<GraphDTO> lineList(String start,String end);
	
}
