package com.site.service;

import java.util.List;

import com.site.dto.GraphDTO;

public interface ChartService {

	List<GraphDTO> barList(String start,String end);

	List<GraphDTO> lineList();
}
