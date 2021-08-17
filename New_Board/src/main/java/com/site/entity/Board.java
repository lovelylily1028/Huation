package com.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeEntity{
	
	@Id@GeneratedValue
	private Long id;
	
	@Column
	private String writer;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Builder
	public Board(Long id, String writer, String content, String title) {
		this.id = id;
		this.writer = writer;
		this.content = content;
		this.title = title;
	}

}
