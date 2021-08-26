package com.site.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration /* 스프링 설정을 위한 파일임을 알림 */
@ConfigurationProperties("spring.datasource.hikari-bootdb2")
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactory2",
		transactionManagerRef = "transactionManager2",
		basePackages = {"com.site.repository"} /* repository 경로*/
		)
public class H2DataSourceConfig extends HikariConfig{

	public Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean(name = "bootdb2DataSource")
	@ConfigurationProperties("spring.datasource.hikari-bootdb2")
	public DataSource dataSource2() {
		log.info("===  H2-db2DataSource Build  =====");
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
		//return new LazyConnectionDataSourceProxy(new HikariDataSource(this));
	}
	
	@Primary
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        HashMap<String,Object> properties = new HashMap<>();

        factory.setPersistenceUnitName("bootdb2DataSource");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource2());
        factory.setPackagesToScan("com.site.entity");
        
        properties.put("hibernate.hbm2ddl.none", env.getProperty("spring.sub.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.sub.hibernate.dialect"));
        
        factory.setJpaPropertyMap(properties);

        factory.afterPropertiesSet();

        return factory;
    }
	
	@Primary
	@Bean
    public PlatformTransactionManager transactionManager2() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory2().getObject());
        return tm;
    }
	
}
