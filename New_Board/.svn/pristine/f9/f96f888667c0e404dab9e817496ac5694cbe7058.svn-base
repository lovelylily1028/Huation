package com.site.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.AjaxBoardDTO;
import com.site.dto.BoardDTO;

@Component
public class Scheduler {
	@Autowired
	BoardService boardService;
	@Autowired
	AjaxService aService;
	
	/* boardDTO */
	BoardDTO boardDto;
	/* ajaxDTO */
	AjaxBoardDTO aDto;
	
	@Scheduled(cron = "${board.cron}") 
	public void cronRun() {

		/* 스케쥴러 실행 시간 찍어주기 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("### Cron Run 10 Minutes  : " + simpleDateFormat.format(new Date()));

		// TODO 1에서 5 사이의 랜덤 숫자를 만든 뒤에 그 숫자만큼 for문을 돌려서, 게시물 업로드 할 예정
		int num = 0;
		num = (int) (Math.random() * 5) + 1;
		System.out.println("### 랜덤숫자 num : " + num);

		// TODO 랜덤 숫자만큼 게시물 업로드
		for (int i = 0; i < num; i++) {
			boardDto = new BoardDTO();
			MultipartFile file = null;

			boardDto.setUser_id("admin");
			boardDto.setTitle("※ 정 기 점 검 ※");
			boardDto.setBcontents("정기점검 게시물 입니다.");

			boardService.insertBoard(boardDto, file);
		}

	}
	
	@Scheduled(cron = "${AjaxBoard.cron}") 
	public void ajaxRun() {

		/* 스케쥴러 실행 시간 찍어주기 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("### Ajax Run 1 Minutes  : " + simpleDateFormat.format(new Date()));

		// TODO 1에서 5 사이의 랜덤 숫자를 만든 뒤에 그 숫자만큼 for문을 돌려서, 게시물 업로드 할 예정
		int num = 0;
		num = (int) (Math.random() * 5) + 1;
		System.out.println("### 랜덤숫자 num : " + num);

		// TODO 랜덤 숫자만큼 게시물 업로드
		for (int i = 0; i < num; i++) {
			aDto = new AjaxBoardDTO();

			aDto.setWriter("admin");
			aDto.setTitle("※ 정 기 점 검 ※");
			aDto.setAcontent("정기점검 게시물 입니다.");
			
			String rmt ="add";
			
			aService.add(aDto,rmt);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
