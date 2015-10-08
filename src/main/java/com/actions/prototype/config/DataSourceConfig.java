package com.actions.prototype.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * <p>
 * DataSourceConfig class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Configuration
//@EnableJpaRepositories(basePackages = "com.actions.prototype")
public class DataSourceConfig {
	
	/**
	 * <p>
	 * Build an embedded database and creates a DataSource
	 * </p>
	 * 
	 * @return a {@link javax.sql.DataSource} object.
	 */
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).
				addScript("db.sql").build();
	}
	
//	@Bean
//	public EntityManagerFactory entityManagerFactory() {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("com.actions.prototype");
//		factory.setPersistenceUnitName("hsql");
//		factory.setDataSource(dataSource());
//		factory.afterPropertiesSet();
//
//		return factory.getObject();
//	}
//
//	@Bean
//	public JpaTransactionManager transactionManager() {
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory());
//		return txManager;
//	}
}
