package com.al.project.SysCo.RPi;

@EnableAutoConfiguration
@EnableScheduling
@Configuration
@ComponentScan


public class Topic{

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(StockQuoter.class, args);
    }
}