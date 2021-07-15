package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	
	private String user_id;
	private String bid;
	private String title;
	private String bcontents;
	private String FileName;
	private String day;
	private String hit;
	// 댓글 관련
	private int bgroup;
	private int bstep;
	private int bindent;
	
	
}
