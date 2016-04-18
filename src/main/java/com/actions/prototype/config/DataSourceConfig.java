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
 * @author Omar Ortiz.
 */
@Configuration
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
}
