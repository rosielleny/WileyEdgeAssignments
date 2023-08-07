package com.doc.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebMvcConfigurer {

	@Configuration
	public class MvcConfig extends WebMvcConfigurationSupport {

	    @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
	    }
	}
}