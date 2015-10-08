package com.actions.prototype.config;

import static org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <p>
 * WebAppInitializer class. Root Web Configuration.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
public class WebAppInitializer implements WebApplicationInitializer {
	
	/** {@inheritDoc} */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(ctx));
		servletContext.addFilter(DEFAULT_FILTER_NAME, new DelegatingFilterProxy(DEFAULT_FILTER_NAME))
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
		final Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
	}
}
