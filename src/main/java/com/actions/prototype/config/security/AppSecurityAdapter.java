package com.actions.prototype.config.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * <p>
 * AppSecurityAdapter class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Configuration
@EnableWebSecurity
public class AppSecurityAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private DataSource dataSource;
	
	private static final String USERS_QUERY = "SELECT username, password, enabled FROM User WHERE username = ?";
	private static final String AUTHORITIES_QUERY = "SELECT username, role FROM User_roles WHERE username = ?";
	private AuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
	
	/** {@inheritDoc} */
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(USERS_QUERY)
			.authoritiesByUsernameQuery(AUTHORITIES_QUERY);
	}

	/** {@inheritDoc} */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/user/register").permitAll()
				.anyRequest().authenticated()
			.and()
				.exceptionHandling()
					.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN))
			.and()
				.formLogin()
					.successHandler(successHandler)
					.failureHandler(authenticationFailureHandler)
	                .permitAll()
			.and()
				.logout()
					.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
						if (authentication != null && authentication.getDetails() != null) {
							 httpServletRequest.getSession().invalidate();
						}
						httpServletResponse.setStatus(HttpServletResponse.SC_OK);
					})
			.and()
				.csrf().disable();
	}
}
