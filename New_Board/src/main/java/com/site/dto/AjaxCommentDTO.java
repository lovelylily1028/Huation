package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AjaxCommentDTO {

	/* 게시물 번호 */
	private int code;
	/* 댓글 번호 */
	private int ccode; 
	/* 작성자 */
	private String writer; 
	/* 댓글 내용 */
	private String recontent; 
	/* 댓글 작성일 */
	private String reg_date;
	
	
}
