package com.al.project.SysCo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableAutoConfiguration

public class RpiApplication {

	public static void main(String[] args) {

		SpringApplication rpiApp = new SpringApplication(RpiApplication.class);
		rpiApp.run();
	}
}
