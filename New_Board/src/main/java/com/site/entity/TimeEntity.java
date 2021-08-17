package com.site.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // 테이블로 매핑하지 않고, 자식 entity 에게 매핑정보를 상속하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class) // JPA에게 해당 Entity는 Auditing기능을 사용한다는 것을 알림
public class TimeEntity {

	
	/**
	 * TODO : 날짜 보내는 형식 바꾸기 YYYY-MM-DD HH:mm:ss
	 */
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createDate;
	
	@LastModifiedDate
	private LocalDateTime notifiedDate;
	
}
