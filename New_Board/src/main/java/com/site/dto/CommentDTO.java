package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommentDTO {
	
	/* 댓글 작성 게시물 번호*/
	private String bid;
	/* 댓글 번호 */
	private String cid; 
	/* 작성자 */
	private String user_id; 
	/* 댓글 내용 */
	private String recontent;
	/* 작성일 */
	private String cdate;
	
	
	/*
	 * 대댓글을 위한 추가 필드
	 * cgroup,step,indent
	 */
	private int cgroup; 
	private int step;
	private int indent;
	
	
}
