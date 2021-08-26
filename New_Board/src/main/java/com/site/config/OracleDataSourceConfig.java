package com.site.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration /* 해당 클래스가 설정관련 파일이라고 설정하는 어노테이션 */
/* MapperScan :  해당 경로 밑에 있는 mapper 인터페이스를 스캔하여 빈으로 등록한다. */
@MapperScan(value = "com.site.mapper ", sqlSessionFactoryRef = "bootdb1SqlSessionFactory")
@EnableTransactionManagement
public class OracleDataSourceConfig {
// @ConfigurationProperties : 프로퍼티 설정파일을 읽어들이는 어노테이션
// application.properties의 설정한 커스텀데이터소스 설정 부분을 prefix의 값으로 설정한다.

	public Logger log = LoggerFactory.getLogger(getClass());
	
	@Bean(name = "bootdb1DataSource")
	@ConfigurationProperties("spring.datasource.hikari-bootdb1")
	public DataSource boot1db1DataSource() {
		log.info("===  ORACLE-db1DataSource Build  =====");
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "bootdb1SqlSessionFactory")
	public SqlSessionFactory bootdb1DataSourceSessionFactory(
			@Qualifier("bootdb1DataSource") DataSource bootdb1DataSource, ApplicationContext applicationContext)
			throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(bootdb1DataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapper/*Mapper.xml");
		
		sqlSessionFactoryBean.setMapperLocations(res);

		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "bootdb1SqlSessionTemplate")
	public SqlSessionTemplate masterSqlSessionTemplate(SqlSessionFactory bootdb1SqlSessionTemplate) throws Exception {
		return new SqlSessionTemplate(bootdb1SqlSessionTemplate);
	}


}
