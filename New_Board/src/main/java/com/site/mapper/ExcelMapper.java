package com.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.BoardDTO;

@Mapper
public interface ExcelMapper {

	List<BoardDTO> selectList(String category,String search);

	void excelInsert(BoardDTO boardDto);

	void insertExcelUp(BoardDTO excelData);

	
	
	
}
