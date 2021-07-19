package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AjaxCommentDTO {

	private int code;
	private int ccode;
	private String writer;
	private String recontent;
	private String reg_date;
	
	
}
