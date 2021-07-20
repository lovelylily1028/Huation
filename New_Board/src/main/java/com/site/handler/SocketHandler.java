package com.site.handler;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler{
	
	/*
	 * 웹소켓 세션을 담아둘 맵 
	 */
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	/*
	 * 메세지 발송
	 * 메세지를 수신하면 실행
	 * 상속받은 TextWebSocketHandler가 handleTextMessage를 실행 시키며,
	 * 메세지 타입에 따라 handleBinaryMessage(파일을 받고 서버에 저장도 하고 채팅방에 전송된 이미지를 표출하는부분)또는 handleTextMessage가 실행됨
	 * 
	 * WebSocketSession session : 전송 주체 정보가 담긴 세션
	 * TextMessage message  : 전송 받은 메세지 정보
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {

		String msg = message.getPayload();
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(msg));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("%s로부터 [%s] 받음\n",session.getId(), message.getPayload());
	}
	
	/*
	 * 소켓 연결
	 * 웹소켓이 연결되면 동작 = 프론트에서 웹소켓이 정확한 경로를 잡아 생성 되는 것
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		
		System.out.printf("%s 연결 됨\n", session.getId());
	}
	
	/*
	 * 소켓 종료
	 * 웹소켓이 종료되면 동작 = 세션 종료
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
		
		System.out.printf("%s 연결 끊김\n", session.getId());
	}
	
	
}
