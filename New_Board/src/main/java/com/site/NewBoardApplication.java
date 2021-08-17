package com.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@SpringBootApplication
@EnableScheduling // 스케쥴 기능을 사용하기 위한 어노테이션
@EnableJpaAuditing	// JPA auditing 기능을 사용하기 위한 어노테이션
public class NewBoardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NewBoardApplication.class, args);
		
	}
	
	/* war 배포를 위한 소스 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(NewBoardApplication.class);
	}
	
	/* bean에 설정 */
	@Bean
	public TaskScheduler taskScheduler() {
		
		return new ConcurrentTaskScheduler(); //단일 스레드 구현
	}

	
}
