package com.IndustrialDataAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class IndustrialDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndustrialDataApiApplication.class, args);
	}

}
