package edu.tamu.app.config;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import edu.tamu.framework.config.CoreWebMvcConfig;

@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class AppWebMvcConfig extends CoreWebMvcConfig {

	@Value("${app.ui.path}")
	private String path;
	
    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
       return new ResourceUrlEncodingFilter();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("WEB-INF" + path + "/");
        registry.setOrder(Integer.MAX_VALUE - 2);
    }
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new html5Forwarder();
    }
    
    private static class html5Forwarder implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
        		
        		
        		container.setContextPath("/tamu-ui-seed");
        	
        		//container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
        
        }
    }

}