package com.site.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.GraphDTO;

@Mapper
public interface ChartMap {

	List<GraphDTO> barList(Date start, Date end,Date tomorrow, String btDay);

	List<GraphDTO> lineList(Date start, Date end,Date tomorrow, String btDay);


}
