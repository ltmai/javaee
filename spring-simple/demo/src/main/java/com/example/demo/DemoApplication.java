package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		try(final AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			context.registerShutdownHook();

			HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
			System.out.println(helloWorld.getMessage());

			HelloWorldUpper helloUpper = (HelloWorldUpper) context.getBean("helloWorldUpper");
			System.out.println(helloUpper.getMessage()); 

			ServiceManager serviceManager = (ServiceManager) context.getBean("serviceManager");
			serviceManager.doService(); 

			InjectCollection jc=(InjectCollection)context.getBean("javaCollection");
			System.out.println(jc.getIntList());
			System.out.println(jc.getStringSet());
			System.out.println(jc.getIndexMap());
			System.out.println(jc.getProperties());			
		}
	}

}
