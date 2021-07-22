package com.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

	/* 채팅방 번호 */
	public int roomNumber;
	/* 채팅방 제목 */
	public String roomName;
	
	@Override
	public String toString() {
		return  "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
	}
	
}
