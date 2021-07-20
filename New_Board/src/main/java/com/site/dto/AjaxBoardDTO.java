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
	 * originNo,level,groupOrd 
	 */
	
	/* 원글 번호 : 답변을 단 본글(부모글)의 번호, 단, 원글의 경우 null을 넣는다.*/
	private int originNo;
	/* 들여쓰기를 위한 레벨 */
	private String level;
	/* 원글(답글포함)에 대한 순서 */
	private int groupOrd;

	
	
}
