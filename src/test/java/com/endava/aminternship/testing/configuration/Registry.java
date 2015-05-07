package com.endava.aminternship.testing.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Registry {
	
	public static ApplicationContext getContext(String fileName){
		return new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/"+fileName);
	}
	
	public static ApplicationContext getContext(String[] fileNames){
		return new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/test-context.xml");
	}
}
