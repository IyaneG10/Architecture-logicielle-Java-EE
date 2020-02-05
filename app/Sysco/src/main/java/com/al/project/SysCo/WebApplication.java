package com.al.project.SysCo;

import appConfig.SysCoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration

public class WebApplication {
    public static void main(String[] args) {

        SpringApplication webApp = new SpringApplication(WebApplication.class);
        webApp.run();
    }
}
