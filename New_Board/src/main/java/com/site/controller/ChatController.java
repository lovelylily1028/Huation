package com.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	/*
	 * Chat 파일을 넘겨주는 view 컨트롤러 생성 후 
	 * 서버 구동하여 정상적으로 .jsp 페이지에 접근이 되는지 확인
	 */
	@RequestMapping("/chat")
	public ModelAndView chat() {
		/*
		 * ModelAndView : 데이터를 전송시킬 수 있는 리턴타입, 데이터와 뷰를 동시에 설정 가능 (String + model.addAttribute)
		 * String : 단순하게 페이지만 열어주는 역할
		 */
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/chat"); // 뷰의 이름 설정
		return mv;
	}
	
	
}
