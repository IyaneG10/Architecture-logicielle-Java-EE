package com.al.project.sysco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration

public class WebApplication {
    public static void main(String[] args)  {

        SpringApplication webApp = new SpringApplication(WebApplication.class);
        webApp.run();
    }
}




