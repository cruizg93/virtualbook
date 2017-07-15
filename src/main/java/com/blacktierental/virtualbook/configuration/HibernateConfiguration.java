package com.blacktierental.virtualbook.configuration;
import java.util.Properties;

import javax.sql.DataSource;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.blacktierental.virtualbook.configuration"})
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.blacktierental.virtualbook.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}
	
	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, environment.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.FORMAT_SQL, environment.getRequiredProperty("hibernate.format_sql"));
		properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, environment.getRequiredProperty("hibernate.batch.size"));
		//properties.put(AvailableSettings.HBM2DDL_AUTO, environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, environment.getProperty("hibernate.current.session.context.class"));
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s){
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
