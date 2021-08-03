package com.site.mapper;

import java.sql.Date;
import java.util.List;

import com.site.dto.GraphDTO;

public interface ChartMap {

	List<GraphDTO> barList(Date startT, Date endT, String btDay);

	List<GraphDTO> lineList(Date startT, Date endT, String btDay);

}
