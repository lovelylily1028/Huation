package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxBoardDTO {

	/* 글 번호 */
	private int code;
	/* 글 제목 */
	private String title;
	/* 글 내용 */
	private String acontent;
	/* 글 작성자 */
	private String writer;
	/* 등록 시간 */
	private String reg_datetime;
	/* 조회수 */
	private String hit;
	
	/*
	 * 계층형 게시판을 위한 추가 필드
	 * originNo,level 
	 */
	/* 원글 번호 : 답변을 단 원래 글(부모글) 본글의 경우 본인의 code를 갖는다.*/
	private int originNo;
	/* 들여쓰기를 위한 레벨 */
	private String level;
	
//	/* 원글(답글포함)에 대한 순서 */
//	private int groupOrd;
//	/* 답글 계층 */
//	private int groupLayer;
//	
	
	
	// 답글 부분은 오라클 계층 쿼리를 사용하여 구현하기
	
	/*
	 * private String writer;
	 * private String bnum; 
	 * private String title;
	 * private String bcontents;
	 * private String FilName; 
	 * private String wtday;
	 * private String hit;
	 */
	
	
	
}
