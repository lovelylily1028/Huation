package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	/* 작성자 */
	private String user_id;
	/* 글 번호 */
	private String bid;
	/* 글 제목 */
	private String title;
	/* 글 내용 */
	private String bcontents;
	/* 첨부된 파일 */
	private String FileName;
	/* 작성일 */
	private String day;
	/* 조회수 */
	private String hit;
	
	/*
	 * 계층형 게시판을 위한 추가 필드
	 * bgroup,bstep,bindent 
	 */
	
	/* 원글 번호 */
	private int bgroup;
	/* 원글(답글포함)에 대한 순서 */
	private int bstep;
	/* 들여쓰기를 위한 bindent */
	private int bindent;
	
	/*
	 * 상세보기 페이지 내에 
	 * 이전글 및 다음글 제목 확인을 위한 필드
	 */

	/* 다음글 번호 */
	private String next_bid;
	/* 다음글 제목 */
	private String next_title;
	/* 이전글 번호 */
	private String pre_bid;
	/* 이전글 제목 */
	private String pre_title;
	
	
}
