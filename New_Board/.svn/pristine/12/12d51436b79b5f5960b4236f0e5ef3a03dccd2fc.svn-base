package com.site.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.site.service.BoardListExcelExporter;
import com.site.dto.BoardDTO;
import com.site.dto.ExcelData;
import com.site.service.ExcelService;

@Controller
public class ExcelController {

	BoardDTO boardDto;

	@Autowired
	ExcelService excelService;
	
	//변수선언
	Map<String, Object> map = new HashMap<String, Object>();


	/**
	 * 다운로드 엑셀파일 생성
	 * 
	 * @param page : 현재 리스트 페이지
	 * @param category : 검색된 category 값
	 * @param search : 검색된 search 값
	 */
	@GetMapping("/downExcel")
	public void downExcelForm(@RequestParam @Nullable String page, 
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search,
			HttpServletResponse response) throws Exception {
		
		//컨텐츠 타입 지정
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		
		//엑셀 파일 명 - 다운로드 한 시각 추가 표시
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		String currentDateTime = dateFormatter.format(new Date());
		String fileName = "boardList_" + currentDateTime + ".xlsx";
		String headerValue = "attachment; filename="+fileName;
		response.setHeader(headerKey, headerValue);
		
		//엑셀 파일로 출력할 Data
		List<BoardDTO> excelList = excelService.excellist(category,search);
		
		//엑셀 추출 및 가공(디자인포함) - class 호출
		BoardListExcelExporter excelExporter = new BoardListExcelExporter(excelList);
		excelExporter.export(response);
		
	}


	/**
	 * 엑셀 업로드
	 */
	@RequestMapping("/excelUp")
	public String excelUp(MultipartFile file, Model model) throws IOException{
			
		excelService.listExcelUp(file);
		model.addAttribute("map", map);
			
		return "redirect:/NewList";
	}
	
	/**
	 * 업로드된 엑셀파일 읽기
	 */
	@RequestMapping("/excel/read")
	public String readExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException { // 2

		List<ExcelData> dataList = new ArrayList<>();

		String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3

		if (!extension.equals("xlsx") && !extension.equals("xls")) {
			throw new IOException("엑셀파일만 업로드 해주세요.");
		}

		Workbook workbook = null;

		if (extension.equals("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		} else if (extension.equals("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}

		Sheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4

			Row row = worksheet.getRow(i);

			ExcelData data = new ExcelData();

			data.setNum((int) row.getCell(0).getNumericCellValue());
			data.setName(row.getCell(1).getStringCellValue());
			data.setEmail(row.getCell(2).getStringCellValue());

			dataList.add(data);
		}

		model.addAttribute("datas", dataList); // 5

		return "excel/excelList";

	}

	/**
	 * 업로드된 엑셀파일을 화면에 출력
	 */
	@RequestMapping("/excel")
	public String main() { // 1
		return "excel";
	}

	
}//
