package com.site.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;
import com.site.mapper.ExcelMapper;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	ExcelMapper excelMapper;

	/*
	 * 엑셀 다운로드 - 리스트 뽑아오기
	 */
	@Override
	public List<BoardDTO> excellist(String category, String search) {

		List<BoardDTO> excelList = excelMapper.selectList(category, search);

		return excelList;
	}

	/*
	 * 엑셀 업로드 - 리스트 읽어오기
	 */
	@Override
	public void listExcelUp(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 엑셀파일 확장자 확인

		// 확장자에 따라 XSSF, HSSF(세부설명 블로그 : https://blog.daum.net/asumade/117)
		Workbook workbook = null;
		if (extension.equals("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		} else if (extension.equals("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}

		Sheet worksheet = workbook.getSheetAt(0);

		System.out.println("입력할 줄의 수 : "+worksheet.getPhysicalNumberOfRows());
		// for문을 이용하여 엑셀파일 읽기(헤더Row 부분은 제외! 즉, i=1부터)
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Row row = worksheet.getRow(i);

				BoardDTO excelData = new BoardDTO();
				// row.getCell(0)은 글 번호 Cell 제외
				// 글번호는 PK이므로 DB에 저장할 때 시퀀스를 이용하여 저장한다.
				excelData.setTitle(row.getCell(1).getStringCellValue()); // 제목
				excelData.setBcontents(row.getCell(2).getStringCellValue()); // 내용
				excelData.setUser_id(row.getCell(3).getStringCellValue()); // 작성자
				excelData.setDay(row.getCell(4).getStringCellValue()); // 작성일
				
				// 여기에 값을 한번 찍어보자
				
				// Row 1개씩 엑셀파일 읽은 후 DB에 차례대로 저장한다.
				excelMapper.insertExcelUp(excelData);
			
			

		} // for

	}

}
