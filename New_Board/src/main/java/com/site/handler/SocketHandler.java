package com.site.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

// 2021-07-21
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Component
public class SocketHandler extends TextWebSocketHandler {

	/*
	 * 웹소켓 세션을 담아둘 맵 HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	 */

	/*
	 * 웹소켓 세션을 담아둘 리스트 -- roomListSessions
	 */
	List<HashMap<String, Object>> rls = new ArrayList<>();

	/********************************************************************************************
	 * 메세지 발송 메세지를 수신하면 실행 상속받은 TextWebSocketHandler가 handleTextMessage를 실행 시키며, 메세지
	 * 타입에 따라 handleBinaryMessage(파일을 받고 서버에 저장도 하고 채팅방에 전송된 이미지를 표출하는부분)또는
	 * handleTextMessage가 실행됨
	 * 
	 * WebSocketSession session : 전송 주체 정보가 담긴 세션 TextMessage message : 전송 받은 메세지 정보
	 ********************************************************************************************/
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {

		String msg = message.getPayload();
		/*
		 * JSON 파싱을 위해 message.getPayload()를 통해 받은 문자열을 만든 함수 jsonToObjectParser에 넣어서
		 * JSONObject값으로 받아서 강제 문자열 형태로 보내주는 부분
		 */
		JSONObject obj = JsonToObjectParser(msg);

		String rN = (String) obj.get("roomNumber");
		HashMap<String, Object> temp = new HashMap<String, Object>();

		if (rls.size() > 0) {
			for (int i = 0; i < rls.size(); i++) {
				String roomNumber = (String) rls.get(i).get("roomNumber"); // 세션리스트의 저장된 방번호를 가져와서
				if (roomNumber.equals(rN)) { // 같은값의 방이 존재한다면
					temp = rls.get(i); // 해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					break;
				}
			}

			// 해당 방의 세션들만 찾아서 메시지를 발송해준다.
			for (String k : temp.keySet()) {
				if (k.equals("roomNumber")) { // 다만 방번호일 경우에는 건너뛴다.
					continue;
				}

				WebSocketSession wss = (WebSocketSession) temp.get(k);
				if (wss != null) {
					try {
						wss.sendMessage(new TextMessage(obj.toJSONString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		System.out.printf("%s 로부터 [%s] 받음\n", session.getId(), message.getPayload());
	}

	
	/************************************************************************************
	 * 소켓 연결 웹소켓이 연결되면 동작 = 프론트에서 웹소켓이 정확한 경로를 잡아 생성 되는 것
	 ************************************************************************************/
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		super.afterConnectionEstablished(session);
		boolean flag = false;
		String url = session.getUri().toString();
		System.out.println("URL : "+url);
		String roomNumber = url.split("/chating/")[1];

		int idx = rls.size(); // 방의 사이즈를 조사한다.
		if (rls.size() > 0) {
			for (int i = 0; i < rls.size(); i++) {
				String rN = (String) rls.get(i).get("roomNumber");
				if (rN.equals(roomNumber)) {
					flag = true;
					idx = i;
					break;
				}
			}
		}

		if (flag) { // 존재하는 방이라면 세션만 추가한다.
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		} else { // 최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
		}

		/*
		 * 세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
		 * 생성된 세션을 저장하면 발신메세지의 타입은 getId라고 명시 후 생성된 세션ID값을 클라이언트 단으로 발송 클라이언트단에서는 type값을
		 * 통해 메세지와 초기 설정값을 구분할 예정
		 */
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));

		System.out.printf("%s 연결 됨\n", session.getId());
	}

	/***********************************************************************************************
	 * 소켓 종료 웹소켓이 종료되면 동작 = 세션 종료
	 **********************************************************************************************/
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for(int i=0; i<rls.size(); i++) {
				rls.get(i).remove(session.getId());
			}
		}
		super.afterConnectionClosed(session, status);

		System.out.printf("%s 연결 끊김\n", session.getId());
	}

	/***********************************************************************************************
	 * JSON 파일이 들어오면 파싱해주는 함수 JSON형태의 문자열을 파라미터로 받아서 SimpleJson의 파서를 활용하여
	 * JSONObject로 파싱처리 해주는 함수
	 ***********************************************************************************************/
	private static JSONObject JsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
