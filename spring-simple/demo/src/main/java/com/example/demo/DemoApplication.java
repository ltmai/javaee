package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		try(final AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml")) {
			HelloWorld bean = (HelloWorld) context.getBean("helloWorld");
			System.out.println(bean.getMessage());
		}
	}

}
