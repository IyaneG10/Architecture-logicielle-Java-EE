package com.al.project.SysCo;


import appConfig.SysCoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration

public class SysCoApplication {

	@Autowired
	private SysCoConfig myConfig;


	public static void main(String[] args) {

		SpringApplication.run(SysCoApplication.class, args);
		//SpringApplication app = new SpringApplication(SysCoApplication.class);
		//app.run();

	}

	public void TestYML(){
		System.out.println("using environment: " + myConfig.getServers());
		//System.out.println("name: " + myConfig.getName());
		//System.out.println("servers: " + myConfig.getServers());
	}
}
