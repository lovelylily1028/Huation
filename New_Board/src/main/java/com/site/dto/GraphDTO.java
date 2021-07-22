package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphDTO {
	
	/* 조회할 날짜 */
	private String wrtday;
	/* 조회된 날자의 게시물 등록수*/
	private int bcount;

	
	
}
