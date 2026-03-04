package com.IndustrialDataAPI;

import com.IndustrialDataAPI.configuration.ApiKeyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableAspectJAutoProxy
//@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApiKeyFilter.class)})
public class IndustrialDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndustrialDataApiApplication.class, args);
	}

}
