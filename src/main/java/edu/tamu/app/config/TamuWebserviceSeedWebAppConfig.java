/* 
 * MyLibraryWebAppConfig.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import edu.tamu.app.controller.interceptor.AppRestInterceptor;
import edu.tamu.framework.config.CoreWebAppConfig;

/** 
 * Web MVC Configuration for application controller.
 * 
 * @author
 *
 */
@Configuration
@ComponentScan(basePackages = {"edu.tamu.app.config", "edu.tamu.app.controller"})
@ConfigurationProperties(prefix="app.controller")
@EnableJpaRepositories(basePackages={"edu.tamu.app.model.repo","edu.tamu.framework.model.repo"})
@EntityScan(basePackages={"edu.tamu.app.model","edu.tamu.framework.model"})
public class TamuWebserviceSeedWebAppConfig extends CoreWebAppConfig {	
	
	/**
	 * Rest interceptor bean.
	 *
	 * @return      RestInterceptor
	 *
	 */
	@Bean
	public AppRestInterceptor restInterceptor() {
	    return new AppRestInterceptor();
	}

	/**
	 * Add interceptor to interceptor registry.
	 *
	 * @param       registry	   InterceptorRegistry
	 *
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(restInterceptor()).addPathPatterns("/rest/**");
	}
}
