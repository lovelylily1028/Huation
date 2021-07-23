package com.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.site.interceptor.LoggerIntercepter;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


		 @Override
			public void addInterceptors(InterceptorRegistry registry) {
			 
			 LoggerIntercepter loginIntercepter = new LoggerIntercepter();
			 
				registry.addInterceptor(loginIntercepter)
				.addPathPatterns(loginIntercepter.loginEssential) // 적용될곳
				.excludePathPatterns(loginIntercepter.loginInessential); // 적용 안되는곳
			}
}

