package com.actions.prototype.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <p>
 * AppConfig class. Root Spring Configuration.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.actions.prototype")
public class AppConfig extends WebMvcAutoConfiguration {

}
