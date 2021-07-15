package com.site.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDTO;
import com.site.dto.CommentDTO;
import com.site.mapper.BoardMap;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMap boardMapper;

	@Autowired
	PageNumber pageNumber;

	List<BoardDTO> list;

	Map<String, Object> map;

	BoardDTO boardDTO;
	CommentDTO commentDTO;

	int i = 0;

// 게시판	
	@Override
	public int insertBoard(BoardDTO boardDto, MultipartFile file) {

		// 원본파일 이름
		String filename = file.getOriginalFilename();
		// 확장자명 가져오기
		if (FilenameUtils.getExtension(filename).toLowerCase() != "") {
			// 파일저장위치
			String fileUrl = "D:/Data/";

			String uploadFileName = filename;

			File f = new File(fileUrl + uploadFileName);
			// 파일업로드
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();

			}
			// 파일이름저장
			boardDto.setFileName(uploadFileName);
		} else {
			boardDto.setFileName("");
		}

		// mapper에 전달
		i = boardMapper.insertBoard(boardDto);

		return i;
	}

	@Override
	public Map<String, Object> selectBoard(String bid, String page, String category, String search,
			HttpServletRequest request, HttpServletResponse response) {

		/*
		 * 새로고침 조회수 증가 방지 처리 - 쿠키 이용 
		 */
		
		// 저장된 쿠키 불러오기
		Cookie cookies[] = request.getCookies();
		Map mapCookie = new HashMap<String, Object>();
		if (request.getCookies() != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie obj = cookies[i];
				mapCookie.put(obj.getName(), obj.getValue());
			} // for
		} // if

		// 저장된 쿠키중에 read_count만 불러오기
		String cookie_read_count = (String) mapCookie.get("read_count");
		// 저장될 새로운 쿠키값 생성
		String new_cookie_read_count = "|" + bid;

		// 저장된 쿠키에 새로운 쿠키값이 존재하는지 검사
		if (StringUtils.indexOfIgnoreCase(cookie_read_count, new_cookie_read_count) == -1) {
			// 없을 경우 쿠키 생성
			Cookie cookie = new Cookie("read_count", cookie_read_count + new_cookie_read_count);
			response.addCookie(cookie);

			// 조회수 1증가 처리
			boardMapper.selectUpHit(bid);

		}

		// content 1개 가져오기
		boardDTO = boardMapper.selectBoard(bid);
		map.put("boardDto", boardDTO);
		map.put("category", category);
		map.put("search", search);
		map.put("page", page);

		return map;
	}

	@Override
	public Map<String, Object> selectBoardList(String listPage, String category, String search) {

		list = new ArrayList<BoardDTO>();
		int page = 1;
		int limit = 10;

		if (listPage != null && listPage != "") {
			page = Integer.parseInt(listPage);
		}

		int startrow = (page - 1) * limit + 1; // 시작 게시글번호 1,11,21...
		int endrow = startrow + limit - 1; // 마지막 게시글번호 10,20,30...

		
		list = boardMapper.selectBoardList(startrow, endrow,search,category);
		
		map = new HashMap<String, Object>();
		map = pageNumber.pageNumber(page, limit, category, search);

		map.put("list", list);

		return map;
	}

	
	@Override
	public Map<String, Object> updateBoard_view(String bid) {

		boardDTO = boardMapper.selectBoard(bid);
		map.put("boardDto", boardDTO);

		return map;
	}

	@Override
	public int updateBoard(BoardDTO boardDto, MultipartFile file) {
		// 원본파일이름
		String orgfileName = file.getOriginalFilename();

		if (file.getSize() != 0) { // 파일사이즈가 0이 아니면

			// 파일 저장위치
			String fileUrl = "D:/Data/";
			// 이름에 시간추가
			long time = System.currentTimeMillis();

			String uploadFileName = orgfileName;

			File f = new File(fileUrl + uploadFileName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 파일이름저장
			boardDto.setFileName(uploadFileName);
		} else {
			// 기존 파일이름을 그대로 저장시키면됨.
			boardDto.setFileName("");
		}

		// mapper전달
		i = boardMapper.updateBoard(boardDto);

		return i;
	}

	@Override
	public int deleteBoard(String bid) {

		i = boardMapper.deleteBoard(bid);

		return i;
	}

	@Override
	public int insertBoardReply(BoardDTO boardDto, @Nullable MultipartFile file) {

		// 원본파일 이름
		String filename = file.getOriginalFilename();
		System.out.println(filename);
		// 확장자명 가져오기
		String fileNameExtension = FilenameUtils.getExtension(filename).toLowerCase();

		if (FilenameUtils.getExtension(filename).toLowerCase() != "") {
			// 파일저장위치
			String fileUrl = "D:/Data/";

			String uploadFileName = filename;

			// String uploadFileName = boardDto.getFileName();
			File f = new File(fileUrl + uploadFileName);
			// 파일업로드
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();

			}
			// 파일이름저장
			boardDto.setFileName(uploadFileName);
		} else {
			boardDto.setFileName("");
		}

		i = boardMapper.insertBoardReply(boardDto);
		boardMapper.insertBoardReplyPlus(boardDto);

		return i;
	}

	/*
	 * 첨부파일 다운로드
	 */
	@Override
	public void fileDown(String bid, HttpServletResponse response) throws Exception {

		BoardDTO boardDto = boardMapper.fileDownInfo(bid);
		String originFileName = boardDto.getFileName(); // 원본파일명
		String fileName = boardDto.getFileName(); // 파일명 중복방지를 위해 가공된 파일명

		response.setContentType("application/octet-stream");
		originFileName = new String(originFileName.getBytes("UTF-8"), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originFileName + "\"");

		OutputStream os = response.getOutputStream();
		String fileUrl = "D:/Data/";
		FileInputStream fis = new FileInputStream(fileUrl + File.separator + fileName);

		int n = 0;
		byte[] b = new byte[512];
		while ((n = fis.read(b)) != -1) {
			os.write(b, 0, n);
		}
		fis.close();
		os.close();

	}

}//
