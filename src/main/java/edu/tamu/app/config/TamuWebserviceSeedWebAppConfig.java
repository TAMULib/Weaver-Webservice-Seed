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

import java.util.Properties;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import edu.tamu.app.controller.interceptor.AppRestInterceptor;
import edu.tamu.app.wro4j.config.CustomConfigurableWroManagerFactory;
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
//@EnableJpaRepositories(basePackages={"edu.tamu.app.model.repo","edu.tamu.framework.model.repo"})
//@EntityScan(basePackages={"edu.tamu.app.model","edu.tamu.framework.model"})
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
	
	/**
	 * WRO Configuration
	 */

    @Bean
    FilterRegistrationBean webResourceOptimizer(Environment env) {
    	FilterRegistrationBean fr = new FilterRegistrationBean();
    	ConfigurableWroFilter filter = new ConfigurableWroFilter();
		Properties props = buildWroProperties(env);
		filter.setProperties(props);
		filter.setWroManagerFactory(new CustomConfigurableWroManagerFactory(props));
    	filter.setProperties(props);
    	fr.setFilter(filter);
    	fr.addUrlPatterns("/wro/*");
    	return fr;
    }

    private static final String[] OTHER_WRO_PROP = new String[] { ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,
    		ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS };

    private Properties buildWroProperties(Environment env) {
    	Properties prop = new Properties();
    	for (ConfigConstants c : ConfigConstants.values()) {
    		addProperty(env, prop, c.name());
    	}
    	for (String name : OTHER_WRO_PROP) {
    		addProperty(env, prop, name);
    	}
    	return prop;
    }

    private void addProperty(Environment env, Properties to, String name) {
    	String value = env.getProperty("wro." + name);
    	if (value != null) {
    		to.put(name, value);
    	}
    }
	
}
