package com.al.project.SysCo;

import appConfig.SysCoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableAutoConfiguration

public class WebApplication {
    public static void main(String[] args) {

        SpringApplication webApp = new SpringApplication(WebApplication.class);


        webApp.run();
    }
}

