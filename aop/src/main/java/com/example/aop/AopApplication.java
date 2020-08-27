package com.example.aop;

import com.example.aop.service.impl.EmployeeManagerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);

		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");

		EmployeeManagerImpl manager = context.getBean(EmployeeManagerImpl.class);

		manager.getEmployeeById(1);

	}

}
