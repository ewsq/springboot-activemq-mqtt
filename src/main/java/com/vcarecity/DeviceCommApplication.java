package com.vcarecity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
public class DeviceCommApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceCommApplication.class, args);
	}
	
}
