package com.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.site.entity.Board;

public interface BoardRepository  extends JpaRepository<Board, Long>{

	/**
	 *  데이터 조작을 담당하는 Repository 
	 */
	
	
}
